package com.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestHttp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			Socket s=new Socket("localhost",8080);
			PrintWriter out=new PrintWriter(s.getOutputStream(),true);
			BufferedReader reader=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out.println("GET /servlet01/reg.html HTTP/1.1");
			out.println("Host: localhost");
			out.println("contentType:text/html");
			out.println();
			String str=null;
			while((str=reader.readLine())!=null){
				System.out.println(str);
			}
		
		
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
