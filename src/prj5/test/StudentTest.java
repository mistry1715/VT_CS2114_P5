package prj5.test;

import prj5.*;
// import prj5.comparators.*;
import prj5.enumeration.*;

public class StudentTest extends student.TestCase {
    private Student student1;
    private Student student2;
    private Student student3;

    public void setUp() {
        student1 = new Student(MAJOR.CS, REGION.NE, HOBBY.ART);
        student2 = new Student(MAJOR.EGR, REGION.OUT, HOBBY.MUSIC);
        student3 = new Student(MAJOR.MATH, REGION.SE, HOBBY.READ);
    }

    // Begin testing here

    public void testConstructor() {
        assertNotNull(student1);
        assertNotNull(student2);
        assertNotNull(student3);

        assertNotNull(student1.getMajor());
        assertNotNull(student2.getMajor());
        assertNotNull(student3.getMajor());

        assertNotNull(student1.getRegion());
        assertNotNull(student2.getRegion());
        assertNotNull(student3.getRegion());

        assertNotNull(student1.getHobby());
        assertNotNull(student2.getHobby());
        assertNotNull(student3.getHobby());
    }

    /**
     * Test the method compareTo().
     */
    public void testCompareTo() {
        // assertEquals(0, student1.compareTo(student2));
    }

    /**
     * Test the method equals().
     */
    public void testEquals() {
        assertFalse(student1.equals(student2));
        assertFalse(student1.equals(null));
        Student nullStudent = null;
        assertFalse(student1.equals(nullStudent));
        assertTrue(student1.equals(student1));
        Student doppleStudent = student1;
        Student similarStudent = new Student(MAJOR.CS, REGION.NE, HOBBY.ART);
        assertTrue(student1.equals(doppleStudent));
        assertTrue(student1.equals(similarStudent));
    }

    /**
     * Test the method toString().
     */
    public void testToString() {
        assertEquals("[CS,NE,ART]", student1.toString());
        assertEquals("[EGR,OUT,MUSIC]", student2.toString());
        assertEquals("[MATH,SE,READ]", student3.toString());
    }

    /**
     * Test the getter methods.
     */
    public void testGetters() {
        assertEquals(MAJOR.CS, student1.getMajor());
        assertEquals(MAJOR.EGR, student2.getMajor());
        assertEquals(MAJOR.MATH, student3.getMajor());

        assertEquals(REGION.NE, student1.getRegion());
        assertEquals(REGION.OUT, student2.getRegion());
        assertEquals(REGION.SE, student3.getRegion());

        assertEquals(HOBBY.ART, student1.getHobby());
        assertEquals(HOBBY.MUSIC, student2.getHobby());
        assertEquals(HOBBY.READ, student3.getHobby());
    }
}
