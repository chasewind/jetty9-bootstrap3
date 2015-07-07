package com.belief.test;

import java.io.File;
import java.io.IOException;

import com.belief.utils.ImageUtils;

public class ImageTest {
	public static String path = "C:\\Users\\ThinkPad\\Desktop\\goagent\\";

	public static void main(String[] args) throws IOException {
		File file1 = new File(path, "刘欣欣.jpg");
		File file2 = new File(path, "60.jpg");
		// mergeTwoImg(file1, file2, 50, 45, 258, 269);
		ImageUtils.fillPic(file1, file2, 80, 45, 202, 278, path);
	}



	 

}