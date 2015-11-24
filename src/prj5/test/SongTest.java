package prj5.test;

import prj5.Song;

// import prj5.comparators.*;

// import prj5.enumeration.*;

/**
 * Tests the song class.
 * @author Julian Davila (jaydee1)
 * @author Alec Alderman (ama2106)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.20
 *
 */
public class SongTest extends student.TestCase
{
    private Song song1;
    private Song song2;
    private Song song3;

    /**
     * Set up the testing environment.
     */
    public void setUp()
    {
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
    public void testConstructor()
    {
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

        Exception thrown = null;

        try
        {
            new Song("", "a", "g", 0);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;

        try
        {
            new Song("a", "", "g", 0);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;

        try
        {
            new Song("a", "a", "", 0);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;

        try
        {
            new Song("a", "a", "g", -1);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    /**
     * Test the method compareTo().
     */
    public void testCompareTo()
    {
        assertEquals(13, song1.compareTo(song2));
        assertEquals(-16, song2.compareTo(song3));
        assertEquals(0, song1.compareTo(song1));
        assertEquals(3, song3.compareTo(song1));

        Exception thrown = null;

        try
        {
            Song test = null;
            song1.compareTo(test);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;

        try
        {
            Object test = new Object();
            song1.compareTo(test);
        }
        catch (Exception e)
        {
            thrown = e;
        }

        assertNotNull(thrown);
        assertTrue(thrown instanceof IllegalArgumentException);
    }

    /**
     * Test the method equals().
     */
    public void testEquals()
    {
        assertFalse(song1.equals(song2));
        Song nullSong = null;
        assertFalse(song1.equals(nullSong));
        assertTrue(song1.equals(song1));
        Song doppleSong = song1;
        Song similarSong = new Song("The Ocean", "Led Zeppelin", "rock", 1973);
        assertTrue(song1.equals(doppleSong));
        assertTrue(song1.equals(similarSong));
        assertFalse(song1.equals(new Object()));
        Song s = new Song("a", "a", "a", 0);
        assertFalse(song1.equals(s));
    }

    /**
     * Test the method toString().
     */
    public void testToString()
    {
        assertEquals("[The Ocean,Led Zeppelin,rock,1973]", song1.toString());
        assertEquals(
                "[Gypsy Eyes,The Jimi Hendrix Experience,"
                + "psychedelic rock,1968]",
                song2.toString());
        assertEquals("[Warlocks,Red Hot Chili Peppers,funk rock,2006]",
                song3.toString());
    }

    /**
     * Test the getter methods.
     */
    public void testGetters()
    {
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