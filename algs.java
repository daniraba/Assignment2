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

