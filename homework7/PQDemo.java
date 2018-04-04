

// Priority queue demonstration
// studentList has students in an arbitrary order
// Students are removed from the student list and inserted into a priority queue studentPQ
// A student object has id (String), name (String), and age (integer).
// The priority queue is a minimum-oriented priority queue 
// "age" is used as a key and a student with a smallest age is removed by removeMin() method

import java.util.ArrayList;
import java.util.Iterator;

// create a package and include all necessary files in it, including HeapAdaptablePriorityQueue
// import AdaptablePQEntry
// import <yourpackage name here>.HeapAdaptablePriorityQueue.AdaptablePQEntry;

public class PQDemo {

	public static void printStudentList(ArrayList<Student> sl){
		Iterator<Student> students = sl.iterator();
		while (students.hasNext()){
			System.out.println(students.next());
		}	
	}
	
	public static void main(String[] args) {
		HeapAdaptablePriorityQueue<Integer, Student> studentPQ = new HeapAdaptablePriorityQueue<>();
		ArrayList<Student> studentList = new ArrayList<Student>();
		AdaptablePQEntry<Integer, Student> studentEntry;
		
		studentList.add(new Student("U01", "John", 25));
		studentList.add(new Student("U02", "Susan", 15));
		studentList.add(new Student("U03", "Molly", 32));
		studentList.add(new Student("U04", "Adam", 56));
		studentList.add(new Student("U05", "Jake", 18));
		studentList.add(new Student("U06", "Kurt", 15));
		studentList.add(new Student("U07", "Kelsey", 27));
		studentList.add(new Student("U08", "Elizabeth", 19));
		studentList.add(new Student("U09", "Emma", 21));
		studentList.add(new Student("U10", "Benji", 25));

		// print all students in the student list
		System.out.println("Print students from the list");
		printStudentList(studentList);
		
		Student s;
		// remove students from student list and insert into priority queue		
		while (!studentList.isEmpty()){
			s = studentList.remove(0);
			studentEntry = (AdaptablePQEntry<Integer, Student>)studentPQ.insert(s.age, s);
		}
		
		// studentList is now empty
		System.out.println("Is student list empty? " + studentList.isEmpty());
		
		// print students from the priority queue as they are stored in the heap
		System.out.println("\nPrint students from the priority queue in the order they are stored in heap");
		for (int i=0; i<studentPQ.heap.size(); i++){
			studentEntry = (AdaptablePQEntry<Integer, Student>)studentPQ.heap.get(i);
			System.out.println(studentEntry.getValue());
		}
		
		// remove students from priority queue and print
		System.out.println("\nRemove and print students from the priority queue in non-decreasing order of age");
		while (!studentPQ.isEmpty()){
			studentEntry = (AdaptablePQEntry<Integer, Student>)studentPQ.removeMin();
			System.out.println(studentEntry.getValue());
		}
			
	}

}
