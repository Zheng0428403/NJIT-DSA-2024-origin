package oy.tol.tra;

public class Algorithms {

    public static void main(String[] args) {
        // Example to demonstrate sorting and searching
        Integer[] arr = {1, 5, 3, 4, 7, 8, 9, 6, 2, 0};
        System.out.print("The array before sorting is: ");
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
        // Sorting
        fastSort(arr);
        System.out.println();
        System.out.print("The array after sorting is: ");
        for (Integer i : arr) {
            System.out.print(i + " ");
        }
        // Binary search
        int index = binarySearch(5, arr, 0, arr.length - 1);
        System.out.println("\nIndex of 5 after sorting is: " + index);
    }

    public static <E extends Comparable<E>> void fastSort(E[] array) {
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

    public static <T extends Comparable<T>> int binarySearch(T value, T[] array, int fromIndex, int toIndex) {
        while (fromIndex <= toIndex) {
            int mid = fromIndex + (toIndex - fromIndex) / 2;
            int compareResult = value.compareTo(array[mid]);
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                toIndex = mid - 1;
            } else {
                fromIndex = mid + 1;
            }
        }
        return -1; // Element not found
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    swap(array, i, j);
                }
            }
        }
    }

    public static <T> void reverse(T[] array) {
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
    }

    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
   