package cn.interesting.sdk.qywx.utils;

import java.io.IOException;
import java.sql.Timestamp;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.type.TypeReference;

import cn.interesting.sdk.qywx.exception.JSONException;

/**
 * JSON转换默认实现
 * @author Administrator
 *
 */
public final class JSONConverter implements JSONConvert {
	 /**
	  * This mapper (or, data binder, or codec) provides functionality for
	  * converting between Java objects (instances of JDK provided core classes,
	  * beans), and matching JSON constructs.
	  */
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public JSONConverter() {
		CustomDeserializerFactory deserializerFactory = new CustomDeserializerFactory();
		deserializerFactory.addSpecificMapping(Timestamp.class, new TimestampDeserializer());
		StdDeserializerProvider provider = new StdDeserializerProvider(deserializerFactory);
		objectMapper.setDeserializerProvider(provider);
		objectMapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	/**
	 * 时间反序列化处理 yyyy-MM-dd & yyyy-MM-dd HH:mi:ss
	 * @author Administrator
	 *
	 */
	public static class TimestampDeserializer extends JsonDeserializer<Timestamp>{

		@Override
		public Timestamp deserialize(JsonParser jp, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			String val = jp.getText();
			if(val == null){
				return null;
			}else{
				if(val.length() == 10){
					return Timestamp.valueOf(val + " 00:00:00");
				}else{
					return Timestamp.valueOf(val);
				}
			}
		}
		
	}

	@Override
	public String object2JSON(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (IOException e) {
			throw new JSONException("["+object.getClass()+"]转JSON失败", e);
		}
	}

	@Override
	public <T> T JSON2Object(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new JSONException("JSON转换["+clazz.getName()+"]失败", e);
		}
	} 

	@Override
	public <T> T JSON2List(String json, TypeReference<T> typeReference) {
		try {typeReference.getType();
			return objectMapper.readValue(json, typeReference);
		} catch (IOException e) {
			throw new JSONException("JSON转换["+typeReference.getType().getTypeName()+"]失败", e);
		}
	}
}
