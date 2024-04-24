package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Contains algorithms for sorting and searching arrays.
 */
public class Algorithms {

    // Quicksort implementation for sorting an array of Comparable elements
    public static <E extends Comparable<E>> void fastSort(E[] array) {
        if (array == null || array.length <= 1) {
            return; // Nothing to sort
        }
        quickSort(array, 0, array.length - 1);
    }

    private static <E extends Comparable<E>> void quickSort(E[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private static <E extends Comparable<E>> int partition(E[] array, int begin, int end) {
        E pivot = array[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (array[j].compareTo(pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    private static <E> void swap(E[] array, int i, int j) {
        E temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Binary search for a sorted array
    public static <T extends Comparable<T>> int binarySearch(T value, T[] array) {
        return binarySearch(value, array, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> int binarySearch(T value, T[] array, int fromIndex, int toIndex) {
        if (array == null || fromIndex > toIndex || fromIndex < 0 || toIndex >= array.length) {
            throw new IllegalArgumentException("Invalid indices or array.");
        }

        while (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            int compareResult = value.compareTo(array[mid]);

            if (compareResult == 0) {
                return mid; // Found the value
            } else if (compareResult < 0) {
                toIndex = mid - 1;
            } else {
                fromIndex = mid + 1;
            }
        }

        return -1; // Element not found
    }

    // Comparator-based sorting
    public static <E> void sortWithComparator(E[] array, Comparator<E> comparator) {
        if (array != null && comparator != null) {
            Arrays.sort(array, comparator);
        }
    }

}