package com.belief.test;

public class AppTest {
    public static void main(String[] args) {
        String[] array =
                "ad-products|http://img.kcyouli.com|iPhkRNUl5GwPOIs-yGElVYb2PrWsBqWIqmUZMpsv|p4_noUMIvnfWxxzhkUwMWsrwd-9hB0u2CFbQalIS"
                        .split("\\|");
        for (String str : array) {
            System.out.println(str);
        }
    }
}
