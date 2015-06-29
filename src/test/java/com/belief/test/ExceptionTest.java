package com.belief.test;

import org.junit.Test;

/**
 * 这里Junit4的输出和main的输出是不一样的
 * 
 * @author 于东伟
 *
 */
public class ExceptionTest {
	public static void main(String[] args) {
		test();
	}

	@Test
	public static void test() {
		try {
			throw new ServiceException();
		} catch (ServiceException e) {
			e.printStackTrace();
			System.out.println(e.getDetailInfo());
		}
	}
}
