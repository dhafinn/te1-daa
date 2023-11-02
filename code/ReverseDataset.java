import java.io.*;
import java.util.*;

public class ReverseDataset {
    public static void main(String[] args) {
        // Read the dataset from dataset1.txt
        int[] dataset = readDatasetFromFile("sorted_dataset3.txt");

        // Reverse the dataset
        reverseArray(dataset);

        // Write reversed dataset to a file
        writeDatasetToFile("reversed_dataset3.txt", dataset);
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

    // Method to reverse an array of integers in place
    private static void reverseArray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            // Swap the elements at the start and end positions
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
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
