package com.belief.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static Logger log = LoggerFactory.getLogger(JsonUtils.class);

	public static <T> T json2Object(String json, Class<T> clazz) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T obj = null;
		try {
			obj = (T) objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return obj;
	}

	/**
	 * list 转换成json
	 * 
	 * @param
	 * @return
	 * @throws IOException
	 */
	public static String object2Json(Object object) throws IOException {
		if (object == null) {
			return "";
		}
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
