package prj5;

import java.awt.Color;
import java.util.Comparator;

import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import prj5.comparators.ArtistComparator;
import prj5.comparators.TitleComparator;
import prj5.comparators.YearComparator;
import prj5.enumeration.SortMethod;

/**
 * This class represents a glyph for display in the GUI.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class GUIGlyph 
{
    private Glyph stats;
    private Shape[] dataBars;
    private TextShape[] songData;
    private Shape separator;
    private static int songDataSelector;

    private static final int MAX_BAR_SIZE = 100;
    private static final int PADDING_X = 5;
    private static final int PADDING_Y = 5;
    private static final int SEP_WIDTH = 5;
    private static final int SEP_HEIGHT = 40;
    private static final int GLYPH_WIDTH = MAX_BAR_SIZE * 2 + SEP_WIDTH;
    private static final int GLYPH_HEIGHT = 90;
    
    private static LinkedList<GUIGlyph> glyphs = new LinkedList<>();
    private static int page = 1;
    private static SortMethod sortedBy = SortMethod.HOBBY;

    /**
     * The constructor for the GUI object.
     * @param glyph - The glyph object that this GUI object will get its
     * statistics from.
     */
    public GUIGlyph(Glyph glyph) 
    {
        this.stats = glyph;
        stats.sortBy(SortMethod.HOBBY);
        glyphs.add(this);
        songData = new TextShape[4];
        songData[0] = new TextShape(0, 0, stats.getSong().getTitle());
        songData[1] = new TextShape(0, 0, "by " 
                + stats.getSong().getArtist());
        songData[2] = new TextShape(0, 0, "Year Released: " 
                + stats.getSong().getYear());
        songData[3] = new TextShape(0, 0, "genre: " 
                + stats.getSong().getGenre());
        songData[0].setBackgroundColor(Color.WHITE);
        songData[1].setBackgroundColor(Color.WHITE);
        songData[2].setBackgroundColor(Color.WHITE);
        songData[3].setBackgroundColor(Color.WHITE);
        dataBars = new Shape[8];
        songDataSelector = 1;
    }
    
    /**
     * Sort the list of GUIGlyphs by the comparator passed in.
     * @param com - The comparator to sort by.
     */
    public static void sortGlyphs(Comparator<Song> com)
    {
        LinkedList<GUIGlyph> sorted = new LinkedList<GUIGlyph>();
        sorted.add(glyphs.remove(0));
        while (!glyphs.isEmpty()) {
            int max = sorted.size();
            GUIGlyph song = glyphs.remove(0);
            int i = 0;
            for (GUIGlyph compare : sorted) {
                if (com.compare(song.stats.getSong(), compare.stats.getSong()) <= 0) {
                    sorted.add(i, song);
                    break;
                }
                i++;
                if (i == max) {
                    sorted.add(song);
                    break;
                }
            }
        }

        glyphs = sorted;
        
        if (com instanceof TitleComparator)
        {
            songDataSelector = 1;
        }
        else if (com instanceof ArtistComparator)
        {
            songDataSelector = 1;
        }
        else if (com instanceof YearComparator)
        {
            songDataSelector = 2;
        }
        else
        {
            songDataSelector = 3;
        }
        
        sortStat(sortedBy);
    }
    
    /**
     * This method recalculates the data in the Glyph object that the 
     * GUIGlyph is using for stats with the given sort method. This method
     * then calls a private helper method within this class that will
     * update the bar shapes for the new sort method.
     * @param sortBy - The sort method to sort the students by
     */
    public static void sortStat(SortMethod sortBy)
    {
        for (GUIGlyph g : glyphs)
        {
            g.stats.sortBy(sortBy);
        }
        sortedBy = sortBy;
    }
    
    /**
     * This method displays the glyphs on the current page on the given window.
     * @param window - The window to display the glyphs in.
     */
    public static void display(Window window)
    {
        window.removeAllShapes();
        
        LinkedList<GUIGlyph> display = new LinkedList<>(); 
        for (int index = (page - 1) * 9; index < page * 9; index++)
        {
            display.add(glyphs.get(index));
        }
        
        int i = 1;
        for (GUIGlyph g : display)
        {
            g.displayAt(window, i);
            i++;
        }
    }
    
    /**
     * Displays the individual glyph on the window specified and the location
     * specified.
     * @param window - The window to display this glyph.
     * @param location - The location (1-9) where to display the glyph.
     */
    private void displayAt(Window window, int location)
    {
        recreate(location);
        
        window.addShape(separator);
        
        for (Shape s : dataBars)
        {
            window.addShape(s);
        }
        
        window.addShape(songData[0]);
        window.addShape(songData[songDataSelector]);
    }
    
    /**
     * Goes to the next page and automatically displays the next page
     * if there is a page available.
     * @param window - The window to display the glyph in.
     */
    public static void nextPage(Window window)
    {
        if (hasNext())
        {
            page++;
            display(window);
        }
    }
    
    /**
     * Goes to the previous page and automatically displays the previous page
     * if there is a page available.
     * @param window - The window to display the glyph in.
     */
    public static void previousPage(Window window)
    {
        if (hasPrevious())
        {
            page--;
            display(window);
        }
    }
    
    /**
     * This method will recalculate the bar sizes when the data is "resorted"
     * by the sortStat() method.
     */
    private void recreate(int location)
    {
        int[] coor = getCoordinates(location);
        int yline = coor[1];
        // Do the title text shape.
        songData[0].setX(coor[0] + ((GLYPH_WIDTH / 2) 
                - (songData[0].getWidth() / 2)));
        songData[0].setY(yline);
        yline += songData[0].getHeight();
        // Do the subtitle text shape.
        songData[songDataSelector].setX(coor[0] 
                + ((GLYPH_WIDTH / 2) - (songData[songDataSelector].getWidth() / 2)));
        songData[songDataSelector].setY(yline);
        yline += songData[songDataSelector].getHeight();
        yline += 5;
        // Do the separator.
        separator = new Shape(coor[0] + MAX_BAR_SIZE, yline, SEP_WIDTH, SEP_HEIGHT);
        separator.setBackgroundColor(Color.BLACK);
        separator.setForegroundColor(Color.BLACK);
        // Do the bars... -_-
        double[] percents = stats.percentsByCat();
        dataBars[0] = new Shape(coor[0] + MAX_BAR_SIZE - (int)(MAX_BAR_SIZE * percents[0]), 
                yline, 
                (int)(MAX_BAR_SIZE * percents[0]), 
                10);
        dataBars[0].setBackgroundColor(Color.MAGENTA);
        dataBars[0].setBackgroundColor(Color.MAGENTA);
        dataBars[1] = new Shape(coor[0] + MAX_BAR_SIZE + SEP_WIDTH, 
                yline, 
                (int)(MAX_BAR_SIZE * percents[4]), 
                10);
        dataBars[1].setBackgroundColor(Color.MAGENTA);
        dataBars[1].setBackgroundColor(Color.MAGENTA);
        yline += 10;
        
        dataBars[2] = new Shape(coor[0] + MAX_BAR_SIZE - (int)(MAX_BAR_SIZE * percents[1]), 
                yline, 
                (int)(MAX_BAR_SIZE * percents[1]), 
                10);
        dataBars[2].setBackgroundColor(Color.BLUE);
        dataBars[2].setBackgroundColor(Color.BLUE);
        dataBars[3] = new Shape(coor[0] + MAX_BAR_SIZE + SEP_WIDTH, 
                yline, 
                (int)(MAX_BAR_SIZE * percents[5]), 
                10);
        dataBars[3].setBackgroundColor(Color.BLUE);
        dataBars[3].setBackgroundColor(Color.BLUE);
        yline += 10;
        
        dataBars[4] = new Shape(coor[0] + MAX_BAR_SIZE - (int)(MAX_BAR_SIZE * percents[2]), 
                yline, 
                (int)(MAX_BAR_SIZE * percents[2]), 
                10);
        dataBars[4].setBackgroundColor(Color.YELLOW);
        dataBars[4].setBackgroundColor(Color.YELLOW);
        dataBars[5] = new Shape(coor[0] + MAX_BAR_SIZE + SEP_WIDTH, 
                yline, 
                (int)(MAX_BAR_SIZE * percents[6]), 
                10);
        dataBars[5].setBackgroundColor(Color.YELLOW);
        dataBars[5].setBackgroundColor(Color.YELLOW);
        yline += 10;
        
        dataBars[6] = new Shape(coor[0] + MAX_BAR_SIZE - (int)(MAX_BAR_SIZE * percents[3]), 
                yline, 
                (int)(MAX_BAR_SIZE * percents[3]), 
                10);
        dataBars[6].setBackgroundColor(Color.GREEN);
        dataBars[6].setBackgroundColor(Color.GREEN);
        dataBars[7] = new Shape(coor[0] + MAX_BAR_SIZE + SEP_WIDTH, 
                yline, 
                (int)(MAX_BAR_SIZE * percents[7]), 
                10);
        dataBars[7].setBackgroundColor(Color.GREEN);
        dataBars[7].setBackgroundColor(Color.GREEN);
    }
    
    /**
     * Gets the X,Y coordinates of the location specified for displaying
     * the glyph.
     * @param location - An integer representation of the location of the
     * glyph in the window starting from the top left and going right until
     * the end of the 3x3 window.
     * @return Returns an array with the X and Y coordinates in integer format.
     */
    private int[] getCoordinates(int location)
    {
        int[] coordinates = new int[2];
        int xGrid = (location % 3);
        int yGrid = ((location - 1) / 3);
        coordinates[0] = (PADDING_X * xGrid) + (GLYPH_WIDTH * xGrid);
        coordinates[1] = (PADDING_Y * yGrid) + (GLYPH_HEIGHT * yGrid);
        return coordinates;
    }

    /**
     * Checks to see if another page can be made with the glyphs in the list.
     * @return True if there is another page.
     */
    public static boolean hasNext()
    {
        return page < glyphs.size() / 9;
    }
    
    /**
     * Checks to see if another page can be made with the glyphs in the list.
     * @return True if there can be another page.
     */
    public static boolean hasPrevious()
    {
        return page > 1;
    }
}
