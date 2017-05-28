package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

/**
 * KPI- FPM - PZKS Course: Algorithms and Data Structures (2) Laboratory work 5
 * 
 * @author Olena Khomenko <br>
 *
 */
public class StudentReader {
	private String path;
	private CSVReader reader = null;

	public StudentReader(String fileName, String currenDir) {
		path = Paths.get(currenDir, fileName).toString();

	}

	public StudentReader(String fileName) {

		String currenDir = System.getProperty("user.dir") + File.separatorChar
				+ "data";
		path = Paths.get(currenDir, fileName).toString();

	}

	/**
	 * Reads the content of a csv file, checks data from the file and returns
	 * the array of students. If the file is empty returns empty list. Object of
	 * class Student is created by invoking method createStudent
	 * 
	 * @return list of Students
	 */
	public List<Student> read() {
		ArrayList<Optional<Student>> students = new ArrayList<>();
		ArrayList<Student> emptyList = new ArrayList<>();

		// read all lines from a file
		List<String[]> lines = readFile();

		// return empty list, if the list of lines is empty
		if (lines.isEmpty()) {
			return emptyList;
		}

		// otherwise, analyze line by line
		for (Iterator<String[]> it = lines.iterator(); it.hasNext();) {

			// create new Student from the information in a line
			Optional<Student> s = createStudent(it.next());

			// add student to the list
			students.add(s);
		}

		// return list of Students
		if (students.isEmpty()) {
			return emptyList;
		} else {
			return getStudents(students);
		}
	}

	private List<Student> getStudents(ArrayList<Optional<Student>> students) {
		return students.stream().filter(s -> s.isPresent() == true)
				.map(Optional::get).collect(Collectors.toList());
	}

	/**
	 * Checks correctness the information about student in this line. If checks
	 * for all elements in this line are successful, create and return the
	 * object of Student.
	 * 
	 * @param line
	 *            array of Strings represented data about student. If line is
	 *            null, return empty Student
	 * @return object of Student if all data about student are correct, empty
	 *         Student - otherwise
	 */
	private Optional<Student> createStudent(String[] line) {

		// return empty object of Student if number of string in a line don't
		// equals
		// the number of Student's fields
		if (line.length != Student.class.getDeclaredFields().length) {
			System.err.println("Error: the line  \"" + Arrays.toString(line)
					+ "\" not completed");
			return Optional.empty();

		} else {
			// check correctness information about student and fill
            // fields of Student object
            // TODO
            // for check each string in a line:
            // 1)invoking methods isValidXXX from class Student
            // Hint: before you pass string isValidXXX method, use String.trim
            // to omit leading and trailing whitespace
//            boolean isValidFirstName = Student.isValidName(line[0].trim());
//            boolean isValidSecondName = Student.isValidName(line[1].trim());
//            boolean isValidYear = Student.isValidYear(Integer.parseInt(line[2].trim()));
//            boolean isValidCard = Student.isValidCardNumber(line[3].trim());
//            boolean isValidGender = Student.isValidGender(line[4].trim());


            // if string contains invalid information don't set field and
			// immediately return Optional.empty()
//			if (!isValidCard || !isValidFirstName || !isValidSecondName || !isValidYear || !isValidGender) {
//				return Optional.empty();
//			}

			// 2) if all string contains valid information, set the field of
			// student
			// Hint: use methods Integer.parseInt/Double.parseDouble to convert
			// from String to int/double

            String lastName = line[0].trim();
            String firstName = line[1].trim();
            int year = Integer.parseInt(line[2].trim());
            int key = Integer.parseUnsignedInt(line[3].trim());
            String gender = line[4].trim();
            String residence = line[5].trim();

			// if all fields in a line contains valid information
			// return the object of Student

			return Optional.of(new Student(lastName , firstName , year , key , gender , residence));
		}

	}

	private List<String[]> readFile() {
		List<String[]> list = new ArrayList<>();
		try {
			reader = new CSVReader(new FileReader(path));
			list = reader.readAll();
		} catch (FileNotFoundException e) {
			System.out.println("Error: file  " + path + "   not found");
			return Collections.emptyList();
		} catch (IOException | SecurityException e) {
			System.err.println("Error:read file " + path + "   error");
			return Collections.emptyList();
		}

		try {
			reader.close();
		} catch (IOException e) {
			System.err.println("Error:close file " + path + "   error");
		}
		return list;
	}
}
