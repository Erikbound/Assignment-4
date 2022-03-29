import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {

	int length;
	LinkedList<CourseDBElement>[] table;
	
	public CourseDBManager() {
		this.length = 30;
		table = new LinkedList[length];
	}
	
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		int hashCode = crn % length;
		boolean canAdd = true;
		
		//If bucket is empty
		if (table[hashCode] == null) {
			table[hashCode] = new LinkedList<CourseDBElement>(); //Create LL
		}
		//Cycle through Linked List in this bucket to see if node to be added already exists
		//If so, exit method
		LinkedList<CourseDBElement> bucket = table[hashCode];
		for(int i = 0; i < bucket.size(); i++) {
			if (bucket.get(i).equals(element)) {
				canAdd = false;
				break;
			}
		}
		if (canAdd) {
			table[hashCode].add(element);
		}
	}

	@Override
	public CourseDBElement get(int crn) {
		int hashCode = crn % length;
		CourseDBElement result = null;
		
		LinkedList<CourseDBElement> bucket = table[hashCode];
		if (bucket != null) { //If the given CRN exists in the table
			//Cycle through the List until you find a CRN that matches the argument
			
			for(int i = 0; i < bucket.size(); i++) {
				if (bucket.get(i).getCRN() == crn) {
					result = table[hashCode].get(i);
				}
			}
		}
		
		return result;
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner scan = new Scanner(input);
		
		String course;
		int crn;
		int credits;
		String room;
		String instructor = "";
		
		while(scan.hasNextLine()) {
			course = scan.next();
			crn = Integer.parseInt(scan.next());
			credits = Integer.parseInt(scan.next());
			room = scan.next();
			instructor = instructor + " " + scan.next();
			
			add(course, crn, credits, room, instructor);
			scan.nextLine();
		}
		scan.close();
		
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0; i < length; i++) {
			if (table[i] != null) {
				list.add(table[i].get(0).toString());
			}
		}
		
		return list;
	}

}
