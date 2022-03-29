import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class CourseDBStructure implements CourseDBStructureInterface{

	int length;
	double load = 1.5;
	LinkedList<CourseDBElement>[] table;
	

	public CourseDBStructure(int length) {
		this.length = (int) Math.round(length / load);
		this.length = (int) (this.length * load);
		table = new LinkedList[this.length];
	}

	public CourseDBStructure(String test, int length) {
		this.length = length;
		table = new LinkedList[length];
	}
	
	@Override
	public void add(CourseDBElement element) {
		int crn = element.getCRN();
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
	public CourseDBElement get(int crn) throws IOException {
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
		} else {
			throw new IOException();
		}
		
		return result;
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> list = new ArrayList<String>();
		
		for(int i = 0; i < length; i++) {
			if (table[i] != null) {
				list.add(table[i].toString());
			}
		}
		
		return list;
	}

	@Override
	public int getTableSize() {
		// TODO Auto-generated method stub
		return length;
	}
	
}
