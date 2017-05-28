package lab8;

/**
 * KPI- FPM - PZKS Course: Algorithms and Data Structures (2) Laboratory work 5
 * 
 * @author Olena Khomenko <br>
 *         Represents information about student: its name and number of course <br>
 *         This class is a sample how to define class, fields and methods
 * 
 *         Rewrite this class and its methods <br>
 *         Choose information to be saved in a class from lab manuals (table 1,
 *         col.2).<br>
 * 
 *         Write methods setXXX to set specified value to the field XXX. <br>
 * 
 *         Write method print to output information about student (values of the
 *         fields) in formatted string. <br>
 * 
 *         Write static methods boolean isValidXXX to check whether specified
 *         string could set (or convert and set) to the field XXX
 *
 */

public class Student {


	// TODO
	// add fields according to your variant
	String secondName;
	String firstName;
	int year;
	private int cardNumber;
	String gender;
	String residence;


	public Student(String secondName , String firstName , int year , int cardNumber , String gender , String residence) {
		this.secondName = secondName;
		this.firstName = firstName;
		this.year = year;
		this.cardNumber = cardNumber;
		this.gender = gender;
		this.residence = residence;
	}

	public int getkey() {
		return cardNumber;
	}

	public void setKey(int key)
    {
        this.cardNumber = key;
    }

	@Override
	public String toString() {
		return String.format("%-10d|  ", cardNumber);
	}

	/**
	 * Determines if the specified string is valid card number: contains only
	 * one digit character
	 * 
	 * @param number
	 *            the string to be tested
	 * @return true if the specified string is a valid card number, false
	 *         otherwise.
	 */
	public static boolean isValidCardNumber(String number) {
		char[] chArray = number.toCharArray();
		for (char ch : chArray) {
			if (!Character.isDigit(ch)) {
				return false;
			}
		}
		return true;
	}

	public static boolean isValidName(String name)
	{
		for(int i = 0 ; i < name.length() ; i++)
		{
			if(!Character.isAlphabetic(name.charAt(i)))
			{
				return false;
			}
		}

		return true;
	}

	public static boolean isValidYear(int year)
	{
		if(year < 1 || year > 6)
		{
			return false;
		}
		return true;
	}

	public static boolean isValidGender(String gender)
	{
		if(gender != "male" && gender != "female")
		{
			return false;
		}

		return true;
	}



}
