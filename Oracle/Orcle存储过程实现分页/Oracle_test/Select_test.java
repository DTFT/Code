package com.ora;
//oracle emp表 输入部门号 查询职员信息


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;

public class Select_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		scan();
	}

	private static void scan() {
		// TODO Auto-generated method stub
		System.out.println("请输入部门编号,输入0 退出");
		int no = 0;
		try {
		  InputStreamReader isr=new InputStreamReader(System.in);
		  BufferedReader br =new BufferedReader(isr);
			no = Integer.parseInt(br.readLine());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		 if(no==10||no==20||no==30){
		show(no);
		 }else if(no==0){
			 System.exit(0);
		 }else{
			 System.out.println("部门编号错误，请重新输入");
			 scan();
		 }
	}

	private static void show(int i) {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection ct=
					DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","scott","tiger");
			CallableStatement cs=ct.prepareCall("{call demo_1(?,?)}");		
			cs.setInt(1, i);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs=(ResultSet) cs.getObject(2);
			
			while(rs.next()){
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
