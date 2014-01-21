package org.leo.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings("rawtypes")
public class JsonUtils {
	private static final transient Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	public static <T> T readJsonToObject(Class<T> clazz, String json) {
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			T result = mapper.readValue(json, clazz);
			return result;
		} catch (Exception e) {
			logger.error(json, e);
		}
		return null;
	}
	
	public static <T> T readJsonToObject(Class<T> clazz, byte[] bytes) {
		if (bytes.length == 0)
			return null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			T result = mapper.readValue(bytes, clazz);
			return result;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	public static <T> T readJsonToObject(TypeReference<T> type, String json){
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		try {
			T result = mapper.readValue(json, type);
			return result;
		} catch (Exception e) {
			logger.error(json, e);
		}
		return null;
	}
	public static <T> List<T> readJsonToObjectList(Class<T> clazz, String json) {
		if (StringUtils.isBlank(json))
			return null;
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
			List<T> result = mapper.readValue(json, type);
			return result;
		} catch (Exception e) {
			logger.error(json, e);
		}
		return null;
	}

	public static Map readJsonToMap(String json) {
		if (StringUtils.isBlank(json))
			return new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map result = mapper.readValue(json, Map.class);
			if (result == null) result = new HashMap();
			return result;
		} catch (Exception e) {
			logger.error(json, e);
			return new HashMap();
		}

	}
	public static String writeObjectToJson(Object object) {
		return writeObjectToJson(object, false);
	}
	
	@SuppressWarnings("deprecation")
	public static String writeObjectToJson(Object object, boolean excludeNull) {
		if (object == null)	return null;
		if(object instanceof Map){
			try{((Map) object).remove(null);}catch(Exception e){}
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
		if(excludeNull) {
			mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		}

		try {
			CustomSerializerFactory sf = new CustomSerializerFactory();
			mapper.setSerializerFactory(sf);
			String data = mapper.writeValueAsString(object);
			return data;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	public static byte[] writeObjectToBytes(Object object) {
		if (object == null)	return null;
		if(object instanceof Map){
			try{((Map) object).remove(null);}catch(Exception e){}
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);

		try {
			CustomSerializerFactory sf = new CustomSerializerFactory();
			mapper.setSerializerFactory(sf);
			byte[] data = mapper.writeValueAsBytes(object);
			return data;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	public static String writeMapToJson(Map<String, String> dataMap){
		if(dataMap==null) return null;
		if(dataMap instanceof HashMap){
			try{dataMap.remove(null);}catch(Exception e){}
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
		try {
			String data = mapper.writeValueAsString(dataMap);
			return data;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	public static String addJsonKeyValue(String json, String key, String value){
		Map info = readJsonToMap(json);
		info.put(key, value);
		return writeMapToJson(info);
	}
	@SuppressWarnings("unchecked")
	public static String removeJsonKeyValue(String json, String key){
		Map info = readJsonToMap(json);
		info.remove(key);
		return writeMapToJson(info);
	}
	@SuppressWarnings("unchecked")
	public static String getJsonValueByKey(String json, String key){
		Map<String, String> info = readJsonToMap(json);
		return info.get(key);
	}

}

