package musicpref.comparators;

import java.util.Comparator;
import musicpref.*;

/**
 * Comparator to compare the artist parameter of two given Song objects.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class ArtistComparator implements Comparator<Song> {

    /**
     * Compare the given songs' artists and return a numerical result.
     * 
     * @param s1 - first Song object for comparison
     * @param s2 - second Song object for comparison
     * @return an integer result based on the comparison
     */
    @Override
    public int compare(Song s1, Song s2) {
        return s1.getArtist().compareTo(s2.getArtist());
    }

}
