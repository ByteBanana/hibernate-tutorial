package com.regalman.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.regalman.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save java object
		// Create student object
		System.out.println("Createing new student object");
		Student t1Model = new Student("T1", "Sentry", "oldmodel@skynet.com");
		Student t2Model = new Student("T2", "Sentry", "oldmodel@skynet.com");
		Student t3Model = new Student("T3", "Sentry", "oldmodel@skynet.com");

		// begin transaction
		System.out.println("Starting transaction");
		session.beginTransaction();

		// save the student object
		System.out.println("Saving all the students object");
		session.save(t1Model);
		session.save(t2Model);
		session.save(t3Model);
		session.getTransaction().commit();
		System.out.println("Done!");

		try {

			session = factory.getCurrentSession();

			// begin transaction
			System.out.println("Starting transaction");
			session.beginTransaction();
			// Find out the student's id: primary key
			int studentId = 6;
			System.out.println("Deleting T1 model");
			Student t1 = session.get(Student.class, studentId);
			if (t1 != null) {
				session.delete(t1);
			}

			System.out.println("Delte all 'Sentry'");
			session.createQuery("delete from Student where last_name = 'Sentry'").executeUpdate();

			// commit the trasaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}
}
