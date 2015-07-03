package com.belief.utils;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GridPage<T> {
	public final static Logger logger = LoggerFactory.getLogger(GridPage.class);
	/** The current page number */
	private long page = 1;
	/** Total number of pages */
	private long total = 1;
	/** Total number of records */
	private long records = 1;
	/** The actual data */
	private List<T> rows = new ArrayList<T>();

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}