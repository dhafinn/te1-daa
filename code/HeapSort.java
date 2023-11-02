import java.io.*;
import java.util.*;

public class HeapSort {
    public void sort(int arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from the heap
        for (int i = n - 1; i >= 0; i--) {
            // Move the current root to the end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static void main(String[] args) {
        // Read the dataset from dataset1.txt
        int[] dataset = readDatasetFromFile("dataset3.txt");

        // Measure the starting time
        long startTime = System.currentTimeMillis();

        // Sort the dataset using Heap Sort
        HeapSort heapSort = new HeapSort();
        heapSort.sort(dataset);

        // Measure the ending time
        long endTime = System.currentTimeMillis();

        // Calculate the running time
        long runningTime = endTime - startTime;

        // Write sorted dataset to a file
        writeDatasetToFile("sorted_dataset_heap3.txt", dataset);

        // Print running time
        System.out.println("Running Time: " + runningTime + " milliseconds");
        
        // Calculate memory space
        Runtime runtime = Runtime.getRuntime();
        long memory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory Space: " + memory + " bytes");
    }

    // Method to read dataset from a file and return it as an array of integers
    private static int[] readDatasetFromFile(String fileName) {
        ArrayList<Integer> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Parse the integers from each line and add to the list
                int number = Integer.parseInt(line.trim());
                dataList.add(number);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the ArrayList to an array of integers
        int[] dataset = new int[dataList.size()];
        for (int i = 0; i < dataList.size(); i++) {
            dataset[i] = dataList.get(i);
        }

        return dataset;
    }

    // Method to write a dataset to a file
    private static void writeDatasetToFile(String fileName, int[] dataset) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int number : dataset) {
                bw.write(Integer.toString(number));
                bw.newLine(); // Write a newline character after each number
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
