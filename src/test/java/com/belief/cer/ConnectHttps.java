package com.belief.cer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class ConnectHttps {
	static String KEYSTORE_FILE = "F:/authen/yitao.p12";
	static String KEYSTORE_PASSWORD = "admin123";
	static String TRUST_FILE = "F:/authen/server.keystore";
	static String TRUST_PASSWORD = "chaseecho";

	public static void main(String[] args) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(KEYSTORE_FILE));
		try {
			keyStore.load(instream, KEYSTORE_PASSWORD.toCharArray());
		} finally {
			instream.close();
		}
		SSLSocketFactory ssf = null;
		SSLContext ctx = SSLContext.getInstance("TLS");
		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		kmf.init(keyStore, KEYSTORE_PASSWORD.toCharArray());
		KeyStore tks = KeyStore.getInstance("JKS");
		tks.load(new FileInputStream(TRUST_FILE), TRUST_PASSWORD.toCharArray());
		tmf.init(tks);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
		System.out.println("load keystore success.");
		ssf = ctx.getSocketFactory();
		SSLSocket socket = (SSLSocket) ssf.createSocket("127.0.0.1", 8443);
		System.out.println("create socket success.");
		// handshake
		socket.startHandshake();
		System.out.println("handshake success.");

		String path = "/taxi-restapi/sys/config";
		// 获取tomcat首页
		// String path = "/";
		OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream());
		BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
		bufferedWriter.write("GET " + path + " HTTP/1.1\r\n");
		bufferedWriter.write("Host: " + "127.0.0.1" + "\r\n");
		bufferedWriter.write("\r\n");
		bufferedWriter.flush();
		BufferedInputStream streamReader = new BufferedInputStream(socket.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamReader, "utf-8"));
		String line = null;
		System.out.println("--------------------");
		System.out.println("auth?" + socket.getNeedClientAuth());

		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		bufferedReader.close();
		bufferedWriter.close();
		socket.close();
		System.exit(0);
	}

	public void sendPost(SSLSocket socket) throws IOException {
		String path = "/taxi-restapi/sys/config";
		String data = URLEncoder.encode("name", "utf-8") + "=" + URLEncoder.encode("gloomyfish", "utf-8") + "&" + URLEncoder.encode("age", "utf-8") + "=" + URLEncoder.encode("32", "utf-8");
		// String data = "name=zhigang_jia";
		SocketAddress dest = new InetSocketAddress("127.0.0.1", 8443);
		socket.connect(dest);
		OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream(), "utf-8");

		BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);

		bufferedWriter.write("POST " + path + " HTTP/1.1\r\n");
		bufferedWriter.write("Host: " + 8443 + "\r\n");
		bufferedWriter.write("Content-Length: " + data.length() + "\r\n");
		bufferedWriter.write("Content-Type: application/x-www-form-urlencoded\r\n");
		bufferedWriter.write("\r\n");
		bufferedWriter.write(data);
		bufferedWriter.flush();
		bufferedWriter.write("\r\n");
		bufferedWriter.flush();

		BufferedInputStream streamReader = new BufferedInputStream(socket.getInputStream());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(streamReader, "utf-8"));
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		bufferedReader.close();
		bufferedWriter.close();
		socket.close();
	}
}
