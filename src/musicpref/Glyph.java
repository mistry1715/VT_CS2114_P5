package musicpref;

import java.util.Comparator;

import musicpref.comparators.HobbyComparator;
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
    	percentages[0] = (double)heardByCat[0] / total;
    	percentages[1] = (double)heardByCat[1] / total;
    	percentages[2] = (double)heardByCat[2] / total;
    	percentages[3] = (double)heardByCat[3] / total;
    	percentages[4] = (double)likedByCat[0] / total;
    	percentages[5] = (double)likedByCat[1] / total;
    	percentages[6] = (double)likedByCat[2] / total;
    	percentages[7] = (double)likedByCat[3] / total;
    	return percentages;
    }
    
    private void sort(Comparator<Student> com)
    {
    	LinkedList<Student> sorted = new LinkedList<Student>();
		sorted.add(liked.remove(0));
		for (int i = 0; i < sorted.size(); i++)
		{
			Student s = null;
			try
			{
				s = liked.remove(0);
			}
			catch (NullPointerException e)
			{
				break;
			}
			

			if (com.compare(s, sorted.get(i)) >= 0)
			{
				sorted.add(i, s);
			}
			else if (i == sorted.size() - 1)
			{
				sorted.add(s);
			}
		}
		
		liked = sorted;
		
		LinkedList<Student> sorted2 = new LinkedList<Student>();
		sorted.add(heard.remove(0));
		for (int i = 0; i < sorted2.size(); i++)
		{
			Student s = null;
			try
			{
				s = heard.remove(0);
			}
			catch (IndexOutOfBoundsException e)
			{
				break;
			}
			

			if (com.compare(s, sorted2.get(i)) >= 0)
			{
				sorted2.add(i, s);
			}
			else if (i == sorted2.size() - 1)
			{
				sorted2.add(s);
			}
		}
		
		heard = sorted2;
    }
    
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
    	System.out.println("Song Artist: " + song.getTitle());
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
    	return sb.toString();
    }
    
}
