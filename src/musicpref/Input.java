package musicpref;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import musicpref.enumeration.*;

/**
 * This class reads in the input file and starts the program.
 * @author Alec
 * @author Julian
 * @author Matt
 * @version 2015.11.16
 */
public class Input 
{
	/**
	 * The main method for the program.
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args)
	{
		new GUIVisualization();
		if (args.length == 2)
		{
			readIn(new File(args[1]), new File(args[0]));
		}
		else 
		{
			readIn(new File("SongList.csv"), new File("MusicSurveyData.csv"));
		}
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
					
					// begin the reading in of the responses.
					int heard = 5;
					int liked = 6;
					
					for (Glyph glyph : songs)
					{
						String sHeard = details[heard];
						String sLiked = details[liked];
						if (!sHeard.equals("") && !sLiked.equals(""))
						{
							if (sHeard.equals("Yes"));
							{
								glyph.addHeard(student);
							}
							if (sLiked.equals("Yes"));
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
		
		for (Glyph glyph : songs)
		{
			glyph.makeGUIGlpyh();
		}
		
		scan.close();
	}
}
