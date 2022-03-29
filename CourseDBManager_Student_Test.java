import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * 
 */

/**
 * @author erikb
 *
 */
public class CourseDBManager_Student_Test {
	private CourseDBManagerInterface dataMgr;
	
	@Before
	public void setUp() throws Exception{
		 dataMgr = new CourseDBManager();
	}
	
	@After
	public void tearDown() throws Exception {
		dataMgr = null;
	}
	
	
	@Test
	public void testAddToDB() {
		try {
			dataMgr.add("MATH180",10200,4,"SC100","Mr. Egg");
			dataMgr.add("ENGL104",12233,3,"SC110","Ms. Beans");
			dataMgr.add("ENGL104",12233,3,"SC110","Ms. Beans");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}

	@Test
	public void testGet() {
		try {
			dataMgr.add("MATH180",10200,4,"SC100","Mr. Egg");
			assertEquals(10200, dataMgr.get(10200).getCRN());
			dataMgr.add("ASTR201",11222,3,"SC250","Mr. Guy");
			dataMgr.add("ENGL104",12233,3,"SC110","Ms. Beans");
			assertEquals(12233, dataMgr.get(12233).getCRN());

			//Shares a node with Ms. Beans
			dataMgr.add("ENGL104",12263,3,"SC111","Ms. Fetuccini");
			assertEquals(12263, dataMgr.get(12263).getCRN());
			
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}

	@Test
	public void testShowAll() {
		dataMgr.add("MATH180",10200,4,"SC100","Mr. Egg");
		dataMgr.add("ENGL104",12233,3,"SC110","Ms. Beans");
		dataMgr.add("ASTR201",11222,3,"SC250","Mr. Guy");
		ArrayList<String> list = dataMgr.showAll();
		assertEquals(list.get(0),"\nCourse:MATH180 CRN:10200 Credits:4 Instructor:Mr. Egg Room:SC100");
		assertEquals(list.get(1),"\nCourse:ASTR201 CRN:11222 Credits:3 Instructor:Mr. Guy Room:SC250");
	 	assertEquals(list.get(2),"\nCourse:ENGL104 CRN:12233 Credits:3 Instructor:Ms. Beans Room:SC110");
	}

	@Test
	public void testRead() {
		try {
			File inputFile = new File("Test1.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("MATH180 10200 4 SC100 Mr. Egg");
			inFile.print("ENGL104 12233 3 SC250 Mr. Guy");
			
			inFile.close();
			dataMgr.readFile(inputFile);
			assertEquals("MATH180",dataMgr.get(10200).getID());
			assertEquals("ENGL104",dataMgr.get(12233).getID());
			assertEquals("SC250",dataMgr.get(12233).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
