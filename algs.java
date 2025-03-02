import java.util.Scanner;
import java.util.Random;

public class algs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of elements in the array(n): ");
        int n = scanner.nextInt();
        System.out.println("Enter the expected number of repititions (r):");
        int r = scanner.nextInt();

        // initialize arrays
        int[] array1 = generateArrayWithRepititions(n,r);
        int[] array2 = new int[array1.length];
        for (int i = 0; i < array1.length; i++) {
            array2[i] = array1[i];
        }

        //Quicksort timing
        long startTimeQS = System.nanoTime();
        quickSort(array1, 0, array1.length - 1);
        long endTimeQS = System.nanoTime();
        System.out.println("QuickSort took " + (endTimeQS - startTimeQS) + " nanoseconds to sort the array");


        //Radix Sort timing
        long startTimeRS = System.nanoTime();
        radixSort(array2);
        long endTimeRS = System.nanoTime();
        System.out.println("RadixSort took " + (endTimeRS - startTimeRS) + " nanoseconds to sort the array");

        scanner.close();
    }

    private static int[] generateArrayWithRepititions(int n, int r) {
        Random random = new Random();
        int[] array = new int[n];
        int i = 0;
        while (i < n) {
            int num = random.nextInt(1_000_000);
            int reps = 1 + random.nextInt(2*r);
            for (int j = 0; j < reps && i < n; j++, i++) {
                array[i] = num;
            }
        }
        return array;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = randomPartition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int randomPartition(int[] array, int low, int high) {
        Random random = new Random();
        int pivotIndex = low + random.nextInt(high - low + 1); // random pivot
        swap(array, pivotIndex, high); // move pivot to the end
        return partition(array, low, high);
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    
}

