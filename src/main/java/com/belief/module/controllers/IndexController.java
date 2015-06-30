package com.belief.module.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belief.model.Product;
import com.belief.utils.PageInfo;

@Controller
public class IndexController {

    private static List<Product> productList = new ArrayList<Product>(800);
    static {
        for (int i = 0; i < 800; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName(RandomStringUtils.randomAlphanumeric(6));
            // product.setName(RandomUtils.nextInt(2000, 8000) + "");
            product.setPrice(RandomUtils.nextDouble(1.0, 100.0) + "");
            productList.add(product);
        }
    }

    @RequestMapping("/index")
    public String index() {
        return "ace2";
    }

    @RequestMapping("/jsonTest")
    public String json() {
        return "jsonTest";
    }

    @ResponseBody
    @RequestMapping("/getJsonData")
    public PageInfo<Product> getJsonData(int offset, int limit) {
        PageInfo<Product> pageInfo = new PageInfo<Product>();
        List<Product> list = new ArrayList<Product>();
        for (int i = offset; i < offset + limit; i++) {
            list.add(productList.get(i));
        }
        pageInfo.setTotal(productList.size());
        pageInfo.setRows(list);
        return pageInfo;
    }
}
