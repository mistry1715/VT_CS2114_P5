package prj5;

import CS2114.Button;
import CS2114.Window;
import CS2114.WindowSide;
import prj5.enumeration.SortMethod;

public class GUIVisualization 
{
	private Window window;
	private int position;
	private SortMethod sortedBy;
	private Button previous;
	private Button next;
	private Button title;
	private Button artist;
	private Button genre;
	private Button year;
	private Button hobby;
	private Button major;
	private Button region;
	private Button quit;
	
	private LinkedList<GUIGlyph> glyphs;
	
    public GUIVisualization(LinkedList<GUIGlyph> glyphs) 
    {
        window = new Window("Data Visualization ama2106 jaydee1 mks2752");
        position = 0;
        sortedBy = SortMethod.UNSORTED;
        previous = new Button("<< Previous");
        next = new Button("Next >>");
        title = new Button("Sort By Song Title");
        artist = new Button("Sort By Artist Name");
        genre = new Button("Sort By Genre");
        year = new Button("Sort By Release Year");
        hobby = new Button("Represent Hobby");
        region = new Button("Represent Region");
        major = new Button("Represent Major");
        quit = new Button("Quit");
        
        previous.onClick(this);
        next.onClick(this);
        title.onClick(this);
        artist.onClick(this);
        genre.onClick(this);
        year.onClick(this);
        hobby.onClick(this);
        region.onClick(this);
        major.onClick(this);
        quit.onClick(this);
        
        window.addButton(previous, WindowSide.NORTH);
        window.addButton(artist, WindowSide.NORTH);
        window.addButton(title, WindowSide.NORTH);
        window.addButton(year, WindowSide.NORTH);
        window.addButton(genre, WindowSide.NORTH);
        window.addButton(next, WindowSide.NORTH);
        window.addButton(hobby, WindowSide.SOUTH);
        window.addButton(major, WindowSide.SOUTH);
        window.addButton(region, WindowSide.SOUTH);
        window.addButton(quit, WindowSide.SOUTH);
        
        this.glyphs = glyphs;
        
        sortedBy = SortMethod.TITLE;
    }

}
