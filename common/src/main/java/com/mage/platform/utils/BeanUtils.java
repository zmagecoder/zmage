package com.mage.platform.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 通用反射处理类
 * @author pzh
 */
public class BeanUtils {
	
	/**
	 * copy 对象属性
	 * @param target
	 * @param src
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void copyProperties(Object target, Object src) throws Exception {
		if (target instanceof Map) {
			Map<String, Object> targetMap = (Map<String, Object>) target;
			if (src instanceof Map) {
				targetMap.putAll((Map<String, Object>)src);
			} else
				targetMap.putAll(PropertyUtils.describe(src));
		} else{
			org.apache.commons.beanutils.BeanUtils.copyProperties(target, src);
		}
	}
	
	
	/**
	 * pojo 对象转换成 数据库Map对象
	 * @param paramMap
	 * @param obj
	 * @throws Exception
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void beanToMap(Map<String, Object> paramMap, Object obj) throws Exception {
		BeanUtils.copyProperties(paramMap, obj);
		MapUtils.removeKeys(paramMap,new String []{"class"});
		Iterator<String> it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			Object value = paramMap.get(key);
			if (value != null) {
				String clsName = value.getClass().getName();
				if(clsName.indexOf("com.mage") > -1) {
					Map<String, Object> subParamMap = new HashMap<String, Object>();
					beanToMap(subParamMap, value);
					paramMap.put(key, subParamMap);
				}else if(value instanceof List){
					List listVal = (List)value;
					List subArrList = new ArrayList();
					for (int i = 0; i < listVal.size(); i++) {
						Object pvalue = listVal.get(i);
						clsName = pvalue.getClass().getName();
						if(clsName.indexOf("com.mage") > -1) {
							Map<String, Object> subParamMap = new HashMap<String, Object>();
							beanToMap(subParamMap, pvalue);
							subArrList.add(subParamMap);
						}else{
							subArrList.add(pvalue);
						}
					}
					paramMap.put(key, subArrList);
				}
			}
		}
	}
	
	/**
	 * 
	 * bean转换为json
	 * @param src
	 * @return
	 */
	public static <T> String beanToJson(T src){
		SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出空置字段
			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
			SerializerFeature.WriteClassName,
		};
		return JSON.toJSONString(src,features);
	}
	
	/**
	 * json转为Bean
	 * @param <T>
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBean(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}
	
	/**
	 * bean转换为json
	 * @param src
	 * @return
	 */
	public static <T> String beanToJsonNCls(T src){
		SerializerFeature[] features = { SerializerFeature.WriteMapNullValue, // 输出空置字段
			SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null
			SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null
			SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null
			SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null
		};
		return JSON.toJSONString(src,features);
	}
}
