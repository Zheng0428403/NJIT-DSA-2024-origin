package oy.tol.tra;

import java.util.function.Supplier;

public class Algorithms
{
public static void main(String[] args)
{
    int[] arr = {1,5,3,4,7,8,9,6,2,0};
    System.out.print("The array before sorting is：");
    for(int i = 0; i < arr.length; i++){
        System.out.print(arr[i] + " ");
    }
    for(int i = 0; i < arr.length-1; i++) 
    {
        for(int j = 0; j < arr.length - 1 - i; j++) 
        {
            if(arr[j] > arr[j+1]){
                int temp = arr[j];
                arr[j] = arr[j+1];
                arr[j+1] = temp;
            }
        }
    }
    System.out.println();
    System.out.print("The array after sorting is：");
    for(int i = 0; i < arr.length; i++){
        System.out.print(arr[i] + " ");
    }
}
public static <T extends Comparable<T>> int binarySearch(T aValue, T[] fromArray, int fromIndex, int toIndex) {
    while (fromIndex <= toIndex) {
        int mid = fromIndex + (toIndex - fromIndex) / 2;

        int cmp = fromArray[mid].compareTo(aValue);
        
        if (cmp == 0) {
            return mid; 
        } else if (cmp < 0) {
            fromIndex = mid + 1;
        } else {
            toIndex = mid - 1; 
        }
    }
    
    return -1;
}

void searchFromMiddle() {
    Integer[] array = {1, 2, 3, 4, 5};
    int index = Algorithms.binarySearch(3, array, 0, array.length - 1);
    assertEquals(2, index, () -> "Number three must be in the index 2 (middle of the array) in this case.");

    Integer[] array2 = {1, 2, 3, 4, 5, 6};
    index = Algorithms.binarySearch(3, array2, 0, array2.length - 1);
    assertEquals(2, index, () -> "Number three must be in the index 2 (left from middle of the array) in this case.");

    index = Algorithms.binarySearch(4, array2, 0, array2.length - 1);
    assertEquals(3, index, () -> "Number four must be in the index 3 (middle of the array) in this case.");
}
private void assertEquals(int expected, int index, Supplier<String> messageSupplier) {
    
    throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
}
public static int slowLinearSearch(String aValue, String [] fromArray, int fromIndex, int toIndex) {
    for (int index = fromIndex; index < toIndex; index++) {
       if (fromArray[index].equals(aValue)) {
          return index;
       }
    }
    return -1;
 }
public static void sort(String[] array) {
    
    throw new UnsupportedOperationException("Unimplemented method 'sort'");
}
public static void sort(Integer[] array) {
    
    throw new UnsupportedOperationException("Unimplemented method 'sort'");
}
 
}

   