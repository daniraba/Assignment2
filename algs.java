import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class algs {

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
        Random random = new Random();

        // Prompt user for input size and number of repetitions
        System.out.print("Enter the size of the input n: ");
        int n = scanner.nextInt();
        System.out.print("Enter the expected number of repetitions r: ");
        int r = scanner.nextInt();

        // Store the result
        List<Integer> numbers = new ArrayList<>();

        // Generate random numbers and their repetitions
        int remaining = n;
        while (remaining > 0) {
            int number = random.nextInt(1000000); // Random number in the interval [0, 999999]
            int repetitions = random.nextInt(2 * r) + 1; // Random repetitions in the range [1, 2r]

            // Total sum of repetitions = n
            if (repetitions > remaining) {
                repetitions = remaining;
            }

            for (int i = 0; i < repetitions; i++) {
                numbers.add(number);
            }

            remaining -= repetitions;
        }

        // Convert the list to an array
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = numbers.get(i);
        }

        // Perform radix sort
        radixSort(A);

        // Print sorted array
        System.out.println("Sorted array:");
        for (int num : A) {
            System.out.print(num + " ");
        }
    }
}