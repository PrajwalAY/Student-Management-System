package mainFile;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import customException.InvalidChoiceException;

public class StudentManagementSystemImpl implements StudentManagementSystem
{

	Scanner ip=new Scanner(System.in);
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("Project");
	EntityManager em=emf.createEntityManager();
	EntityTransaction et=em.getTransaction();
	
	public void addStudent() {		System.out.print("Enter student name: ");
		String name=ip.next();
		System.out.print("Enter student age: ");
		int age=ip.nextInt();
		System.out.print("Enter student marks: ");
		double marks=ip.nextDouble();
		Student s=new Student();
		s.setName(name);
		s.setAge(age);
		s.setMarks(marks);
		
		et.begin();
		em.persist(s);
		et.commit();
	}

	public void displayStudent() {
		System.out.print("Enter id to display student details: ");
		int id=ip.nextInt();
		et.begin();
		Query q=em.createQuery("select s from Student s where s.id=?1 ");
		q.setParameter(1, id);
		List<Student> l = q.getResultList();
		System.out.printf("%15s%15s%15s%15s","id","name","age","marks");
		System.out.println();
		System.out.println("=============================================================");
		for(Student student: l) {
			System.out.printf("%15s%15s%15s%15s",student.getId(),student.getName(),student.getAge(),student.getMarks());
		}
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		et.commit();
	}

	public void displayAllStudents() {
		et.begin();
		System.out.println("STUDENT DETAILS");
		System.out.println();
		Query q=em.createQuery("select s from Student s ");
		List<Student> l=q.getResultList();
		System.out.printf("%15s%15s%15s%15s","id","name","age","marks");
		System.out.println();
		System.out.println("=============================================================");
		for(Student student: l) {
			System.out.printf("%15s%15s%15s%15s",student.getId(),student.getName(),student.getAge(),student.getMarks());
			System.out.println();
			System.out.println("-------------------------------------------------------------");
		}
		et.commit();
	}

	public void removeStudent() {
		System.out.print("\nEnter student Id: ");
		int id=ip.nextInt();
		et.begin();
		Student s=em.find(Student.class, id);
		em.remove(s);
		et.commit();
		System.out.println("\nstudent with the id "+id+" is deleted");
		et.commit();
	}

	public void removeAllStudents() {
		et.begin();
		Query q=em.createQuery("select s from Student s ");
		List<Student> l=q.getResultList();
		for(Student student : l) {
			em.remove(student);
		}
		System.out.println("\nAll Students records from Database is Deleted");
		et.commit();
	}

	public void updateStudent() {
		System.out.print("\nEnter student Id: ");
		int id=ip.nextInt();
		Student s=em.find(Student.class, id);
		System.out.println("\n1.Update Name\n2.Update Age\n3.Update Marks\n");
		System.out.print("Enter choice: ");
		int choice=ip.nextInt();
		et.begin();
		switch(choice) {
			case 1:System.out.print("\nEnter name to update: ");
				   String name=ip.next();
				   s.setName(name);
				   System.out.println("Name updated successfully");
				   break;
			case 2:System.out.print("\nEnter age to update: ");
				   String age=ip.next();
				   s.setName(age);
				   System.out.println("Age updated successfully");
				   break;
			case 3:System.out.print("\nEnter marks to update: ");
				   String marks=ip.next();
				   s.setName(marks);
				   System.out.println("Marks updated successfully");
				   break;
			default:{
				try {
					new InvalidChoiceException("Invalid choice, Kindly enter valid choice!!!");
				}
				catch(InvalidChoiceException e) {
					e.getMessage();
				}
			}
		}
		et.commit();
	}

	public void countStudent() {
		et.begin();
		Query q=em.createQuery("select s from Student s ");
		List<Student> l=q.getResultList();
		System.out.println();
		System.out.println("Total number of students records -> "+l.size());
		et.commit();
	}

	public void sortStudent() {
		System.out.println("\n1.Sort Student by Name\n2.Sort Student by Age\n3.Sort Student by Marks\n");
		System.out.print("Enter choice: ");
		int choice=ip.nextInt();
		et.begin();
		switch(choice) {
		case 1: Query q=em.createQuery("select s from Student s order by s.name asc ");
			    List<Student> l=q.getResultList();
			    System.out.printf("%15s%15s%15s%15s","id","name","age","marks");
			    System.out.println();
			    System.out.println("=============================================================");
			    for(Student student: l) {
			    	
			    	System.out.printf("%15s%15s%15s%15s",student.getId(),student.getName(),student.getAge(),student.getMarks());
			    	System.out.println();
				    System.out.println("-------------------------------------------------------------");
				}
			    break;
		case 2: Query q1=em.createQuery("select s from Student s order by s.age asc ");
			    List<Student> l1=q1.getResultList();
			    System.out.printf("%15s%15s%15s%15s","id","name","age","marks");
			    System.out.println();
			    System.out.println("=============================================================");
			    for(Student student: l1) {
			    	
			    	System.out.printf("%15s%15s%15s%15s",student.getId(),student.getName(),student.getAge(),student.getMarks());
			    	System.out.println();
				    System.out.println("-------------------------------------------------------------");
				}
			    break;
		case 3: Query q2=em.createQuery("select s from Student s order by s.marks asc ");
			    List<Student> l2=q2.getResultList();
			    System.out.printf("%15s%15s%15s%15s","id","name","age","marks");
			    System.out.println();
			    System.out.println("=============================================================");
			    for(Student student: l2) {
			    	
			    	System.out.printf("%15s%15s%15s%15s",student.getId(),student.getName(),student.getAge(),student.getMarks());
			    	System.out.println();
				    System.out.println("-------------------------------------------------------------");
				}
			    break;
		default:
			try {
				new InvalidChoiceException("Invalid choice, Kindly enter valid choice!!!");
			}
			catch(InvalidChoiceException e) {
				e.getMessage();
			}
		}
		et.commit();
	}

	public void getStudentWithHighestMarks() {
		et.begin();
		Query q=em.createQuery("select s from Student s order by s.marks asc ");
		List<Student> l=q.getResultList();
		Student s=l.get(l.size()-1);
		System.out.println("\nStudent With the Highest Marks is "+s.getName());
		System.out.println("\nMarks obtained by "+s.getName()+" is "+s.getMarks());
		et.commit();
	}

	public void getStudentWithLowestMarks() {		et.begin();
		Query q = em.createQuery("select s from Student s order by s.marks asc ");
		List<Student> l = q.getResultList();
		Student s=l.get(0);
		System.out.println("\nStudent With the lowest Marks is "+s.getName());
		System.out.println("\nMarks obtained by "+s.getName()+" is "+s.getMarks());
		et.commit();
	}
}