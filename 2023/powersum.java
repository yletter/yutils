import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'powerSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER X
     *  2. INTEGER N
     */

    public static int powerSum(int X, int N) {
    // Write your code here

        double a = (double)X;
        double b = (double)1/N;
        int k = (int)(Math.pow(a, b) + 1);
        System.out.println(k);
        double[] arr = new double[k];
        for (int i =0; i<k; i++) {
            arr[i] = Math.pow(i, N);
            //System.out.println(arr[i]);
        }
        return pof(X, N, 0, k);
    }
	
    static int pof(int x, int n, int i, int k) {
        System.out.println("Call: i=" + i + ", x=" + x + ", n=" + n + ", k=" + k);
        x = x - (int) Math.pow(i, n);
        if (x == 0) return 1;
        if (x > 0) {
            int sum = 0;
            for (int j = i + 1; j <= k; j++) {
                sum += pof(x, n, j, k);
                System.out.println("j=" + j + ", x=" + x + ", n=" + n + ", k=" + k);
            }
            System.out.println("Sum:" + sum);
            return sum;
        } else return 0;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int X = Integer.parseInt(bufferedReader.readLine().trim());

        int N = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.powerSum(X, N);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
