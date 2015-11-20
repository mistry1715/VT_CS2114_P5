package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

import prj5.comparators.GenreComparator;
import prj5.enumeration.*;

/**
 * This class reads in the input file and starts the program.
 * @author Alec
 * @author Julian
 * @author Matt
 * @version 2015.11.16
 */
public class Input 
{
    private static LinkedList<Glyph> data = new LinkedList<Glyph>(); // Temp glyph list.
    private static int numOfStudents = 0;
    
    /**
     * The main method for the program.
     * @param args Command-line arguments.
     */
    public static void main(String[] args)
    {
        if (args.length == 2)
        {
            readIn(new File(args[1]), new File(args[0]));
        }
        else 
        {
            readIn(new File("SongList.csv"), new File("MusicSurveyData.csv"));
        }
        
        for (Glyph g : data)
        {
            g.sortBy(SortMethod.HOBBY);
            g.printOutData(); // Print the data.
            System.out.println(""); // Print an empty line.
        }
        
        sort(new GenreComparator());
        
        for (Glyph g : data)
        {
            g.printOutData(); // Print the data.
            System.out.println(""); // Print an empty line.
        }
    }
    
    // REMOVE
    private static void sort(Comparator<Song> com)
    {
        LinkedList<Glyph> sorted = new LinkedList<Glyph>();
        sorted.add(data.remove(0));
        while (!data.isEmpty())
        {
            for (int i = 0; i < sorted.size(); i++)
            {
                if (data.size() == 0)
                {
                    break;
                }
                else if (com.compare(sorted.get(i).getSong(), data.get(0).getSong()) >= 0)
                {
                    sorted.add(i + 1, data.remove(0));
                }
                else if (i == sorted.size() - 1)
                {
                    sorted.add(data.remove(0));
                }
            }
        }
        
        data = sorted;
    }
    
    public static void readIn(File songs, File students) 
    {
        readInStudents(students, readInSongs(songs));
    }
    
    private static LinkedList<Glyph> readInSongs(File songs)
    {
        Scanner scan = null;
        try 
        {
            scan = new Scanner(songs);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Song file not found. Exiting...");
            System.exit(0);
        }
        
        String line = null;
        LinkedList<Glyph> glyphs = new LinkedList<>();
        
        scan.nextLine();
        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            if (line != null && !line.equals("") && !line.startsWith("Song"));
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
    }

    private static void readInStudents(File students, LinkedList<Glyph> songs)
    {
        Scanner scan = null;
        try 
        {
            scan = new Scanner(students);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Students file not found. Exiting...");
            System.exit(0);
        }
        
        String line = null;
        
        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            if (line != null && !line.equals("") && !line.startsWith("Nr"))
            {
                if (line.split(",").length < 20)
                {
                    break;
                }

                boolean doNotCreate = false;
                String[] details = line.split(",");
                
                // Start with data point 2
                // Data 2 is major, 3 is region, and 4 is hobby
                // Switch for all 3 data points.
                Hobby hobby = null;
                Region region = null;
                Major major = null;
                
                switch (details[2])
                {
                case "Math or CMDA" :
                    major = Major.MATH;
                    break;
                case "Computer Science" :
                    major = Major.CS;
                    break;
                case "Other Engineering" :
                    major = Major.EGR;
                    break;
                case "Other" :
                    major = Major.OTHER;
                    break;
                default:
                    doNotCreate = true;
                    break;
                }
                
                switch (details[3])
                {
                case "Southeast" :
                    region = Region.SE;
                    break;
                case "Northeast" :
                    region = Region.NE;
                    break;
                case "United States (other than Southeast or Northwest)" :
                    region = Region.US;
                    break;
                case "Outside of United States" :
                    region = Region.OUT;
                    break;
                default:
                    doNotCreate = true;
                    break;
                }
                
                switch (details[4])
                {
                case "sports" :
                    hobby = Hobby.SPORTS;
                    break;
                case "music" :
                    hobby = Hobby.MUSIC;
                    break;
                case "reading" :
                    hobby = Hobby.READ;
                    break;
                case "art" :
                    hobby = Hobby.ART;
                    break;
                default:
                    doNotCreate = true;
                    break;
                }
                // If it is not flagged as not a student
                if (!doNotCreate)
                {
                    // make the new student
                    Student student = new Student(major, region, hobby);
                    Student.students.add(student);
                    
                    // begin the reading in of the responses.
                    int heard = 5;
                    int liked = 6;
                    
                    for (Glyph glyph : songs)
                    {
                        String sHeard = details[heard];
                        String sLiked = (liked < details.length) ? details[liked] : "";
                        if (!sHeard.equals("") && !sLiked.equals(""))
                        {
                            if (sHeard.equals("Yes"))
                            {
                                glyph.addHeard(student);
                            }
                            if (sLiked.equals("Yes"))
                            {
                                glyph.addLiked(student);
                            }
                        }
                        else
                        {
                            glyph.addIgnored(student);
                        }
                        heard += 2;
                        liked += 2;
                    }
                }
            }
        }
        
        scan.close();
        
        data = songs;
    }
    
    public static int getStudents()
    {
        return numOfStudents;
    }
}
