package com.regalman.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.regalman.hibernate.demo.entity.Student;

public class UpdateStudentDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// use the session object to save java object
		try {
			int studentId = 1;

			// begin transaction
			System.out.println("Starting transaction");

			// create session
			Session session = factory.getCurrentSession();
			session.beginTransaction();

			System.out.println("Getting student with id: " + studentId);

			Student myStudent = session.get(Student.class, studentId);

			System.out.println("Updating student...");
			myStudent.setFirstName("Retep");

			System.out.println("Updating email for all student");
			session.createQuery("Update Student set email = 'winner@skynet.com'").executeUpdate();

			// commit transaction
			System.out.println("Commit to the database");
			session.getTransaction().commit();

			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}
}
