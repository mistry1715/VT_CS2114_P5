package prj5.test;

import prj5.*;
import prj5.comparators.*;
import prj5.enumeration.*;

/**
 * Test the comparator classes.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class ComparatorTest extends student.TestCase {
    private ArtistComparator artist;
    private GenreComparator genre;
    private HobbyComparator hobby;
    private MajorComparator major;
    private RegionComparator region;
    private TitleComparator title;
    private YearComparator year;

    /**
     * sets up for testing
     */
    public void setUp() {
        artist = new ArtistComparator();
        genre = new GenreComparator();
        hobby = new HobbyComparator();
        major = new MajorComparator();
        region = new RegionComparator();
        title = new TitleComparator();
        year = new YearComparator();
    }

    /**
     * tests the artist comparator method
     */
    public void testArtistComparator() {
        assertEquals(artist.compare(new Song("title", "Artist", "Genre", 0),
                new Song("title", "Artist", "Genre", 0)), 0);
        assertTrue(artist.compare(new Song("title", "Artist2", "Genre", 0),
                new Song("title", "Artist", "Genre", 0)) > 0);
        assertTrue(artist.compare(new Song("title", "Artis", "Genre", 0),
                new Song("title", "Artist", "Genre", 0)) < 0);
    }

    /**
     * tests the genre comparator method
     */
    public void testGenreComparator() {
        assertEquals(genre.compare(new Song("title", "Artist", "Genre", 0),
                new Song("title", "Artist", "Genre", 0)), 0);
        assertTrue(genre.compare(new Song("title", "Artist", "Genre2", 0),
                new Song("title", "Artist", "Genre", 0)) > 0);
        assertTrue(genre.compare(new Song("title", "Artist", "Genr", 0),
                new Song("title", "Artist", "Genre", 0)) < 0);
    }

    /**
     * tests the hobby comparator method
     */
    public void testHobbyComparator() {
        assertEquals(hobby.compare(new Student(Major.CS, Region.NE, Hobby.READ),
                new Student(Major.CS, Region.NE, Hobby.READ)), 0);
        assertTrue(hobby.compare(new Student(Major.CS, Region.NE, Hobby.ART),
                new Student(Major.CS, Region.NE, Hobby.READ)) > 0);
        assertTrue(hobby.compare(new Student(Major.CS, Region.NE, Hobby.SPORTS),
                new Student(Major.CS, Region.NE, Hobby.READ)) > 0);
        assertTrue(hobby.compare(new Student(Major.CS, Region.NE, Hobby.ART),
                new Student(Major.CS, Region.NE, Hobby.MUSIC)) < 0);
    }

    /**
     * tests the major comparator method
     */
    public void testMajorComparator() {
        assertEquals(major.compare(new Student(Major.CS, Region.NE, Hobby.READ),
                new Student(Major.CS, Region.NE, Hobby.READ)), 0);
        assertTrue(major.compare(new Student(Major.MATH, Region.NE, Hobby.ART),
                new Student(Major.CS, Region.NE, Hobby.READ)) > 0);
        assertTrue(
                major.compare(new Student(Major.OTHER, Region.NE, Hobby.SPORTS),
                        new Student(Major.EGR, Region.NE, Hobby.READ)) > 0);
        assertTrue(major.compare(new Student(Major.MATH, Region.NE, Hobby.ART),
                new Student(Major.OTHER, Region.NE, Hobby.MUSIC)) < 0);
    }

    /**
     * tests the region comparator method
     */
    public void testRegionComparator() {
        assertEquals(
                region.compare(new Student(Major.CS, Region.NE, Hobby.READ),
                        new Student(Major.CS, Region.NE, Hobby.READ)),
                0);
        assertTrue(region.compare(new Student(Major.MATH, Region.SE, Hobby.ART),
                new Student(Major.CS, Region.NE, Hobby.READ)) > 0);
        assertTrue(region.compare(
                new Student(Major.OTHER, Region.OUT, Hobby.SPORTS),
                new Student(Major.EGR, Region.SE, Hobby.READ)) > 0);
        assertTrue(region.compare(new Student(Major.MATH, Region.SE, Hobby.ART),
                new Student(Major.OTHER, Region.US, Hobby.MUSIC)) < 0);
    }

    /**
     * tests the title comparator method
     */
    public void testTitleComparator() {
        assertEquals(title.compare(new Song("title", "Artist", "Genre", 0),
                new Song("title", "Artist", "Genre", 0)), 0);
        assertTrue(title.compare(new Song("title2", "Artist", "Genre2", 0),
                new Song("title", "Artist", "Genre", 0)) > 0);
        assertTrue(title.compare(new Song("titl", "Artist", "Genr", 0),
                new Song("title", "Artist", "Genre", 0)) < 0);
    }

    /**
     * tests the year comparator method
     */
    public void testYearComparator() {
        assertEquals(year.compare(new Song("title", "Artist", "Genre", 0),
                new Song("title", "Artist", "Genre", 0)), 0);
        assertTrue(year.compare(new Song("title", "Artist", "Genre2", 1),
                new Song("title", "Artist", "Genre", 0)) > 0);
        assertTrue(year.compare(new Song("title", "Artist", "Genr", 0),
                new Song("title", "Artist", "Genre", 1)) < 0);
    }
}