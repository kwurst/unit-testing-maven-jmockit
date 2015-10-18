package edu.worcester.cs.kwurst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.Verifications;
import mockit.VerificationsInOrder;

public class StudentTest {
	@Mocked Transcript transcript;
	Student student;

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testStudentCount() {
		assertEquals(3, student.getStudentCount(), 0.0);
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
	public void testCurrentEarnedCr() {
		new Expectations() {{
			transcript.getCurrentEarnedCr(); returns(100);
		}};
		student = new Student("Sue", "Storm");
		assertEquals(100, transcript.getCurrentEarnedCr());
	}
	
	@Test
	public void testName() {
		student = new Student("Sue", "Storm");
		assertEquals("Sue", student.getFirstName());
		assertEquals("Storm", student.getLastName());
		student.setFirstName("Johnny");
		assertEquals("Johnny", student.getFirstName());
		student.setLastName("Rocket");
		assertEquals("Rocket", student.getLastName());
	}
	
	@Test
	public void testID() {
		student = new Student("Sue", "Storm");
		assertEquals("0000003", student.getId());
	}
	
	
	@Test
	public void testAnticipatedAddlCr() {
		new Expectations() {{
			transcript.getAnticipatedAdditionalCr(); returns(28);
		}};
		
		assertEquals(28, transcript.getAnticipatedAdditionalCr());
	}
	
	@Test
	public void testGetLASCCompleted() {
		new Expectations() {{
			transcript.getLascComplete(); returns(true);
		}};
		assertTrue(transcript.getLascComplete());
	}
	
	@Test
	public void testGetMajorCompleted() {
		new Expectations() {{
			transcript.getMajorComplete(); returns(true);
		}};
		
		assertTrue(transcript.getMajorComplete() == true);
	}
	
	@Test
	public void testGetCurrentRemain() {
		new Expectations() {{
			transcript.getCurrentRemainingCr(); returns(32);
		}};
		
		assertEquals(32, transcript.getCurrentRemainingCr());
	}
	
	@Test
	public void testAnticipatedRemain() {
		new Expectations() {{
			transcript.getAnticipatedRemainingCr(); returns(44);
		}};
		
		assertEquals(44, transcript.getAnticipatedRemainingCr());
	}
	
	@Test
	public void testGetReadytoGrad() {
		new Expectations() {{
			transcript.readyToGraduate(); returns(true);
		}};
		
		assertTrue(transcript.readyToGraduate() == true);
	}
	
	@Test
	public void testGetTranscript() {
		new Expectations() {{
			transcript.getTranscript(); returns("Calculus" + "\n" + "Data Mining");
		}};
		
		assertEquals("Calculus" + "\n" + "Data Mining", transcript.getTranscript());
	}
	
	
}
