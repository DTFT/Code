package com.vince.test;

import java.sql.SQLException;
import java.util.List;

import com.vince.dao.PersonDao;
import com.vince.dao.impl.PersonDaoImpl;
import com.vince.domain.Person;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersonDao dao = new PersonDaoImpl();
		try {
			dao.add(new Person("小黑",19,"是一个很黑很黑的人"));
//			Person p = dao.findById(2);
//			List<Person> persons = dao.findAll();
//			System.out.println(persons);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}













