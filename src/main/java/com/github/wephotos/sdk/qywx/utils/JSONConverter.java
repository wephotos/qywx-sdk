package com.github.wephotos.sdk.qywx.utils;

import java.io.IOException;
import java.sql.Timestamp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.wephotos.sdk.qywx.exception.JSONException;

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
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Timestamp.class, getTimestampDeserializer());
		objectMapper.registerModule(module);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	private JsonDeserializer<Timestamp> getTimestampDeserializer() {
		return new JsonDeserializer<Timestamp>() {
			@Override
			public Timestamp deserialize(JsonParser p, DeserializationContext ctxt)
					throws IOException, JsonProcessingException {
				String val = p.getText();
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
			
		};
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
