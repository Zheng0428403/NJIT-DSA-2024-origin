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



   