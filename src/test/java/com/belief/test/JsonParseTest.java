package com.belief.test;

import org.junit.Test;

import com.belief.utils.JsonUtils;

public class JsonParseTest {

	@Test
	public void testJson() {
		String json = "{key:\"xxxxxxxxx\",suffix:\"yyyyyyyyyyyy\"}";
		JsonUtils.json2Object(json, ImgForm.class);
	}
}
