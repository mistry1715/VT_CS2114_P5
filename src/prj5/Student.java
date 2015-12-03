package prj5;

import prj5.comparators.*;
import prj5.enumeration.*;

/**
 * Each Student object contains the data given by a text file containing student
 * responses. The responses regarding major, region, and hobby are stored by
 * each object in the form of an enumeration to be used later while sorting.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class Student {
    /**
     * Static list of Student objects to be updated while sorting.
     */
    public static LinkedList<Student> students = new LinkedList<>();
    private Major major;
    private Region region;
    private Hobby hobby;

    /**
     * Create a new Student object with the given MAJOR, REGION, and HOBBY
     * enumerators.
     * 
     * @param major - student's major
     * @param region - student's region
     * @param hobby - student's hobby
     * @throws IllegalArgumentException if an invalid name is given (i.e. an
     *         empty string value)
     */
    public Student(Major major, Region region, Hobby hobby) {
        this.major = major;
        this.region = region;
        this.hobby = hobby;
    }

    /**
     * Compare the given object parameter to the calling object.
     * 
     * @param obj - the object to compare with the calling object
     * @return the comparison result
     */
    public int compareTo(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object passed is null.");
        }

        if (obj.getClass() != this.getClass()) {
            throw new IllegalArgumentException("Object passed is not a Song.");
        }

        Student other = (Student) obj;

        return 0;
    }

    /**
     * Check if the given object parameter is equal to the calling object.
     * 
     * @param obj - the object to compare with the calling object
     * @return true if the two objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Student other;
        if (obj.getClass() == this.getClass()) {
            other = (Student) obj;
            return other.major == this.major && other.region == this.region
                    && other.hobby == this.hobby;
        }

        return false;
    }

    /**
     * Convert the Student object to a string of values within a set of
     * brackets.
     * 
     * @return the calling Student object in the form of a string
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        builder.append(major + "," + region + "," + hobby);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Return the student's major.
     * 
     * @return the student's major
     */
    public Major getMajor() {
        return major;
    }

    /**
     * Return the student's region.
     * 
     * @return the student's region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Return the student's hobby
     * 
     * @return the student's hobby
     */
    public Hobby getHobby() {
        return hobby;
    }

}
