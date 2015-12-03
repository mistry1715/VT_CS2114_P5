package prj5.test;

import prj5.Song;
import prj5.Glyph;
import prj5.Student;
import prj5.enumeration.*;

/**
 * Test the Glyph class.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.20
 */
public class GlyphTest extends student.TestCase {
    private Glyph glyph;

    /**
     * Set up the testing environment.
     */
    public void setUp() {

        glyph = new Glyph(new Song("title", "artist", "genre", 0));

        // add to glyph assume each student heard and liked the song
        glyph.addLiked(new Student(Major.CS, Region.NE, Hobby.ART));
        glyph.addHeard(new Student(Major.CS, Region.NE, Hobby.ART));
        glyph.addLiked(new Student(Major.CS, Region.NE, Hobby.READ));
        glyph.addHeard(new Student(Major.CS, Region.NE, Hobby.READ));
        glyph.addLiked(new Student(Major.CS, Region.NE, Hobby.SPORTS));
        glyph.addHeard(new Student(Major.CS, Region.NE, Hobby.SPORTS));
        glyph.addLiked(new Student(Major.CS, Region.NE, Hobby.MUSIC));
        glyph.addHeard(new Student(Major.CS, Region.NE, Hobby.MUSIC));
        glyph.addIgnoredLiked(new Student(Major.CS, Region.NE, Hobby.ART));
        glyph.addIgnoredHeard(new Student(Major.CS, Region.NE, Hobby.ART));
        glyph.addIgnoredLiked(new Student(Major.CS, Region.NE, Hobby.READ));
        glyph.addIgnoredHeard(new Student(Major.CS, Region.NE, Hobby.READ));
        glyph.addIgnoredLiked(new Student(Major.CS, Region.NE, Hobby.SPORTS));
        glyph.addIgnoredHeard(new Student(Major.CS, Region.NE, Hobby.SPORTS));
        glyph.addIgnoredLiked(new Student(Major.CS, Region.NE, Hobby.MUSIC));
        glyph.addIgnoredHeard(new Student(Major.CS, Region.NE, Hobby.MUSIC));
        // 4 students that each heard and liked the song
        Student.students.add(new Student(Major.CS, Region.NE, Hobby.ART));
        Student.students.add(new Student(Major.CS, Region.NE, Hobby.READ));
        Student.students.add(new Student(Major.CS, Region.NE, Hobby.SPORTS));
        Student.students.add(new Student(Major.CS, Region.NE, Hobby.MUSIC));
        Student.students.add(new Student(Major.CS, Region.NE, Hobby.ART));
        Student.students.add(new Student(Major.CS, Region.NE, Hobby.READ));
        Student.students.add(new Student(Major.CS, Region.NE, Hobby.SPORTS));
        Student.students.add(new Student(Major.CS, Region.NE, Hobby.MUSIC));
    }
}