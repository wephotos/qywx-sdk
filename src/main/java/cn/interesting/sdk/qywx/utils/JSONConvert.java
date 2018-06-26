package cn.interesting.sdk.qywx.utils;

import org.codehaus.jackson.type.TypeReference;

/**
 * JSON转换接口
 * @author Administrator
 *
 */
public interface JSONConvert {

	/**
	 * 对象转JSON
	 * @param object
	 * @return
	 */
	String object2JSON(Object object);
	/**
	 * JSON转对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	<T> T JSON2Object(String json, Class<T> clazz);
	/**
	 * JSON转对象，支持复制泛型的转换
	 * @param json
	 * @param clazz
	 * @return
	 */
	<T> T JSON2List(String json, TypeReference<T> typeReference);
}
