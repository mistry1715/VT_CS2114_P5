package musicpref;

import musicpref.enumeration.*;

/**
 * TODO
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class Student {
    // public LinkedList<Student> students;
    private String name;
    private MAJOR major;
    private REGION region;
    private HOBBY hobby;

    /**
     * Create a new Student object with the given MAJOR, REGION, and HOBBY
     * enumerators.
     * 
     * @param major - student's major
     * @param region - student's region
     * @param hobby - student's hobby
     */
    public Student(String name, MAJOR major, REGION region, HOBBY hobby) {
        this.name = name;
        this.major = major;
        this.region = region;
        this.hobby = hobby;
        // TODO
    }

    /**
     * Compare the given object parameter to the calling object.
     * 
     * @param obj - the object to compare with the calling object
     * @return the comparison result
     */
    public int compareTo(Object obj) {
        return 0; // TODO
    }

    /**
     * Check if the given object parameter is equal to the calling object.
     * 
     * @param obj - the object to compare with the calling object
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        return false; // TODO
    }

    /**
     * Convert the Student object to a string of values within a set of
     * brackets.
     * 
     * @return the calling Student object in the form of a string
     */
    @Override
    public String toString() {
        String result = "[";

        StringBuilder builder = new StringBuilder();
        builder.append(name + "," + major + "," + region + "," + hobby);

        return result;

        // TODO Will appending the enum print the right value?
    }

    /**
     * Return the student's major.
     * 
     * @return the student's major
     */
    public MAJOR getMajor() {
        return major;
    }

    /**
     * Return the student's region.
     * 
     * @return the student's region
     */
    public REGION getRegion() {
        return region;
    }

    /**
     * Return the student's hobby
     * 
     * @return the student's hobby
     */
    public HOBBY getHobby() {
        return hobby;
    }

}
