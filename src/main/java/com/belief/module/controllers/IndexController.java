package com.belief.module.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.belief.model.Product;
import com.belief.utils.GridPage;
import com.belief.utils.PageInfo;

@Controller
public class IndexController {

	private static List<Product> productList = new ArrayList<Product>(800);
	static {
		for (int i = 0; i < 809; i++) {
			Product product = new Product();
			product.setId(i);
			product.setName(RandomStringUtils.randomAlphanumeric(6));
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
	public PageInfo<Product> getJsonData(int offset, int limit, String search) {
		PageInfo<Product> pageInfo = new PageInfo<Product>();
		List<Product> list = new ArrayList<Product>();
		for (int i = offset; i < offset + limit; i++) {
			list.add(productList.get(i));
		}
		pageInfo.setTotal(productList.size());
		pageInfo.setRows(list);
		return pageInfo;
	}

	@RequestMapping("/gridPage")
	public String gridPage() {
		return "gridPage";
	}
	@ResponseBody
	@RequestMapping("/getGridData")
	public GridPage<Product> getGridData(int page, int rows) {
		int listSize = productList.size();
		GridPage<Product> gridPage = new GridPage<Product>();
		List<Product> list = new ArrayList<Product>();
		int start = page * rows;
		int end = start + rows;
		if (end > listSize) {
			end = listSize;
		}
		for (int i = start; i < end; i++) {
			list.add(productList.get(i));
		}

		gridPage.setTotal(listSize / rows == 0 ? listSize / rows : listSize / rows + 1);
		gridPage.setRows(list);
		gridPage.setPage(page);
		gridPage.setRecords(listSize);
		return gridPage;
	}

}
