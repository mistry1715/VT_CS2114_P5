package prj5.test;

import prj5.*;
// import prj5.comparators.*;
import prj5.enumeration.*;

public class StudentTest extends student.TestCase {
    private Student student1;
    private Student student2;
    private Student student3;

    public void setUp() {
        student1 = new Student(Major.CS, Region.NE, Hobby.ART);
        student2 = new Student(Major.EGR, Region.OUT, Hobby.MUSIC);
        student3 = new Student(Major.MATH, Region.SE, Hobby.READ);
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
        Student similarStudent = new Student(Major.CS, Region.NE, Hobby.ART);
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
        assertEquals(Major.CS, student1.getMajor());
        assertEquals(Major.EGR, student2.getMajor());
        assertEquals(Major.MATH, student3.getMajor());

        assertEquals(Region.NE, student1.getRegion());
        assertEquals(Region.OUT, student2.getRegion());
        assertEquals(Region.SE, student3.getRegion());

        assertEquals(Hobby.ART, student1.getHobby());
        assertEquals(Hobby.MUSIC, student2.getHobby());
        assertEquals(Hobby.READ, student3.getHobby());
    }
}
