package musicpref.test;

import musicpref.*;
import musicpref.comparators.*;
import musicpref.enumeration.*;

public class StudentTest extends student.TestCase {
    private Student student1;
    private Student student2;
    private Student student3;

    public void setUp() {
        student1 = new Student("Banjo", MAJOR.CS, REGION.NE, HOBBY.ART);
        student2 = new Student("Kazooie", MAJOR.EGR, REGION.OUT, HOBBY.MUSIC);
        student3 = new Student("Mumbo", MAJOR.MATH, REGION.SE, HOBBY.READ);
    }

    // Begin testing here

    public void testConstructor() {
        assertNotNull(student1);
        assertNotNull(student2);
        assertNotNull(student3);

        assertFalse(student1.getName() == "");
        assertFalse(student2.getName() == "");
        assertFalse(student3.getName() == "");

        assertNotNull(student1.getMajor());
        assertNotNull(student2.getMajor());
        assertNotNull(student3.getMajor());

        assertNotNull(student1.getRegion());
        assertNotNull(student2.getRegion());
        assertNotNull(student3.getRegion());

        assertNotNull(student1.getHobby());
        assertNotNull(student2.getHobby());
        assertNotNull(student3.getHobby());

        Exception exception = null;
        try {
            Student eStudent = new Student("", MAJOR.CS, REGION.NE, HOBBY.ART);
        }
        catch (IllegalArgumentException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

    /**
     * Test the method compareTo().
     */
    public void testCompareTo() {
        // TODO
    }

    /**
     * Test the method equals().
     */
    public void testEquals() {
        // TODO
    }

    /**
     * Test the method toString().
     */
    public void testToString() {
        assertEquals("[Banjo,CS,NE,ART]", student1.toString());
        assertEquals("[Kazooie,EGR,OUT,MUSIC]", student2.toString());
        assertEquals("[Mumbo,MATH,SE,READ]", student3.toString());
    }

    /**
     * Test the getter methods.
     */
    public void testGetters() {
        // assertEquals("", student1.getName());
        // assertEquals("", student2.getName());
        // assertEquals("", student3.getName());

        // assertEquals(, student1.getMajor());
        // assertEquals(, student2.getMajor());
        // assertEquals(, student3.getMajor());

        // test region

        // test hobby
    }
}
