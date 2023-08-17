package customSorting;

import java.util.Comparator;

import mainFile.Student;

public class SortStudentByName implements Comparator<Student>{

	public int compare(Student s1, Student s2) {
		return s1.getName().compareTo(s2.getName());
	}
}