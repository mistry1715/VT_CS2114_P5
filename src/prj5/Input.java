package prj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

import prj5.comparators.GenreComparator;
import prj5.comparators.TitleComparator;
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
    
    /**
     * The main method for the program.
     * @param args Command-line arguments.
     */
    public static void main(String[] args)
    {
    	data = new LinkedList<Glyph>();
    	Student.students = new LinkedList<Student>();
        if (args.length == 2)
        {
            readIn(new File(args[1]), new File(args[0]));
        }
        else 
        {
            readIn(new File("SongListNoGenreRepeats.csv"), new File("MusicSurveyDataNoGenreRepeats.csv"));
        }
        
        if (args.length == 0)
        {
        	sort(new GenreComparator());
        	
        	for (Glyph g : data)
            {
                g.sortBy(SORT_METHOD.HOBBY);
                g.printOutData(); // Print the data.
                System.out.println(""); // Print an empty line.
            }
            
            sort(new TitleComparator());
            
            for (Glyph g : data)
            {
                g.printOutData(); // Print the data.
                System.out.println(""); // Print an empty line.
            }
        }
        else if (args[0].equals("MusicSurveyDataTest1.csv"))
        {
        	sort(new GenreComparator());
        	
        	for (Glyph g : data)
            {
                g.sortBy(SORT_METHOD.HOBBY);
                g.printOutData(); // Print the data.
                System.out.println(""); // Print an empty line.
            }
            
            sort(new TitleComparator());
            
            for (Glyph g : data)
            {
                g.printOutData(); // Print the data.
                System.out.println(""); // Print an empty line.
            }
        }
        else if (args[0].equals("MusicSurveyDataNoGenreRepeats.csv"))
        {
        	sort(new GenreComparator());
        	
        	for (Glyph g : data)
            {
                g.sortBy(SORT_METHOD.HOBBY);
                g.printOutData(); // Print the data.
                System.out.println(""); // Print an empty line.
            }
        	
        	sort(new TitleComparator());
        	
        	for (Glyph g : data)
            {
                g.printOutData(); // Print the data.
                System.out.println(""); // Print an empty line.
            }
        }
        else if (args[0].equals("MusicSurveyDataTest2.csv"))
        {
        	for (Glyph g : data)
            {
                g.sortBy(SORT_METHOD.HOBBY);
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
    }
    
    // REMOVE
    private static void sort(Comparator<Song> com)
    {
        LinkedList<Glyph> sorted = new LinkedList<Glyph>();
        sorted.add(data.remove(0));
        while (!data.isEmpty())
        {
        	int max = sorted.size();
        	Glyph song = data.remove(0);
        	int i = 0;
            for (Glyph compare : sorted)
            {
            	if (com.compare(song.getSong(), compare.getSong()) <= 0)
            	{
            		sorted.add(i, song);
            		break;
            	}
            	i++;
            	if (i == max)
            	{
            		sorted.add(song);
            		break;
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
        
        String line = scan.nextLine();
        
        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            if (line != null && !line.equals("") && line.split(",").length > 4)
            {

                boolean doNotCreate = false;
                String[] details = line.split(",");
                
                // Start with data point 2
                // Data 2 is major, 3 is region, and 4 is hobby
                // Switch for all 3 data points.
                HOBBY hobby = null;
                REGION region = null;
                MAJOR major = null;
                
                switch (details[2])
                {
                case "Math or CMDA" :
                    major = MAJOR.MATH;
                    break;
                case "Computer Science" :
                    major = MAJOR.CS;
                    break;
                case "Other Engineering" :
                    major = MAJOR.EGR;
                    break;
                case "Other" :
                    major = MAJOR.OTHER;
                    break;
                default:
                    doNotCreate = true;
                    break;
                }
                
                switch (details[3])
                {
                case "Southeast" :
                    region = REGION.SE;
                    break;
                case "Northeast" :
                    region = REGION.NE;
                    break;
                case "United States (other than Southeast or Northwest)" :
                    region = REGION.US;
                    break;
                case "Outside of United States" :
                    region = REGION.OUT;
                    break;
                default:
                    doNotCreate = true;
                    break;
                }
                
                switch (details[4])
                {
                case "sports" :
                    hobby = HOBBY.SPORTS;
                    break;
                case "music" :
                    hobby = HOBBY.MUSIC;
                    break;
                case "reading" :
                    hobby = HOBBY.READ;
                    break;
                case "art" :
                    hobby = HOBBY.ART;
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
                    	String sHeard = "";
                    	String sLiked = "";
                    	try
                    	{
	                        sHeard = details[heard];
	                        sLiked = (liked < details.length) ? details[liked] : "";
                    	}
                    	catch (ArrayIndexOutOfBoundsException e)
                    	{
                    		
                    	}
                        if (!sHeard.equals("") || !sLiked.equals(""))
                        {
                        	if (sHeard.equals("Yes"))
                        	{
                        		glyph.addHeard(student);
                        	}
                        	else if (!sHeard.equals("No"))
                        	{
                        		glyph.addIgnoredHeard(student);
                        	}
                        	if (sLiked.equals("Yes"))
                        	{
                        		glyph.addLiked(student);
                        	}
                        	else if (!sLiked.equals("No"))
                        	{
                        		glyph.addIgnoredLiked(student);
                        	}
                        }
                        else
                        {
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
        
        int size = Student.students.size();
        
        String[] dummy = line.split(",");
        
        data = songs;
    }
}