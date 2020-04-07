package com.chivas.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.chivas.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		// use the session object to save java object
		try {

			// begin transaction
			System.out.println("Starting transaction");
			session.beginTransaction();

			// Query students
			System.out.println("\n## All student");
			List<Student> studentList = session.createQuery("from Student", Student.class).getResultList();

			displayStudents(studentList);

			// Query student last name is 'Connor'
			System.out.println("\n## Student who last name is connor");
			studentList = session.createQuery("from Student where lower(last_name)='connor'", Student.class)
					.getResultList();

			// dislay the student
			displayStudents(studentList);

			// Query student name is 'John Connor'
			System.out.println("\n## Student who name is 'John Connor'");
			studentList = session
					.createQuery("from Student where lower(first_name)='john' and lower(last_name)='connor'",
							Student.class)
					.getResultList();
			displayStudents(studentList);

			System.out.println("\n## Student who name start with 'K' or 'k");
			studentList = session.createQuery("from Student where first_name Like 'K%'").getResultList();
			displayStudents(studentList);

			// commit transaction
			System.out.println("Commit to the database");
			session.getTransaction().commit();

		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> studentList) {
		for (Student student : studentList) {
			System.out.println(student);
		}
	}
}
