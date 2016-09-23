package com.mage.cms.common;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import com.mage.cms.anniton.UrlPathMethod;
import com.mage.cms.anniton.UrlPathService;
import com.mage.common.ServiceMethodDefinition;
import com.mage.common.ServiceMethodHandler;
import com.mage.common.ZteError;
import com.mage.platform.framework.context.SpringContextHolder;
import com.mage.utils.CommonTools;

/**
 * cms服务类 注册
 * @author pzh
 */
public class DefaultCmsBeanDefine{

    private  final Map<String, ServiceMethodHandler> serviceHandlerMap = new HashMap<String, ServiceMethodHandler>();

    private final Set<String> serviceMethods = new HashSet<String>();

    private static boolean doneLoad  = false;

    public static DefaultCmsBeanDefine getInstance(){
        return new DefaultCmsBeanDefine();
    }

    /**
     * 初始化cms事件处理器
     */
    public DefaultCmsBeanDefine(){
    	if(doneLoad)
    		return ;
        String[] beanNames = SpringContextHolder.getApplicationContext().getBeanNamesForType(Object.class);
        //默认启动时装载一次
        for (String beanName :beanNames) {
            registerFromContext(beanName);
        }
        doneLoad = true;
    }

    public void addServiceMethod(String methodName, String version, ServiceMethodHandler serviceMethodHandler) {
        serviceMethods.add(methodName);
        serviceHandlerMap.put(ServiceMethodHandler.methodWithVersion(methodName, version), serviceMethodHandler);
    }

    public ServiceMethodHandler getServiceMethodHandler(String methodName, String version) {
        ServiceMethodHandler serviceMethodHandler = serviceHandlerMap.get(ServiceMethodHandler.methodWithVersion(methodName, version));
        if(serviceMethodHandler == null){
            CommonTools.addError(new ZteError("-1","找不到方法"));
        }
        return serviceMethodHandler;
    }
    
    public void registerFromContext(final String  beanName) throws BeansException {
        if(!(beanName.startsWith("shop") && beanName.indexOf("Widget")>-1))
            return;
        Class<?> handlerType = null;
        try{
            handlerType = SpringContextHolder.getBean(beanName).getClass();
        }catch (Exception e) {}
        if(handlerType == null)
            return;
        
        ReflectionUtils.doWithMethods(handlerType, new ReflectionUtils.MethodCallback() {
                    public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                        ReflectionUtils.makeAccessible(method);

                        UrlPathMethod urlPathMethod = method.getAnnotation(UrlPathMethod.class);
                        UrlPathService urlPathService = method.getDeclaringClass().getAnnotation(UrlPathService.class);
                        ServiceMethodDefinition  definition = buildServiceMethodDefinition(urlPathService, urlPathMethod);
                        ServiceMethodHandler serviceMethodHandler = new ServiceMethodHandler();
                        serviceMethodHandler.setServiceMethodDefinition(definition);

                        serviceMethodHandler.setHandler(SpringContextHolder.getBean(beanName)); //handler
                        serviceMethodHandler.setHandlerMethod(method);
                        addServiceMethod(definition.getMethod(), definition.getVersion(), serviceMethodHandler);
                    }
                },
                new ReflectionUtils.MethodFilter() {
                    public boolean matches(Method method) {
                        return AnnotationUtils.findAnnotation(method, UrlPathMethod.class) != null;
                    }
                }

        );
    }

    private ServiceMethodDefinition buildServiceMethodDefinition(UrlPathService serviceMethodBean, UrlPathMethod serviceMethod) {
        ServiceMethodDefinition definition = new ServiceMethodDefinition();
        definition.setMethod(serviceMethod.path());
        definition.setVersion("1.0");
        definition.setMethodGroup(serviceMethodBean.pluginType());
        return definition;
    }

    public Map<String, ServiceMethodHandler> getAllServiceMethodHandlers() {
        return serviceHandlerMap;
    }
    
    public boolean isValidMethod(String methodName) {
        return serviceMethods.contains(methodName);
    }

    public boolean isValidMethodVersion(String methodName, String version) {
        return serviceHandlerMap.containsKey(ServiceMethodHandler.methodWithVersion(methodName, version));
    }

}