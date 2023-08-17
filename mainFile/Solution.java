package mainFile;

import java.util.Scanner;

import customException.InvalidChoiceException;

public class Solution {
	public static void main(String[] args) {
		Scanner ip=new Scanner(System.in);
		StudentManagementSystemImpl std=new StudentManagementSystemImpl();
		
		System.out.println("\n------WELCOME TO THE STUDENT DATABASE------");
		while(true) {
			System.out.println("\n1.Add Student\n2.Display Student\n3.Display All Students\n4.Remove Student\n5.Remove All Student\n6.Update Student\n7.Count Student");
			System.out.println("8.Sort Student\n9.Get Student With Highest Marks\n10.Get Student With Lowest Marks\n11.Exit");
			System.out.print("\nEnter Choice: ");
			int choice=ip.nextInt();
			
			switch(choice) {
				case 1:
					std.addStudent();
					break;
				case 2:
					std.displayStudent();
					break;
				case 3:
					std.displayAllStudents();
					break;
				case 4:
					std.removeStudent();
					break;
				case 5:
					std.removeAllStudents();
					break;
				case 6:
					std.updateStudent();
					break;
				case 7:
					std.countStudent();
					break;
				case 8:
					std.sortStudent();
					break;
				case 9:
					std.getStudentWithHighestMarks();
					break;
				case 10:
					std.getStudentWithLowestMarks();
					break;
				case 11:System.out.println("Thank you!!!");
					System.exit(0);
				default:
					try {
						new InvalidChoiceException("Invalid choice, Kindly enter valid choice!!!");
					}
					catch(InvalidChoiceException e) {
						e.getMessage();
					}
			}
		}
	}
}