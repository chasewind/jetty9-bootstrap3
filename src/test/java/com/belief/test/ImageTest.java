package com.belief.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.belief.utils.ImageCutVO;
import com.belief.utils.ImageUtils;

public class ImageTest {
    public static String path = "C:\\Users\\ThinkPad\\Desktop\\goagent\\";

    public static void main(String[] args) throws IOException {
        // File file1 = new File(path, "刘欣欣.jpg");
        // File file2 = new File(path, "60.jpg");
        // ImageUtils.fillPic(file1, file2, 80, 45, 202, 278, path);
        // resizePic(file1, file2, 80, 45, 278, 202);
        // reSizeTFile(file1, 278, 202);
        // File file3 = new File(path, "resize.png");
        // File file4 = new File(path, "SNH48.jpg");
        // resizePicNoScale(file3, file2, 80, 45, 278, 202);
        // resizePicNoScale(file3, file4, 80, 45, 278, 202);
        File file5 = new File(path, "meilv10.jpg");
        reSizeTFile(file5, 120, 180);
    }

    public static void reSizeTFile(File smallImg, int resizeHeight, int resizeWidth)
            throws IOException {
        FileInputStream smallStream = new FileInputStream(smallImg);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = smallStream.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        smallStream.close();
        InputStream inStream = new ByteArrayInputStream(baos.toByteArray());
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 1024)) > 0) {
            byteStream.write(buff, 0, rc);
        }
        byte[] bytes = byteStream.toByteArray();

        // 根据宽度和高度缩放用户图片，得到流
        ImageUtils.reSizeTFile(bytes, resizeHeight, resizeWidth, "jpg", path);
    }

    public static void resizePic(File smallImg, File bigImg, int leftX, int leftY,
            int resizeHeight, int resizeWidth) throws IOException {
        FileInputStream smallStream = new FileInputStream(smallImg);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = smallStream.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        smallStream.close();
        InputStream inStream = new ByteArrayInputStream(baos.toByteArray());
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 1024)) > 0) {
            byteStream.write(buff, 0, rc);
        }
        byte[] bytes = byteStream.toByteArray();

        // 根据宽度和高度缩放用户图片，得到流
        byte[] resizeBytes = ImageUtils.reSize(bytes, resizeHeight, resizeWidth, "jpg");
        ByteArrayInputStream resizeStream = new ByteArrayInputStream(resizeBytes);

        InputStream bigStream = new FileInputStream(bigImg);

        ImageCutVO imageCutVO = new ImageCutVO();
        imageCutVO.setFormat("jpg");
        imageCutVO.setLeftX(leftX);
        imageCutVO.setLeftY(leftY);
        imageCutVO.setWidth(resizeWidth);
        imageCutVO.setHeight(resizeHeight);
        ImageUtils.fillPicInEllipseFile(resizeStream, bigStream, imageCutVO, path);

    }

    public static void resizePicNoScale(File smallImg, File bigImg, int leftX, int leftY,
            int resizeHeight, int resizeWidth) throws IOException {
        FileInputStream smallStream = new FileInputStream(smallImg);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = smallStream.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        smallStream.close();
        InputStream inStream = new ByteArrayInputStream(baos.toByteArray());

        InputStream bigStream = new FileInputStream(bigImg);

        ImageCutVO imageCutVO = new ImageCutVO();
        imageCutVO.setFormat("jpg");
        imageCutVO.setLeftX(leftX);
        imageCutVO.setLeftY(leftY);
        imageCutVO.setWidth(resizeWidth);
        imageCutVO.setHeight(resizeHeight);
        ImageUtils.fillPicInEllipseFile(inStream, bigStream, imageCutVO, path);

    }

}
