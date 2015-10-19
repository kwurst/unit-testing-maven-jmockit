package edu.worcester.cs.kwurst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.worcester.cs.kwurst.Transcript.Semester;

public class TranscriptTest {


	@Mocked Transcript transcript;
	Student student;
	Grade grade;

	private Transcript t1;
	private Student student1;
	private Student student2;
	
	private Course course1, course2;
	private Grade gradeA, gradeCplus, gradeE, gradeIP;
	
	@Before
	public void setUp() throws Exception {
		t1 = new Transcript();
		student1 = new Student("Jane", "Smith");
		student2 = new Student("Jonathan", "Shatos");
		
		course1 = new Course("CS", 201, "Data Mining", 3);
		course2 = new Course("Math", 301, "Calculus", 4);
		
		gradeA = new Grade("A");
		gradeCplus = new Grade("C+");
		gradeE = new Grade("E");
		gradeIP = new Grade("IP");
	}

	@Test
	public void testGpa() {
		new Expectations() {{
			new Transcript();
			transcript.addCourse(
					new Course("CS", 443, "Software Quality Assurance and Testing", 3), 
					Transcript.Semester.FALL, 2015, new Grade("A"));
			transcript.getGpa(); returns(4.0);
		}};
		
		student = new Student("Sue", "Storm");
		student.addCourse(
				new Course("CS", 443, "Software Quality Assurance and Testing", 3), 
				Transcript.Semester.FALL, 2015, new Grade("A"));
		assertEquals(4.0, student.getGpa(), 0.0);
	
		new VerificationsInOrder() {{
			new Transcript();
			transcript.addCourse(
					new Course("CS", 443, "Software Quality Assurance and Testing", 3), 
					Transcript.Semester.FALL, 2015, new Grade("A"));
			transcript.getGpa();
		}};
	}  
	
	@Test
	public void testGetLetterGrade(){
		assertEquals("A", gradeA.getLetterGrade());
		assertEquals("C+", gradeCplus.getLetterGrade());
		assertEquals("E", gradeE.getLetterGrade());
		assertEquals("IP", gradeIP.getLetterGrade());
	}
	
	@Test
	public void testGetNumericGrade(){
		assertEquals(4.0, gradeA.getNumericGrade(),0);
		assertEquals(2.3, gradeCplus.getNumericGrade(),0);
		assertEquals(0, gradeE.getNumericGrade(),0);
		assertEquals(0, gradeIP.getNumericGrade(),0);
	}
	
	@Test
	public void testGetDepartment(){
		assertEquals("CS", course1.getDepartment());
		assertEquals("Math", course2.getDepartment());
		course2.setDepartment("Biology");
		assertEquals("Biology", course2.getDepartment());
		
	}
	
	@Test
	public void testGetNumber(){
		assertEquals(201, course1.getNumber());
		assertEquals(301, course2.getNumber());
		course2.setNumber(999);
		assertEquals(999, course2.getNumber());
		
	}
	
	@Test
	public void testGetTitle(){
		assertEquals("Data Mining", course1.getTitle());
		assertEquals("Calculus", course2.getTitle());
		course2.setTitle("Cell Growth");
		assertEquals("Cell Growth", course2.getTitle());
		
	}
	
	@Test
	public void testGetCredits(){
		assertEquals(3, course1.getCredits());
		assertEquals(4, course2.getCredits());
		course2.setCredits(999);
		assertEquals(999, course2.getCredits());
		
	}
	
	@Test
	public void testAddCourse(){
		Course c = new Course("CS", 443, "Quality Assurance", 3);
		student1.addCourse(c, Semester.FALL, 2015, gradeA);
		assertTrue(student1.getCurrentEarnedCr() == 3);
	}
	
	@Test
	public void testGetTranscript(){
		student2.addCourse(course2,Semester.SPRING, 2016, gradeA);
		assertTrue(student2.getTranscript() != null);
	}
}