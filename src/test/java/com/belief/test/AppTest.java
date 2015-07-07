package com.belief.test;

public class AppTest {
	public static void main(String[] args) {
		String[] array = "ad-products|http://img.kcyouli.com|iPhkRNUl5GwPOIs-yGElVYb2PrWsBqWIqmUZMpsv|p4_noUMIvnfWxxzhkUwMWsrwd-9hB0u2CFbQalIS".split("\\|");
		for (String str : array) {
			System.out.println(str);
		}
		array = "kk-resource|http://kk-resource.qiniudn.com|pvq0yhV90DZRWntM0jZLI_KkSgpwT2rWg6NUDNkT|iGExckccKW6zoh67BKq0etiYB2bZ6gPS0_xNAKEX".split("\\|");
		for (String str : array) {
			System.out.println(str);
		}
		// ////////////////////
		System.out.println("-------------------");
		String fileName = "hello.txt";
		int idx = fileName.lastIndexOf(".");
		// 获取后缀
		String suffix = fileName.substring((idx + 1));
		System.out.println(suffix);

	}
}
