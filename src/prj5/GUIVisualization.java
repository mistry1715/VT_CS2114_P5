package prj5;

import java.awt.Color;
import CS2114.Button;
import CS2114.Shape;
import CS2114.TextShape;
import CS2114.Window;
import CS2114.WindowSide;
import prj5.comparators.ArtistComparator;
import prj5.comparators.GenreComparator;
import prj5.comparators.TitleComparator;
import prj5.comparators.YearComparator;
import prj5.enumeration.SortMethod;

/**
 * GUIVisualization window
 *
 * @author Matthew Scanland (mks2752)
 *
 */
public class GUIVisualization {
    private Window window;
    private SortMethod represent;
    private TextShape notSorted;
    private TextShape[] shapeArray;
    private int width;
    private int height;
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

    /**
     * builds the window
     *
     * @param glyphs glyphs to be built
     */
    public GUIVisualization(LinkedList<Glyph> glyphs) {
        for (Glyph g : glyphs) {
            g.makeGUIGlyph();
        }

        window = new Window("Data Visualization ama2106 jaydee1 mks2752");

        updateWindowSize();

        represent = SortMethod.HOBBY;
        notSorted = new TextShape(width / 2, height / 2,
                "Please select how to represent songs");
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
        shapeArray = new TextShape[12];

        previous.onClick(this, "clickedPrevious");
        next.onClick(this, "clickedNext");
        title.onClick(this, "clickedTitle");
        artist.onClick(this, "clickedArtist");
        genre.onClick(this, "clickedGenre");
        year.onClick(this, "clickedYear");
        hobby.onClick(this, "clickedHobby");
        region.onClick(this, "clickedRegion");
        major.onClick(this, "clickedMajor");
        quit.onClick(this, "clickedQuit");

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
        if (!GUIGlyph.hasNext()) {
            next.disable();
        }
        previous.disable();

        notSorted.setBackgroundColor(Color.RED);

        buildShapeArray();
        GUIGlyph.display(window);
        buildLegend(SortMethod.HOBBY);
    }

    /**
     * builds the legend
     *
     * @param sortBy what is sorted by
     */
    public void buildLegend(SortMethod sortBy) {

        updateWindowSize();

        // build box around legend and general info
        window.addShape(
                new Shape(width - 120, height - 200, 120, 5, Color.BLACK));
        window.addShape(
                new Shape(width - 5, height - 200, 5, 200, Color.BLACK));
        window.addShape(
                new Shape(width - 120, height - 5, 120, 5, Color.BLACK));
        window.addShape(
                new Shape(width - 120, height - 200, 5, 200, Color.BLACK));

        // build lower portion of legend
        TextShape tempTextShape = new TextShape(width - 115, height - 195,
                getLegendType(sortBy) + " Legend");
        tempTextShape.setBackgroundColor(Color.WHITE);
        Shape tempSeperator = new Shape(width - 50, height - 35, 5, 30,
                Color.BLACK);
        window.addShape(tempTextShape);
        tempTextShape = new TextShape(width - 100, height - 30, "Heard");
        tempTextShape.setBackgroundColor(Color.WHITE);
        window.addShape(tempTextShape);
        window.addShape(tempSeperator);
        tempTextShape = new TextShape(width - 50, height - 30, "Likes");
        tempTextShape.setBackgroundColor(Color.WHITE);
        window.addShape(tempTextShape);
        tempTextShape = new TextShape(width - 90, height - 50, "Song Title");
        tempTextShape.setBackgroundColor(Color.WHITE);
        window.addShape(tempTextShape);

        switch (represent) {
            case HOBBY:
                for (int i = 0; i < 4; i++) {
                    window.addShape(shapeArray[i]);
                }
                break;
            case MAJOR:
                for (int i = 4; i < 8; i++) {
                    window.addShape(shapeArray[i]);
                }
                break;
            case REGION:
                for (int i = 8; i < 12; i++) {
                    window.addShape(shapeArray[i]);
                }
                break;
            default:
                window.addShape(new TextShape(1, 1, "something random"));
        }

        if (GUIGlyph.hasNext()) {
            next.enable();
        }
        if (GUIGlyph.hasPrevious()) {
            previous.enable();
        }
    }

    /**
     * clicked the previous button
     */
    public void clickedPrevious(Button button) {
        updateWindowSize();
        buildShapeArray();
        if (!GUIGlyph.hasPrevious()) {
            previous.disable();
        }
        next.enable();
        GUIGlyph.previousPage(window);
        GUIGlyph.display(window);
        buildLegend(represent);
        if (!GUIGlyph.hasPrevious()) {
            previous.disable();
        }
    }

