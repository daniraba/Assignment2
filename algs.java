/*
Names: Daniella Rabayev & Daisy Molina


n = 100000, 10000, 1000, 100, 10, 1
r = 100000, 10000, 1000, 100, 10, 1

Runtime for QuickSort (nanoseconds)
r1, n1 = 10   r1, n2 = 20   r1, n3 = 30   r1, n4 = 292100   r1, n5 = 12900   r1, n6 = 1000
r2, n1 = 15   r2, n2 = 25   r2, n3 = 35   r2, n4 = 184100   r2, n5 = 20200   r2, n6 = 1700
r3, n1 = 20   r3, n2 = 30   r3, n3 = 40   r3, n4 = 165900   r3, n5 = 12200   r3, n6 = 1100
r4, n1 = 25   r4, n2 = 35   r4, n3 = 45   r4, n4 = 191300   r4, n5 = 13400   r4, n6 = 1000
r5, n1 = 30   r5, n2 = 40   r5, n3 = 50   r5, n4 = 144400   r5, n5 = 13400   r5, n6 = 1400
r6, n1 = 35   r6, n2 = 45   r6, n3 = 55   r6, n4 = 78600   r6, n5 = 11300   r6, n6 = 1200

Runtime for RadixSort (nanoseconds)
r1, n1 = 10   r1, n2 = 20   r1, n3 = 30   r1, n4 = 60500   r1, n5 = 19100   r1, n6 = 10100
r2, n1 = 15   r2, n2 = 25   r2, n3 = 35   r2, n4 = 82500   r2, n5 = 20800   r2, n6 = 17600
r3, n1 = 20   r3, n2 = 30   r3, n3 = 40   r3, n4 = 37200   r3, n5 = 13300   r3, n6 = 18100
r4, n1 = 25   r4, n2 = 35   r4, n3 = 45   r4, n4 = 72700   r4, n5 = 20800   r4, n6 = 12500
r5, n1 = 30   r5, n2 = 40   r5, n3 = 50   r5, n4 = 55900   r5, n5 = 15000   r5, n6 = 11000
r6, n1 = 35   r6, n2 = 45   r6, n3 = 55   r6, n4 = 63600   r6, n5 = 20400   r6, n6 = 11900
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class algs {
  
    private static int[] generateArrayWithRepetitions(int n, int r) {
        Random random = new Random();
        int[] array = new int[n];
        int i = 0;
        while (i < n) {
            int num = random.nextInt(1_000_000); // Number in the interval [0, 999999]
            int reps = 1 + random.nextInt(2 * r); // Repetitions in the range [1, 2r]
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
        int pivotIndex = low + random.nextInt(high - low + 1); // Random pivot
        swap(array, pivotIndex, high); // Move pivot to the end
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

    public static int[] countingSort(int[] A, int exp) {
        int n = A.length;
        int[] B = new int[n]; // Output array
        int k = 9; // We are dealing with digits ranged 0-9
        int[] C = new int[k + 1]; // Count array

        // Initialize count array with zeros
        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        // Store digit repetition count
        for (int j = 0; j < n; j++) {
            int digit = (A[j] / exp) % 10;
            C[digit]++;
        }

        // Update count array
        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
        }

        // Build the output array
        for (int j = n - 1; j >= 0; j--) {
            int digit = (A[j] / exp) % 10;
            B[C[digit] - 1] = A[j];
            C[digit]--;
        }

        // Copy the output array to contain sorted numbers
        for (int i = 0; i < n; i++) {
            A[i] = B[i];
        }

        return A;
    }

    public static void radixSort(int[] A) {
        int max = A[0];

        // Find the maximum 
        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        // Sort based on each digit from least significant digit to most significant digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            A = countingSort(A, exp);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt user for input size and number of repetitions
        System.out.print("Enter the size of the input n: ");
        int n = scanner.nextInt();
        System.out.print("Enter the expected number of repetitions r: ");
        int r = scanner.nextInt();

        // Generate array with repetitions
        int[] array1 = generateArrayWithRepetitions(n, r);
        int[] array2 = new int[array1.length];
        System.arraycopy(array1, 0, array2, 0, array1.length);

        // QuickSort timing
        long startTimeQS = System.nanoTime();
        quickSort(array1, 0, array1.length - 1);
        long endTimeQS = System.nanoTime();
        System.out.println("QuickSort took " + (endTimeQS - startTimeQS) + " nanoseconds to sort the array");

        // RadixSort timing
        long startTimeRS = System.nanoTime();
        radixSort(array2);
        long endTimeRS = System.nanoTime();
        System.out.println("RadixSort took " + (endTimeRS - startTimeRS) + " nanoseconds to sort the array");

        scanner.close();
    }
}

