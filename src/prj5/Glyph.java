package prj5;

import java.util.Comparator;
import prj5.comparators.HobbyComparator;
import prj5.comparators.MajorComparator;
import prj5.enumeration.SORT_METHOD;

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
        
        liked = new LinkedList<Student>();
        heard = new LinkedList<Student>();
        ignored = new LinkedList<Student>();
        likedByCat = new int[4];
        heardByCat = new int[4];
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
	    	case REGION:
	    		break;
	    	case MAJOR:
	    		sort(new MajorComparator());
	    		break;
	    	case HOBBY:
	    		sort(new HobbyComparator());
	    		break;
	    	default:
    		break;
    	}
    	
    	recalc();
    	
    	sortedBy = sortMethod;
    }
    
    public double[] percentsByCat()
    {
    	double total = (double)(Student.students.size() - ignored.size());
    	double[] percentages = new double[8];
    	percentages[0] = (double)heardByCat[0] / total * 100;
    	percentages[1] = (double)heardByCat[1] / total * 100;
    	percentages[2] = (double)heardByCat[2] / total * 100;
    	percentages[3] = (double)heardByCat[3] / total * 100;
    	percentages[4] = (double)likedByCat[0] / total * 100;
    	percentages[5] = (double)likedByCat[1] / total * 100;
    	percentages[6] = (double)likedByCat[2] / total * 100;
    	percentages[7] = (double)likedByCat[3] / total * 100;
    	return percentages;
    }
    
    /**
     * Insertion Sort with the given comparator.
     * @param com The comparator to sort by.
     */
    private void sort(Comparator<Student> com)
    {    	
    	recalc();
    }
    
    public Song getSong()
    {
    	return this.song;
    }
    
    /**
     * Recalculates the numbers in the appropriate arrays,
     * after the glyph has been resorted.
     */
    private void recalc()
    {
    	for (Student s : liked)
    	{
	    	switch (s.getHobby())
	    	{
	    	case READ :
	    		likedByCat[0]++;
	    		break;
	    	case ART :
	    		likedByCat[1]++;
	    		break;
	    	case SPORTS :
	    		likedByCat[2]++;
	    		break;
	    	case MUSIC :
	    		likedByCat[3]++;
	    		break;
	    	}
    	}
    	
    	for (Student s : heard)
    	{
	    	switch (s.getHobby())
	    	{
	    	case READ :
	    		heardByCat[0]++;
	    		break;
	    	case ART :
	    		heardByCat[1]++;
	    		break;
	    	case SPORTS :
	    		heardByCat[2]++;
	    		break;
	    	case MUSIC :
	    		heardByCat[3]++;
	    		break;
	    	}
    	}
    }
    
    // METHODS FOR OUTPUT PRINTING FOR INTERMEDIATE SUBMISSION
    public void printOutData()
    {
    	System.out.println("Song Title: " + song.getTitle());
    	System.out.println("Song Artist: " + song.getArtist());
    	System.out.println("Song Genre: " + song.getGenre());
    	System.out.println("Song Year: " + song.getYear());
    	System.out.println("Heard");
    	System.out.println(percentHeardByCat());
    	System.out.println("Likes");
    	System.out.println(percentLikedByCat());
    }
    
    public String percentLikedByCat()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append("reading:");
    	sb.append((int)percentsByCat()[4]);
    	sb.append(" art:");
    	sb.append((int)percentsByCat()[5]);
    	sb.append(" sports:");
    	sb.append((int)percentsByCat()[6]);
    	sb.append(" music:");
    	sb.append((int)percentsByCat()[7]);
    	return sb.toString();
    }
    
    public String percentHeardByCat()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append("reading:");
    	sb.append((int)percentsByCat()[0]);
    	sb.append(" art:");
    	sb.append((int)percentsByCat()[1]);
    	sb.append(" sports:");
    	sb.append((int)percentsByCat()[2]);
    	sb.append(" music:");
    	sb.append((int)percentsByCat()[3]);
    	return sb.toString();
    }
    
}
