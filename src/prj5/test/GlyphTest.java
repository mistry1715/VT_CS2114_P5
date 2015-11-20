package prj5.test;

import prj5.Song;
import prj5.Glyph;
import prj5.Student;
import prj5.enumeration.*;

/**
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.20
 */
public class GlyphTest extends student.TestCase
{
    private Glyph glyph;

    /**
     * Set up the testing environment.
     */
    public void setUp()
    {

        glyph = new Glyph(new Song("title", "artist", "genre", 0));

        // add to glyph assume each student heard and liked the song
        glyph.addLiked(new Student(MAJOR.CS, REGION.NE, HOBBY.ART));
        glyph.addHeard(new Student(MAJOR.CS, REGION.NE, HOBBY.ART));
        glyph.addLiked(new Student(MAJOR.CS, REGION.NE, HOBBY.READ));
        glyph.addHeard(new Student(MAJOR.CS, REGION.NE, HOBBY.READ));
        glyph.addLiked(new Student(MAJOR.CS, REGION.NE, HOBBY.SPORTS));
        glyph.addHeard(new Student(MAJOR.CS, REGION.NE, HOBBY.SPORTS));
        glyph.addLiked(new Student(MAJOR.CS, REGION.NE, HOBBY.MUSIC));
        glyph.addHeard(new Student(MAJOR.CS, REGION.NE, HOBBY.MUSIC));
        glyph.addIgnoredLiked(new Student(MAJOR.CS, REGION.NE, HOBBY.ART));
        glyph.addIgnoredHeard(new Student(MAJOR.CS, REGION.NE, HOBBY.ART));
        glyph.addIgnoredLiked(new Student(MAJOR.CS, REGION.NE, HOBBY.READ));
        glyph.addIgnoredHeard(new Student(MAJOR.CS, REGION.NE, HOBBY.READ));
        glyph.addIgnoredLiked(new Student(MAJOR.CS, REGION.NE, HOBBY.SPORTS));
        glyph.addIgnoredHeard(new Student(MAJOR.CS, REGION.NE, HOBBY.SPORTS));
        glyph.addIgnoredLiked(new Student(MAJOR.CS, REGION.NE, HOBBY.MUSIC));
        glyph.addIgnoredHeard(new Student(MAJOR.CS, REGION.NE, HOBBY.MUSIC));
        // 4 students that each heard and liked the song
        Student.students.add(new Student(MAJOR.CS, REGION.NE, HOBBY.ART));
        Student.students.add(new Student(MAJOR.CS, REGION.NE, HOBBY.READ));
        Student.students.add(new Student(MAJOR.CS, REGION.NE, HOBBY.SPORTS));
        Student.students.add(new Student(MAJOR.CS, REGION.NE, HOBBY.MUSIC));
    }

    /**
     * Test the output.
     */
    public void testOutput()
    {
        glyph.sortBy(SORT_METHOD.HOBBY);
        glyph.printOutData();

        String s = "Song Title: title\n" + "Song Artist: artist\n"
                + "Song Genre: genre\n" + "Song Year: 0\n" + "Heard\n"
                + "reading:100 art:100 sports:100 music:100\n" + "Likes\n"
                + "reading:100 art:100 sports:100 music:100\n";
        assertFuzzyEquals(s, systemOut().getHistory());
    }
}

