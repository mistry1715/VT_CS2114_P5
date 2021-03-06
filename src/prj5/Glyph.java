package prj5;

import prj5.enumeration.SortMethod;

/**
 * Each Glyph object contains the core data that is used in the program. Per
 * each Glyph, there is a Song object and various LinkedList objects containing
 * the Student objects relating to that given Song (i.e. for each student that
 * responded to the given song in the survey, add them to the appropriate list
 * based on whether or not they heard the song and whether or not they liked the
 * song). These values can be sorted depending on the desired category into
 * percentage values.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class Glyph 
{
    private SortMethod sortedBy;

    private LinkedList<Student> liked;
    private LinkedList<Student> heard;
    private LinkedList<Student> ignoredLiked;
    private LinkedList<Student> ignoredHeard;
    private int[] likedByCat;
    private int[] heardByCat;
    private int[] totalByCat;
    private Song song;

    /**
     * Create a new Glyph object with the given Song parameter.
     * 
     * @param song - create a new Glyph with the given Song parameter
     */
    public Glyph(Song song) {
        this.song = song;
        sortedBy = SortMethod.UNSORTED;

        liked = new LinkedList<Student>();
        heard = new LinkedList<Student>();
        ignoredLiked = new LinkedList<Student>();
        ignoredHeard = new LinkedList<Student>();
        likedByCat = new int[4];
        heardByCat = new int[4];
        totalByCat = new int[8];
    }

    /**
     * Add a new Student to the LinkedList containing students who replied "yes"
     * to if they liked the song.
     * 
     * @param student - Student object to add to LinkedList liked
     */
    public void addLiked(Student student) {
        liked.add(student);
    }

    /**
     * Add a new Student to the LinkedList containing students who replied "yes"
     * to if they heard the song.
     * 
     * @param student - Student object to add to LinkedList heard
     */
    public void addHeard(Student student) {
        heard.add(student);
    }

    /**
     * Add a new Student to the LinkedList containing students who did not reply
     * to if they liked the song.
     * 
     * @param student - Student object to add to LinkedList ignoredLiked
     */
    public void addIgnoredLiked(Student student) {
        ignoredLiked.add(student);
    }

    /**
     * Add a new Student to the LinkedList containing students who did not reply
     * to if they liked the song.
     * 
     * @param student - Student object to add to LinkedList ignoredHeard
     */
    public void addIgnoredHeard(Student student) {
        ignoredHeard.add(student);
    }

    /**
     * "Sort" the current Glyph using the given sortMethod enumeration.
     * 
     * @param sortMethod - the method of sorting to call method sort() with.
     */
    public void sortBy(SortMethod sortMethod) {
        switch (sortMethod) {
            case REGION:
                // sort(new RegionComparator());
                recalcRegion();
                break;
            case MAJOR:
                // sort(new MajorComparator());
                recalcMajor();
                break;
            case HOBBY:
                // sort(new HobbyComparator());
                recalcHobby();
                break;
            default:
                break;
        }

        sortedBy = sortMethod;
    }

    /**
     * Calculate the percentages by category of the total Student data. The
     * arrays heardByCat and likedByCat are divided by their respective
     * totalByCat to calculate a percentage of "heard" and "like" per given
     * category.
     * 
     * @return a new array of double values containing the percentages by
     *         category
     */
    public double[] percentsByCat() {
        double[] percentages = new double[8];
        percentages[0] = (double) heardByCat[0] / totalByCat[0];
        percentages[1] = (double) heardByCat[1] / totalByCat[1];
        percentages[2] = (double) heardByCat[2] / totalByCat[2];
        percentages[3] = (double) heardByCat[3] / totalByCat[3];
        percentages[4] = (double) likedByCat[0] / totalByCat[4];
        percentages[5] = (double) likedByCat[1] / totalByCat[5];
        percentages[6] = (double) likedByCat[2] / totalByCat[6];
        percentages[7] = (double) likedByCat[3] / totalByCat[7];
        return percentages;
    }

    /* OMITTED - Unnecessary given the recalc() methods.
     * TODO This may also mean that we do not need the comparators
     * for these sorts.
     *
    /**
     * Insertion Sort with the given comparator.
     * 
     * @param com - the comparator to sort by.
     *
    private void sort(Comparator<Student> com) 
    {
        
    }
    */

    /**
     * Get the Song object being used by the current Glyph.
     * 
     * @return the Song object being used by the current Glyph
     */
    public Song getSong() {
        return this.song;
    }

    /**
     * Get the current sort for the object.
     */
    public SortMethod sortedBy()
    {
        return sortedBy;
    }
    
    /**
     * Recalculates the numbers in the appropriate arrays, after the glyph has
     * been resorted.
     */
    private void recalcHobby() {
        likedByCat = new int[4];
        heardByCat = new int[4];
        totalByCat = new int[8];
        for (Student s : liked) {
            switch (s.getHobby()) {
                case READ:
                    likedByCat[0]++;
                    break;
                case ART:
                    likedByCat[1]++;
                    break;
                case SPORTS:
                    likedByCat[2]++;
                    break;
                case MUSIC:
                    likedByCat[3]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : heard) {
            switch (s.getHobby()) {
                case READ:
                    heardByCat[0]++;
                    break;
                case ART:
                    heardByCat[1]++;
                    break;
                case SPORTS:
                    heardByCat[2]++;
                    break;
                case MUSIC:
                    heardByCat[3]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : Student.students) {
            switch (s.getHobby()) {
                case READ:
                    totalByCat[0]++;
                    totalByCat[4]++;
                    break;
                case ART:
                    totalByCat[1]++;
                    totalByCat[5]++;
                    break;
                case SPORTS:
                    totalByCat[2]++;
                    totalByCat[6]++;
                    break;
                case MUSIC:
                    totalByCat[3]++;
                    totalByCat[7]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : ignoredHeard) {
            switch (s.getHobby()) {
                case READ:
                    totalByCat[0]--;
                    break;
                case ART:
                    totalByCat[1]--;
                    break;
                case SPORTS:
                    totalByCat[2]--;
                    break;
                case MUSIC:
                    totalByCat[3]--;
                    break;
                default:
                    break;
            }
        }

        for (Student s : ignoredLiked) {
            switch (s.getHobby()) {
                case READ:
                    totalByCat[4]--;
                    break;
                case ART:
                    totalByCat[5]--;
                    break;
                case SPORTS:
                    totalByCat[6]--;
                    break;
                case MUSIC:
                    totalByCat[7]--;
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Recalculates the numbers of the info, based on major.
     */
    private void recalcMajor() {
        likedByCat = new int[4];
        heardByCat = new int[4];
        totalByCat = new int[8];
        for (Student s : liked) {
            switch (s.getMajor()) {
                case CS:
                    likedByCat[0]++;
                    break;
                case MATH:
                    likedByCat[1]++;
                    break;
                case EGR:
                    likedByCat[2]++;
                    break;
                case OTHER:
                    likedByCat[3]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : heard) {
            switch (s.getMajor()) {
                case CS:
                    heardByCat[0]++;
                    break;
                case MATH:
                    heardByCat[1]++;
                    break;
                case EGR:
                    heardByCat[2]++;
                    break;
                case OTHER:
                    heardByCat[3]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : Student.students) {
            switch (s.getMajor()) {
                case CS:
                    totalByCat[0]++;
                    totalByCat[4]++;
                    break;
                case MATH:
                    totalByCat[1]++;
                    totalByCat[5]++;
                    break;
                case EGR:
                    totalByCat[2]++;
                    totalByCat[6]++;
                    break;
                case OTHER:
                    totalByCat[3]++;
                    totalByCat[7]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : ignoredHeard) {
            switch (s.getMajor()) {
                case CS:
                    totalByCat[0]--;
                    break;
                case MATH:
                    totalByCat[1]--;
                    break;
                case EGR:
                    totalByCat[2]--;
                    break;
                case OTHER:
                    totalByCat[3]--;
                    break;
                default:
                    break;
            }
        }

        for (Student s : ignoredLiked) {
            switch (s.getMajor()) {
                case CS:
                    totalByCat[4]--;
                    break;
                case MATH:
                    totalByCat[5]--;
                    break;
                case EGR:
                    totalByCat[6]--;
                    break;
                case OTHER:
                    totalByCat[7]--;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Recalculates the numbers of the info, based on region.
     */
    private void recalcRegion() {
        likedByCat = new int[4];
        heardByCat = new int[4];
        totalByCat = new int[8];
        for (Student s : liked) {
            switch (s.getRegion()) {
                case NE:
                    likedByCat[0]++;
                    break;
                case SE:
                    likedByCat[1]++;
                    break;
                case US:
                    likedByCat[2]++;
                    break;
                case OUT:
                    likedByCat[3]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : heard) {
            switch (s.getRegion()) {
                case NE:
                    heardByCat[0]++;
                    break;
                case SE:
                    heardByCat[1]++;
                    break;
                case US:
                    heardByCat[2]++;
                    break;
                case OUT:
                    heardByCat[3]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : Student.students) {
            switch (s.getRegion()) {
                case NE:
                    totalByCat[0]++;
                    totalByCat[4]++;
                    break;
                case SE:
                    totalByCat[1]++;
                    totalByCat[5]++;
                    break;
                case US:
                    totalByCat[2]++;
                    totalByCat[6]++;
                    break;
                case OUT:
                    totalByCat[3]++;
                    totalByCat[7]++;
                    break;
                default:
                    break;
            }
        }

        for (Student s : ignoredHeard) {
            switch (s.getRegion()) {
                case NE:
                    totalByCat[0]--;
                    break;
                case SE:
                    totalByCat[1]--;
                    break;
                case US:
                    totalByCat[2]--;
                    break;
                case OUT:
                    totalByCat[3]--;
                    break;
                default:
                    break;
            }
        }

        for (Student s : ignoredLiked) {
            switch (s.getRegion()) {
                case NE:
                    totalByCat[4]--;
                    break;
                case SE:
                    totalByCat[5]--;
                    break;
                case US:
                    totalByCat[6]--;
                    break;
                case OUT:
                    totalByCat[7]--;
                    break;
                default:
                    break;
            }
        }
    }
    
    /**
     * Creates a GUI Glyph for this object.
     */
    public void makeGUIGlyph()
    {
        new GUIGlyph(this);
    }
    
    /* OMITTED - Output methods for intermediate submission not needed any more
     * 
     *
     * METHODS FOR OUTPUT PRINTING FOR INTERMEDIATE SUBMISSION
     *
     *
     * Print the current Glyph data to the console. This data includes the
     * current Song object's parameters (title, artist, genre, year) and the
     * total numbers of "heard" and "like" per category.
     * 
     * METHOD USED FOR OUTPUT PRINTING FOR INTERMEDIATE SUBMISSION.
     *
     * public void printOutData() {
     *   System.out.println("Song Title: " + song.getTitle());
     *   System.out.println("Song Artist: " + song.getArtist());
     *   System.out.println("Song Genre: " + song.getGenre());
     *   System.out.println("Song Year: " + song.getYear());
     *   System.out.println("Heard");
     *   System.out.println(percentHeardByCat());
     *   System.out.println("Likes");
     *   System.out.println(percentLikedByCat());
     * }
     *
     *
     * Create a new string of "like" percentages per category for use by
     * printOutData().
     * 
     * METHOD USED FOR OUTPUT PRINTING FOR INTERMEDIATE SUBMISSION.
     * 
     * @return a string containing "like" percentages per category
     *
     * public String percentLikedByCat() {
     *   StringBuilder sb = new StringBuilder();
     *   sb.append("reading:");
     *   sb.append((int) percentsByCat()[4]);
     *   sb.append(" art:");
     *   sb.append((int) percentsByCat()[5]);
     *   sb.append(" sports:");
     *   sb.append((int) percentsByCat()[6]);
     *   sb.append(" music:");
     *   sb.append((int) percentsByCat()[7]);
     *   return sb.toString();
     * }
     *
     *
     * Create a new string of "heard" percentages per category for use by
     * printOutData().
     * 
     * METHOD USED FOR OUTPUT PRINTING FOR INTERMEDIATE SUBMISSION.
     * 
     * @return a string containing "heard" percentages per category
     *
     * public String percentHeardByCat() {
     *   StringBuilder sb = new StringBuilder();
     *   sb.append("reading:");
     *   sb.append((int) percentsByCat()[0]);
     *   sb.append(" art:");
     *   sb.append((int) percentsByCat()[1]);
     *   sb.append(" sports:");
     *   sb.append((int) percentsByCat()[2]);
     *   sb.append(" music:");
     *   sb.append((int) percentsByCat()[3]);
     *   return sb.toString();
     * }
     *
     */
}
