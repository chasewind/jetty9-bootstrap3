package com.belief.test;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 测试StringWriter
 * 
 * @author 于东伟
 *
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -7665349355341530843L;

	public String getDetailInfo() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		printStackTrace(pw);
		StringBuffer sb = sw.getBuffer();
		return sb.toString();
	}
}
