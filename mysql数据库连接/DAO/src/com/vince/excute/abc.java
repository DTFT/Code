package com.vince.excute;

import java.sql.SQLException;

import com.vince.dao.PersonDAO;
import com.vince.dao.impl.PersonDaoImpl;

public class abc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PersonDaoImpl pd=new PersonDaoImpl();
		try {
			System.out.println(pd.findAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
