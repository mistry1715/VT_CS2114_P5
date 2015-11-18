package musicpref.test;

import musicpref.*;
import musicpref.comparators.*;
import musicpref.enumeration.*;

public class SongTest extends student.TestCase {
    private Song song1;
    private Song song2;
    private Song song3;

    public void setUp() {
        song1 = new Song("The Ocean", "Led Zeppelin", "rock", 1973);
        song2 = new Song("Gypsy Eyes", "The Jimi Hendrix Experience",
                "psychedelic rock", 1968);
        song3 = new Song("Warlocks", "Red Hot Chili Peppers", "funk rock",
                2006);
    }

    // Begin testing here

    /**
     * Test the constructor.
     */
    public void testConstructor() {
        assertNotNull(song1);
        assertNotNull(song2);
        assertNotNull(song3);

        assertNotNull(song1.getTitle());
        assertNotNull(song2.getTitle());
        assertNotNull(song3.getTitle());

        assertNotNull(song1.getArtist());
        assertNotNull(song2.getArtist());
        assertNotNull(song3.getArtist());

        assertNotNull(song1.getGenre());
        assertNotNull(song2.getGenre());
        assertNotNull(song3.getGenre());

        assertNotNull(song1.getYear());
        assertNotNull(song2.getYear());
        assertNotNull(song3.getYear());
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
        // TODO
    }

    /**
     * Test the getter methods.
     */
    public void testGetters() {
        // TODO
    }
}
