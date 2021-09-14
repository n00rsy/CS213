package songlibrary;

import java.util.Comparator;

public class ArrayUtil {

    public static void print (Object[] array, int start, int end) {
        for(int i = start; i < end; i++) {
            System.out.println(array[i].toString());
        }
    }

    public static Object[] copy (Object[] array, int start, int end) {
        Object[] newArray = new Object[end-start];

        for (int i = start; i < end; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    public static void insertionSort (Object[] array, Comparator<Object> C) {
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
