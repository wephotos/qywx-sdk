package cn.interesting.sdk.qywx.utils;

import org.codehaus.jackson.type.TypeReference;

/**
 * JSON转换工具类
 * @author Administrator
 *
 */
public final class JSONUtils {

	/**
	 * JSON转换对象
	 */
	private static JSONConvert jsonConvert;
	
	static{
		jsonConvert = new JSONConverter();
	}

	public static void setJsonConvert(JSONConvert jsonConvert) {
		JSONUtils.jsonConvert = jsonConvert;
	}
	
	/**
	 * 对象转JSON
	 * @param object
	 * @return
	 */
	public static String object2JSON(Object object){
		return jsonConvert.object2JSON(object);
	}
	/**
	 * JSON转对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T JSON2Object(String json, Class<T> clazz){
		return jsonConvert.JSON2Object(json, clazz);
	}
	/**
	 * JSON转列表
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T JSON2List(String json, TypeReference<T> typeReference){
		return jsonConvert.JSON2List(json, typeReference);
	}
}
