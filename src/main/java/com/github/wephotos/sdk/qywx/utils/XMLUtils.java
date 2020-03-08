package com.github.wephotos.sdk.qywx.utils;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.github.wephotos.sdk.qywx.config.EnvConfig;
import com.github.wephotos.sdk.qywx.exception.XMLException;

/**
 * XML工具类
 * @author Administrator
 *
 */
public final class XMLUtils {
	
	/**
	 * XML转为JavaBean对象
	 * @param xml
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2Object(String xml, Class<T> clazz){
		try{
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T)unmarshaller.unmarshal(new StringReader(xml));
		}catch(JAXBException e){
			throw new XMLException("XML转JavaBean失败\n" + xml, e);
		}
	}

	/**
	 * JavaBean对象转为XML字符串
	 * @param object
	 * @return
	 */
	public static String  object2XML(Object object){
		try{
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, EnvConfig.charset);
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
	        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);// 是否省略xm头声明信息
	        StringWriter writer = new StringWriter();
			marshaller.marshal(object, writer);
			return writer.toString();
		}catch(JAXBException e){
			throw new XMLException("JavaBean转XML失败\n" + object, e);
		}
	}
}
