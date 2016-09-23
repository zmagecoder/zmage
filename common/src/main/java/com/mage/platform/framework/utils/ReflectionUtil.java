package com.mage.platform.framework.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.StringUtils;

import com.mage.database.BigTextField;
import com.mage.database.DateField;
import com.mage.database.NotDbField;

/**
 * 通用反射处理类
 * @author pzh
 */
public class ReflectionUtil {
	
	/**
	 * 反射调用方法
	 * @param className
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(String className, 
				String methodName, Object[] args) {
		try {
			Class<?> serviceClass = Class.forName(className);
			Object service = serviceClass.newInstance();

			Class<?>[] argsClass = new Class[args.length];
			for (int i = 0, j = args.length; i < j; i++) {
				argsClass[i] = args[i].getClass();
			}
			Method method = serviceClass.getMethod(methodName, argsClass);
			return method.invoke(service, args);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 反射构造对象
	 * @param className
	 * @param _args
	 * @return
	 */
	public static Object newInstance(String className,Object... _args ){
		try {
			Class<?>[] argsClass = new Class[_args.length];                                  
			                                                                                 
			for (int i = 0, j = _args.length; i < j; i++) {   
				if(_args[i] == null){
					argsClass[i] = null;
				}else{
					argsClass[i] = _args[i].getClass();
				}
			}      
			Class<?> newoneClass  = Class.forName(className);
			Constructor<?> cons = newoneClass.getConstructor(argsClass);                    
			Object obj = cons.newInstance(_args);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据对象返回数据库字段
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getDbFields(Object bean){
		Map<String, Object> dbMap = null;
		try {
			dbMap =  bean instanceof Map ? (Map<String, Object>)bean : PropertyUtils.describe(bean);
			Method[] ms = bean.getClass().getMethods();
			Class<?> parentClass = bean.getClass().getSuperclass();
			for(Method m : ms){
				String name = m.getName();
				if("getClass".equals(name)){					//剔除无用字段
					dbMap.remove("class");
					continue;
				}
				if(name.startsWith("get") || name.startsWith("is")){
					//父类的数据库字段
					
					try{
						Method parent = parentClass.getMethod(name);;
						if(removeField(parent, dbMap, name))			//删除非数据库字段
							continue;
						dealDateField(parent, dbMap, name);				//日期字段处理  如果添加其他字段处理，请用continue
					}catch(Exception e){}
					
					//当前类的数据库字段
					if(removeField(m, dbMap, name))						//删除非数据库字段
						continue;
					dealDateField(m, dbMap, name);						//日期字段处理  如果添加其他字段处理，请用continue
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbMap;
	}
	
	/**
	 * 剔除前缀
	 * @param method
	 * @return
	 */
	public static String removePrex(String method){
		if(!StringUtils.isEmpty(method)){
			if(method.startsWith("get"))
				return method.substring(3, 4).toLowerCase() + method.substring(4);
			if(method.startsWith("is"))	
				return method.substring(2, 3).toLowerCase() + method.substring(3);
		}
		return method;
	}
	
	/**
	 * 删除非数据库字段
	 * @param method
	 * @param dbMap
	 * @param methodName
	 * @return
	 */
	public static boolean removeField(Method method, Map<String, Object> dbMap, String methodName){
		try{
			if(method.getAnnotation(NotDbField.class) != null 
					|| method.getAnnotation(BigTextField.class) != null){			//非数据、大文本字段
				dbMap.remove(removePrex(methodName));
				return true;
			}
		}catch(Exception e){}
		return false;
	}
	
	/**
	 * 处理日期字段
	 * @param method
	 * @param dbMap
	 * @param methodName
	 * @return
	 */
	public static boolean dealDateField(Method method, Map<String, Object> dbMap, String methodName){
		String fieldName = removePrex(methodName);
		try{
			DateField dateField = method.getAnnotation(DateField.class);
			if(dateField != null){						//日期字段处理
				String filedVal = MapUtils.getString(dbMap, fieldName, "");
				String format = dateField.format();
				dbMap.put(fieldName, DateUtil.parse(filedVal, format, Timestamp.class));
				return true;
			}
		}catch(Exception e){
			dbMap.put(fieldName, "");									//非日期字段，直接清空
		}
		return false;
	}
	
}
