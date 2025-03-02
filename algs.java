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

    public static void quickSort(int[] array
}

