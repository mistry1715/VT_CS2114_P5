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
<<<<<<< HEAD
    // protected static LinkedList<Student> students;
    private String name;
=======
    public static LinkedList<Student> students = new LinkedList<>();
>>>>>>> 465a2184f7f2c53b54b5e5e07c69409e0473ba9e
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
     * @throws IllegalArgumentException if an invalid name is given (i.e. an
     *         empty string value)
     */
    public Student(String name, MAJOR major, REGION region, HOBBY hobby) {
        if (name == "") {
            throw new IllegalArgumentException("Empty string given.");
        }
        this.name = name;
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
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        Student other;
        if (obj.getClass() == this.getClass()) {
            other = (Student) obj;
            return other.name == this.name && other.major == this.major
                    && other.region == this.region && other.hobby == this.hobby;
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
        builder.append(name + "," + major + "," + region + "," + hobby);
        builder.append("]");
        return builder.toString();
    }

    /**
     * Return the student's name.
     * 
     * @return the student's name
     */
    public String getName() {
        return name;
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
