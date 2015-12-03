package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import prj5.enumeration.*;

/**
 * Program driver; the Input class receives the CSV files containing the song
 * data and the student response data. The data is sorted into individual Glyphs
 * to be used by the front-end.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class Input {
    private static LinkedList<Glyph> data = new LinkedList<Glyph>();

    /**
     * The main method for the program. If two arguments are passed, try to read
     * in the two arguments as new files. If no arguments are given, use default
     * text file strings.
     * 
     * @param args - command line arguments
     */
    public static void main(String[] args) {
        data = new LinkedList<Glyph>();
        Student.students = new LinkedList<Student>();
        if (args.length == 2) {
            readIn(new File(args[1]), new File(args[0]));
        }
        else {
            readIn(new File("SongList.csv"), new File("MusicSurveyData.csv"));
        }



        new GUIVisualization(data);
    } // end main

    /**
     * Read in the given files containing song data and student response data.
     * 
     * @param songs - a text file containing the song data
     * @param students - a text file containing the student data
     */
    public static void readIn(File songs, File students) {
        readInStudents(students, readInSongs(songs));
    } // end readIn

    /**
     * When a valid text file containing song data is passed in, create a new
     * glyph to be added to the LinkedList of glyphs containing the various
     * songs.
     * 
     * @param songs - a text file containing the song data
     * @return a LinkedList of Glyph objects
     * @throws FileNotFoundException if no file is received
     */
    private static LinkedList<Glyph> readInSongs(File songs) {
        Scanner scan = null;
        try {
            scan = new Scanner(songs);
        }
        catch (FileNotFoundException e) {
            System.out.println("Song file not found. Exiting...");
            System.exit(0);
        }

        String line = null;
        LinkedList<Glyph> glyphs = new LinkedList<>();

        scan.nextLine();
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (line != null && !line.equals("") && !line.startsWith("Song"))
                ;
            {
                String[] details = line.split(",");
                String title = details[0];
                String artist = details[1];
                String genre = details[3];
                int year = Integer.parseInt(details[2]);
                glyphs.add(new Glyph(new Song(title, artist, genre, year)));
            }
        }

        scan.close();

        return glyphs;
    } // end readInSongs

    /**
     * When a valid text file containing student response data is passed in,
     * separate the given data into their respective glyphs based on the
     * corresponding songs.
     * 
     * @param students - a text file containing the song data
     * @param songs - the LinkedList of glyphs to separate the response data
     *        into
     * @throws FileNotFoundException if no file is received
     */
    private static void readInStudents(File students, LinkedList<Glyph> songs) {
        Scanner scan = null;
        try {
            scan = new Scanner(students);
        }
        catch (FileNotFoundException e) {
            System.out.println("Students file not found. Exiting...");
            System.exit(0);
        }

        String line = scan.nextLine();

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (line != null && !line.equals("")
                    && line.split(",").length > 4) {

                boolean doNotCreate = false;
                String[] details = line.split(",");

                // Start with data point 2
                // Data 2 is major, 3 is region, and 4 is hobby
                // Switch for all 3 data points.
                Hobby hobby = null;
                Region region = null;
                Major major = null;

                switch (details[2]) {
                    case "Math or CMDA":
                        major = Major.MATH;
                        break;
                    case "Computer Science":
                        major = Major.CS;
                        break;
                    case "Other Engineering":
                        major = Major.EGR;
                        break;
                    case "Other":
                        major = Major.OTHER;
                        break;
                    default:
                        doNotCreate = true;
                        break;
                }

                switch (details[3]) {
                    case "Southeast":
                        region = Region.SE;
                        break;
                    case "Northeast":
                        region = Region.NE;
                        break;
                    case "United States (other than Southeast or Northwest)":
                        region = Region.US;
                        break;
                    case "Outside of United States":
                        region = Region.OUT;
                        break;
                    default:
                        doNotCreate = true;
                        break;
                }

                switch (details[4]) {
                    case "sports":
                        hobby = Hobby.SPORTS;
                        break;
                    case "music":
                        hobby = Hobby.MUSIC;
                        break;
                    case "reading":
                        hobby = Hobby.READ;
                        break;
                    case "art":
                        hobby = Hobby.ART;
                        break;
                    default:
                        doNotCreate = true;
                        break;
                }
                // If it is not flagged as not a student
                if (!doNotCreate) {
                    // make the new student
                    Student student = new Student(major, region, hobby);
                    Student.students.add(student);

                    // begin the reading in of the responses.
                    int heard = 5;
                    int liked = 6;

                    for (Glyph glyph : songs) {
                        String sHeard = "";
                        String sLiked = "";
                        try {
                            sHeard = details[heard];
                            sLiked = (liked < details.length) ? details[liked]
                                    : "";
                        }
                        catch (ArrayIndexOutOfBoundsException e) {

                        }
                        if (!sHeard.equals("") || !sLiked.equals("")) {
                            if (sHeard.equals("Yes")) {
                                glyph.addHeard(student);
                            }
                            else if (!sHeard.equals("No")) {
                                glyph.addIgnoredHeard(student);
                            }

                            if (sLiked.equals("Yes")) {
                                glyph.addLiked(student);
                            }
                            else if (!sLiked.equals("No")) {
                                glyph.addIgnoredLiked(student);
                            }
                        }
                        else {
                            glyph.addIgnoredLiked(student);
                            glyph.addIgnoredHeard(student);
                        }
                        heard += 2;
                        liked += 2;
                    }
                }
            }
        }

        scan.close();

        data = songs;
    } // end readInStudents
}