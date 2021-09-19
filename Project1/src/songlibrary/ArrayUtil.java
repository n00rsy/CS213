package songlibrary;

import java.util.Comparator;

/**
 * A class containing helper methods for array operations.
 *
 * @author Noor
 */
public class ArrayUtil {

    /**
     * Prints the contents of an array from start to end-1.
     *
     * @param array the array to iterate through
     * @param start start index
     * @param end   end index
     */
    public static void print(Object[] array, int start, int end) {
        for (int i = start; i < end; i++) {
            System.out.println(array[i].toString());
        }
    }

    /**
     * Copies the contents of an array to a new array.
     *
     * @param array the array to copy
     * @param start start index
     * @param end   end index
     * @return the new array with copied elements.
     */
    public static Object[] copy(Object[] array, int start, int end) {
        Object[] newArray = new Object[end - start];

        for (int i = start; i < end; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * Sorts the contents of the array using insertion sort.
     *
     * @param array the array to sort
     * @param C     the comparator to use while sorting
     */
    public static void insertionSort(Object[] array, Comparator<Object> C) {
        for (int i = 0; i < array.length; i++) {
            Object current = array[i];
            int j = i - 1;
            while (j >= 0 && C.compare(current, array[j]) < 1) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }
}
