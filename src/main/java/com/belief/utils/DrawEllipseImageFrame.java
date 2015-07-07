package com.belief.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawEllipseImageFrame extends JFrame {
	private Image img = null; // 声明图像对象
	private EllipseImagePanel imagePanel = null; // 声明图像面板对象
    public static void main(String args[]) {
        DrawEllipseImageFrame frame = new DrawEllipseImageFrame();
        frame.setVisible(true);
    }
    public DrawEllipseImageFrame() {
        super();
		URL imgUrl = DrawEllipseImageFrame.class.getResource("/image.jpg");// 获取图片资源的路径
		img = Toolkit.getDefaultToolkit().getImage(imgUrl); // 获取图像资源
		imagePanel = new EllipseImagePanel(); // 创建图像面板对象
		this.setBounds(200, 160, 316, 237); // 设置窗体大小和位置
		this.add(imagePanel); // 在窗体上添加图像面板对象
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体关闭模式
		this.setTitle("以椭圆形显示图像"); // 设置窗体标题
    }

	// 创建面板类
    class EllipseImagePanel extends JPanel {
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
			g2.drawImage(img, 20, 20, 260, 160, this);// 绘制图像
			Rectangle2D.Float rectangle = new Rectangle2D.Float(0, 0, getWidth(), getHeight());// 创建矩形对象
			Ellipse2D.Float ellipse = new Ellipse2D.Float(20, 20, 260, 160);// 创建椭圆形对象
			Area area1 = new Area(rectangle); // 创建区域矩形
			Area area2 = new Area(ellipse); // 创建区域椭圆
			area1.subtract(area2);// 两个区域形状进行减运算
			g2.setColor(getBackground());// 设置绘图上下文的颜色为面板的背景颜色
			g2.fill(area1); // 绘制减运算后的区域形状
        }
    }
}
