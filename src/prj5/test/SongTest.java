package prj5.test;

import prj5.*;
// import prj5.comparators.*;
// import prj5.enumeration.*;

/**
 * Test the Song class.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class SongTest extends student.TestCase {
    private Song song1;
    private Song song2;
    private Song song3;

    /**
     * Set up the testing environment.
     */
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
        assertEquals(13, song1.compareTo(song2));
        assertEquals(-16, song2.compareTo(song3));
        assertEquals(0, song1.compareTo(song1));
        assertEquals(3, song3.compareTo(song1));
    }

    /**
     * Test the method equals().
     */
    public void testEquals() {
        assertFalse(song1.equals(song2));
        assertFalse(song1.equals(null));
        Song nullSong = null;
        assertFalse(song1.equals(nullSong));
        assertTrue(song1.equals(song1));
        Song doppleSong = song1;
        Song similarSong = new Song("The Ocean", "Led Zeppelin", "rock", 1973);
        assertTrue(song1.equals(doppleSong));
        assertTrue(song1.equals(similarSong));
    }

    /**
     * Test the method toString().
     */
    public void testToString() {
        assertEquals("[The Ocean,Led Zeppelin,rock,1973]", song1.toString());
        assertEquals(
                "[Gypsy Eyes,The Jimi Hendrix Experience,psychedelic rock,1968]",
                song2.toString());
        assertEquals("[Warlocks,Red Hot Chili Peppers,funk rock,2006]",
                song3.toString());
    }

    /**
     * Test the getter methods.
     */
    public void testGetters() {
        assertEquals("The Ocean", song1.getTitle());
        assertEquals("Gypsy Eyes", song2.getTitle());
        assertEquals("Warlocks", song3.getTitle());

        assertEquals("Led Zeppelin", song1.getArtist());
        assertEquals("The Jimi Hendrix Experience", song2.getArtist());
        assertEquals("Red Hot Chili Peppers", song3.getArtist());

        assertEquals("rock", song1.getGenre());
        assertEquals("psychedelic rock", song2.getGenre());
        assertEquals("funk rock", song3.getGenre());

        assertEquals(1973, song1.getYear());
        assertEquals(1968, song2.getYear());
        assertEquals(2006, song3.getYear());
    }
}
