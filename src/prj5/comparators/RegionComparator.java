package prj5.comparators;

import java.util.Comparator;
import prj5.*;

/**
 * Comparator to compare the region parameter of two given Student objects.
 * 
 * @author Alec Alderman (ama2106)
 * @author Julian Davila (jaydee1)
 * @author Matthew Scanland (mks2752)
 * @version 2015.11.16
 */
public class RegionComparator implements Comparator<Student> {

    /**
     * Compare the given students' regions and return a numerical result.
     * 
     * @param s1 - first Student object for comparison
     * @param s2 - second Student object for comparison
     * @return an integer result based on the comparison
     */
    @Override
    public int compare(Student s1, Student s2) {
        return s1.getRegion().compareTo(s2.getRegion());
    }

}
