package lab8;

import java.io.File;
import java.util.List;

public class Task1Main {
	// name of file which stores data about students
	private static String fileName = "students.csv";

	// path to the current directory that contains directory "data"
	private static String currenDir = System.getProperty("user.dir")
			+ File.separatorChar + "data";

	public static void main(String[] args) {
		// create object of class StudentReader and invoke method read
		// TODO
		StudentReader reader = new StudentReader("students.csv");
		List<Student> studentList = reader.read();

		StudentDictionary<Student> dict = new BSTree<>();


		// TEST dict.put
		// fill dictionary:insert students to the dictionary
		// TODO

		for(int i = 0 ; i< studentList.size() ; i++)
		{
			Student s = dict.put(studentList.get(i).getkey() , studentList.get(i));
		}


		// TEST dict.print dictionary
		// output the content of a dictionary
		// TODO

		System.out.println("_________________________________________");
		System.out.println("Initial inserion from file : ");
		dict.printDictionary();

		// TEST dict.put
		// insert students with non-unique key
		// TODO

		Student s = dict.put(54345 , new Student("Herasymov" , "Artem" , 1 , 54345 , "male" , "RentVilla"));

		System.out.println("_________________________________________");
		System.out.println("Dictionary after adding non-unique record : ");
		dict.printDictionary();

		// TEST: dict.contains
		// TODO

		System.out.println("_________________________________________");
		System.out.println("Checking whether record with key '" + 32323 + "' is contained in the dictionary : ");

		if(dict.containsKey(54345))
		{
			System.out.println("Result : Contains.");
		}
		else
		{
			System.out.println("Result : Not found");
		}

		// TEST: dict.get
		// TODO

		System.out.println("_________________________________________");
		System.out.println("Getting the  record by the key : " + 32323);
		System.out.println("|" + dict.get(32323).secondName + "|" + dict.get(32323).firstName + "|" + dict.get(32323).year + "|" + dict.get(32323).gender
		+ "|" + dict.get(32323).residence + "|");

		// TEST: dict.remove
		// TODO

		System.out.println("_________________________________________");
		System.out.println("Remove from dictionary all records that contain females who live in the dormitory(one child case)");
		dict.remove("female" , "dormitory");
		dict.printDictionary();

		System.out.println("_________________________________________");
		System.out.println("Testing remove on a leaf node");
		dict.remove(76767);
		dict.printDictionary();

		System.out.println("_________________________________________");
		System.out.println("Testing remove on a node with two children");

		dict.remove(23233);

		dict.printDictionary();


		System.out.println("_________________________________________");
		System.out.println("Testing remove on a root node");

			System.out.println("Before : ");
		dict.printDictionary();

		dict.remove(54345);

		System.out.println("After : ");
		dict.printDictionary();
	}

}