    /**
     * clicked the next button
     */
    public void clickedNext(Button button) {
        updateWindowSize();
        buildShapeArray();
        if (!GUIGlyph.hasNext()) {
            next.disable();
        }
        previous.enable();
        GUIGlyph.nextPage(window);
        GUIGlyph.display(window);
        buildLegend(represent);
        if (!GUIGlyph.hasNext()) {
            next.disable();
        }
    }

    /**
     * clicked the artist button
     */
    public void clickedArtist(Button button) {
        updateWindowSize();
        buildShapeArray();
        GUIGlyph.sortGlyphs(new ArtistComparator());
        GUIGlyph.display(window);
        buildLegend(represent);
    }

    /**
     * clicked the title button
     */
    public void clickedTitle(Button button) {
        updateWindowSize();
        buildShapeArray();
        GUIGlyph.sortGlyphs(new TitleComparator());
        GUIGlyph.display(window);
        buildLegend(represent);
    }

    /**
     * clicked the year button
     */
    public void clickedYear(Button button) {
        updateWindowSize();
        buildShapeArray();
        GUIGlyph.sortGlyphs(new YearComparator());
        GUIGlyph.display(window);
        buildLegend(represent);
    }

    /**
     * clicked the genre button
     */
    public void clickedGenre(Button button) {
        updateWindowSize();
        buildShapeArray();
        GUIGlyph.sortGlyphs(new GenreComparator());
        GUIGlyph.display(window);
        buildLegend(represent);
    }

    /**
     * clicked the hobby button
     */
    public void clickedHobby(Button button) {
        represent = SortMethod.HOBBY;
        updateWindowSize();
        buildShapeArray();
        GUIGlyph.sortStat(SortMethod.HOBBY);
        GUIGlyph.display(window);
        buildLegend(SortMethod.HOBBY);
    }

    /**
     * clicked the major button
     */
    public void clickedMajor(Button button) {
        represent = SortMethod.MAJOR;
        updateWindowSize();
        buildShapeArray();
        GUIGlyph.sortStat(SortMethod.MAJOR);
        GUIGlyph.display(window);
        buildLegend(SortMethod.MAJOR);
    }

    /**
     * clicked the region button
     */
    public void clickedRegion(Button button) {
        represent = SortMethod.REGION;
        updateWindowSize();
        buildShapeArray();

        GUIGlyph.sortStat(SortMethod.REGION);
        GUIGlyph.display(window);
        buildLegend(SortMethod.REGION);
    }

    /**
     * tests the quit button
     */
    public void clickedQuit(Button button) {
        System.exit(0);
    }

    /**
     * TODO
     *
     * @param sortBy
     *
     * @return
     */
    private String getLegendType(SortMethod sortBy) {
        String legendType = "";
        switch (sortBy) {
            case HOBBY:
                legendType = "Hobby";
                break;
            case MAJOR:
                legendType = "Major";
                break;
            case REGION:
                legendType = "Region";
                break;
            default:
                legendType = "Unsorted";
                break;
        }

        return legendType;
    }

    /**
     * builds the shapeArray, 0-3 hobby, 4-7 major, 8-11 region
     */
    private void buildShapeArray() {
        updateWindowSize();
        // 0 through 3 hobby
        TextShape text = null;
        text = new TextShape(width - 115, height - 170, "Read");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.MAGENTA);
        shapeArray[0] = text;
        text = new TextShape(width - 115, height - 140, "Art");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.BLUE);
        shapeArray[1] = text;
        text = new TextShape(width - 115, height - 110, "Sports");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.YELLOW);
        shapeArray[2] = text;
        text = new TextShape(width - 115, height - 80, "Music");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.GREEN);
        shapeArray[3] = text;

        // 4 through 7 major
        text = new TextShape(width - 115, height - 170, "Comp Sci");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.MAGENTA);
        shapeArray[4] = text;
        text = new TextShape(width - 115, height - 140, "Math/CDMA");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.BLUE);
        shapeArray[5] = text;
        text = new TextShape(width - 115, height - 110, "Other Eng");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.YELLOW);
        shapeArray[6] = text;
        text = new TextShape(width - 115, height - 80, "Other");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.GREEN);
        shapeArray[7] = text;

        // 8 through 11 region
        text = new TextShape(width - 115, height - 170, "Southeast");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.MAGENTA);
        shapeArray[8] = text;
        text = new TextShape(width - 115, height - 140, "Northeast");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.BLUE);
        shapeArray[9] = text;
        text = new TextShape(width - 115, height - 110, "US(Other)");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.YELLOW);
        shapeArray[10] = text;
        text = new TextShape(width - 115, height - 80, "non-US");
        text.setBackgroundColor(Color.WHITE);
        text.setForegroundColor(Color.GREEN);
        shapeArray[11] = text;
    }

    private void updateWindowSize() {
        height = window.getGraphPanelHeight();
        width = window.getGraphPanelWidth();
    }

}