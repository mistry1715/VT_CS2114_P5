package prj5;

import prj5.comparators.*;

/**
 * Each Song object contains the data given by a text file of songs separated
 * into individual songs by title. The title, artist, genre, and year are stored
 * to be used later when sorting student responses into their respective lists.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class Song {
    // protected static LinkedList<Song> songs;
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
     * @throws IllegalArgumentException if an invalid title, artist, genre, or
     *         year is given (i.e. an empty string or a negative integer)
     */
    public Song(String title, String artist, String genre, int year) {
        if (title == "" || artist == "" || genre == "" || year < 0) {
            throw new IllegalArgumentException(
                    "Empty string or negative integer given.");
        }

        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
    }

    /**
     * Compare the given object parameter to the calling object.
     * 
     * @param obj - the object to compare with the calling object
     * @return the comparison result
     * @throws IllegalArgumentException if an invalid object is passed
     */
    public int compareTo(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object passed is null.");
        }

        if (obj.getClass() != this.getClass()) {
            throw new IllegalArgumentException("Object passed is not a Song.");
        }

        Song other = (Song) obj;

        int diff = new TitleComparator().compare(this, other);

        if (diff == 0) {
            diff = new ArtistComparator().compare(this, other);
        }

        if (diff == 0) {
            diff = new GenreComparator().compare(this, other);
        }

        if (diff == 0) {
            diff = new YearComparator().compare(this, other);
        }

        return diff;
    }

    /**
     * Check if the given object parameter is equal to the calling object.
     * 
     * @param obj - the object to compare with the calling object
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Song other;
        if (obj.getClass() == this.getClass()) {
            other = (Song) obj;
            return other.title == this.title && other.artist == this.artist
                    && other.genre == this.genre && other.year == this.year;
        }

        return false;
    }

    /**
     * Convert the Song object to a string of values within a set of brackets.
     * 
     * @return the calling Song object in the form of a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(title + "," + artist + "," + genre + "," + year);
        builder.append("]");
        return builder.toString();
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
