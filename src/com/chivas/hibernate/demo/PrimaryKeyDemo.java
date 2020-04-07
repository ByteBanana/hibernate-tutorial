package com.chivas.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chivas.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save java object
		try {

			// Create student object
			System.out.println("Createing new student object");
			Student student1 = new Student("John", "Connor", "John.Connor@skynet.com");
			Student student2 = new Student("Sarah", "Connor", "Sarah.Connor@skynet.com");
			Student student3 = new Student("Kyle", "Reese", "Kyle.Reese@skynet.com");

			// begin transaction
			System.out.println("Starting transaction");
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student object");
			session.save(student1);
			session.save(student2);
			session.save(student3);

			// commit transaction
			System.out.println("Commit to the database");
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}
}
