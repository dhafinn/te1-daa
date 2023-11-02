import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class ShellSort {
    public static final int C=4; // number of region compare-exchange repetitions
    public static void exchange(int[ ] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static void compareExchange(int[ ] a, int i, int j) {
        if (((i < j) && (a[i] > a[j])) || ((i > j) && (a[i] < a[j]))) 
            exchange(a, i, j);
    }
    public static void permuteRandom(int a[], Random rand) {
        for (int i=0; i<a.length; i++) // Use the Knuth random perm. algorithm
            exchange(a, i, rand.nextInt(a.length-i)+i);
    }
    
    // compare-exchange two regions of length offset each
    public static void compareRegions(int[] a, int s, int t, int offset, Random rand) {
        int[] mate = new int[offset]; // index offset array
        for (int count=0; count<C; count++) { // do C region compare-exchanges 
            for (int i=0; i<offset; i++) mate[i] = i;
                permuteRandom(mate,rand); // comment this out to get a deterministic Shellsort
            for (int i=0; i<offset; i++)
                compareExchange(a, s+i, t+mate[i]);
        }
    }

    public static void randomizedShellSort(int[] a) {
        int n = a.length; // we assume that n is a power of 2
        Random rand = new Random(); // random number generator (not shown)
        for (int offset = n/2; offset > 0; offset /= 2) { 
            for (int i=0; i < n - offset; i += offset) // compare-exchange up
                compareRegions(a,i,i+offset,offset,rand);
            for (int i=n-offset; i >= offset; i -= offset) // compare-exchange down
                compareRegions(a,i-offset,i,offset,rand);
            for (int i=0; i < n-3*offset; i += offset) // compare 3 hops up
                compareRegions(a,i,i+3*offset,offset,rand);
            for (int i=0; i < n-2*offset; i += offset) // compare 2 hops up
                compareRegions(a,i,i+2*offset,offset,rand);
            for (int i=0; i < n; i += 2*offset) // compare odd-even regions
                compareRegions(a,i,i+offset,offset,rand); 
            for (int i=offset; i < n-offset; i += 2*offset) // compare even-odd regions
                compareRegions(a,i,i+offset,offset,rand);
        }
    }

    public static void main(String[] args) {
        // Read the dataset from dataset1.txt
        int[] dataset = readDatasetFromFile("dataset3.txt");

        // Measure the starting time
        long startTime = System.currentTimeMillis();

        // Sort the dataset using randomizedShellSort
        randomizedShellSort(dataset);

        // Measure the ending time
        long endTime = System.currentTimeMillis();

        // Calculate the running time
        long runningTime = endTime - startTime;

        // Write sorted dataset to a file
        writeDatasetToFile("sorted_dataset3.txt", dataset);

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
