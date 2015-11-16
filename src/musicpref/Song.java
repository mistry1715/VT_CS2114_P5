package musicpref;

/**
 * TODO
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class Song {
    // public LinkedList<Song> songs;
    private String title;
    private String artist;
    private String genre;
    private int year;

    /**
     * Create a new Song object with the given title, artist, genre, and year
     * parameters.
     * 
     * @param title - the song's title
     * @param artist - the song's artist
     * @param genre - the song's genre
     * @param year - the song's release year
     */
    public Song(String title, String artist, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        // TODO
    }

    /**
     * Compare the given object parameter to the calling object.
     * 
     * @param obj - the object to compare with the calling object
     * @return the comparison result
     */
    public int compareTo(Object obj) {
        return 0; // TODO
    }

    /**
     * Check if the given object parameter is equal to the calling object.
     * 
     * @param obj - the object to compare with the calling object
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        return false; // TODO
    }

    /**
     * Return the song's title.
     * 
     * @return the song's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Return the song's artist.
     * 
     * @return the song's artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Return the song's genre.
     * 
     * @return the song's genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Return the song's release year.
     * 
     * @return the song's release year
     */
    public int getYear() {
        return year;
    }

}
