package com.chivas.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chivas.hibernate.demo.entity.Student;

public class CreateStudentDemo {
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
			Student student = new Student("Peter", "Silberman", "Peter.Silberman@skynet.com");

			// begin transaction
			System.out.println("Starting transaction");
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student object");
			session.save(student);

			// commit transaction
			System.out.println("Commit to the database");
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}
}
