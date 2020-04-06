package com.chivas.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chivas.hibernate.demo.entity.Student;

public class ReadStudentDemo {
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
			Student student = new Student("John", "Jackson", "saharat.paynok@gmail.com");

			// begin transaction
			System.out.println("Starting transaction");
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student object");
			session.save(student);

			// commit transaction
			System.out.println("Commit to the database");
			session.getTransaction().commit();

			// Find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + student.getId());

			// Get new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();

			// retrieve student based on the id: primary key
			System.out.println("Getting student with id: " + student.getId());
			Student resultStd = session.get(Student.class, student.getId());
			System.out.println(resultStd.toString());

			// commit the trasaction
			session.getTransaction().commit();
			
			System.out.println("Done!");

		} finally {
			factory.close();
		}

	}
}
