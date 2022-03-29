
public class CourseDBElement implements Comparable{

	String CourseID;
	int CRN;
	int credits;
	String roomNumber;
	String instructor;

	public CourseDBElement() {
		
	}
	
	public CourseDBElement(String CourseID, int CRN, int credits, String roomNumber, String instructor){
		this.CourseID = CourseID;
		this.CRN = CRN;
		this.credits = credits;
		this.roomNumber = roomNumber;
		this.instructor = instructor;
	}
	
	@Override
	public int compareTo(Object o) {
		
		return 0;
	}

	public void setID(String CourseID) {
		this.CourseID = CourseID;
	}
	public String getID() {
		return CourseID;
	}
	

	public void setCRN(int CRN) {
		this.CRN = CRN;
	}
	public int getCRN() {
		return CRN;
	}
	

	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getCredits() {
		return credits;
	}


	public void setRoomNum(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public String getRoomNum() {
		return roomNumber;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getInstructor() {
		return instructor;
	}

	@Override
	public String toString() {
		return "\nCourse:" + CourseID + " CRN:" + CRN + " Credits:" + credits + " Instructor:" + instructor + " Room:" + roomNumber;
	}
	
	
	
}
