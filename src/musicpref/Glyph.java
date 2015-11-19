package musicpref;

import musicpref.enumeration.SORT_METHOD;

public class Glyph 
{
	private SORT_METHOD sortedBy;
	private LinkedList<Student> liked;
	private LinkedList<Student> heard;
	private LinkedList<Student> ignored;
	private int[] likedByCat;
	private int[] heardByCat;
	private Song song;
	
    public Glyph(Song song) 
    {
        this.song = song;
        sortedBy = SORT_METHOD.UNSORTED;
    }
    
    public void makeGUIGlpyh()
    {
    	GUIVisualization.glyphs.add(new GUIGlyph(this));
    }
    
    public void addLiked(Student student)
    {
    	liked.add(student);
    }
    
    public void addHeard(Student student)
    {
    	heard.add(student);
    }
    
    public void addIgnored(Student student)
    {
    	ignored.add(student);
    }
    
    public void sortBy(SORT_METHOD sortMethod)
    {
    	switch (sortMethod)
    	{
    	
    	}
    	sortedBy = sortMethod;
    }

}
