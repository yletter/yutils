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
     * Complete the 'gridlandMetro' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. INTEGER k
     *  4. 2D_INTEGER_ARRAY track
     */
     
     static class Pair {
         Integer min;
         Integer max;
         Pair(Integer min, Integer max) {
             this.min = min;
             this.max = max;
         }
         int track() {
             return max - min +1;
         }
     }

public static long gridlandMetro(int n, int m, int k, List<List<Integer>> track) {
        HashMap<Long, List<Tracks>> Metro  = new HashMap<>();
        for(List<Integer> t : track){
            Long row = (long)t.get(0);
            Tracks tk = new Tracks(t.get(1), t.get(2));
            if(!Metro.containsKey(row)){
                Metro.put(row, new ArrayList<Tracks>());
            }
            Metro.get(row).add(tk);
        }
        long area = (long) n * (long) m;
        for(List<Tracks> tkList : Metro.values()){
            TreeMap<Long,Long> SortedMetro = new TreeMap<>();
            for(Tracks tk : tkList){
                if(SortedMetro.containsKey(tk.left))
                {
                    if(SortedMetro.get(tk.left) < tk.right){
                        SortedMetro.put(tk.left, tk.right);
                    }
                } else {
                    SortedMetro.put(tk.left, tk.right);
                }
            }
            List<Map.Entry<Long,Long>> MetroGrid = new ArrayList<>(SortedMetro.entrySet());
            long LastPoint = MetroGrid.get(0).getValue();
            long StartingPoint = SortedMetro.firstEntry().getKey();
            long overlay = 0;
            
            for(int i = 1; i < MetroGrid.size(); i++){
                if(MetroGrid.get(i).getKey()-1 > LastPoint ){
                    overlay += MetroGrid.get(i).getKey() - LastPoint - 1;
                }
                if(LastPoint < MetroGrid.get(i).getValue()){
                    LastPoint = MetroGrid.get(i).getValue();
                }
            }
            long OcupiedTracks = (LastPoint - StartingPoint + 1) - overlay;
            area -= OcupiedTracks;
            
        }
        return area;
    }

}

class Tracks{
    long left;
    long right;
    public Tracks(long l, long r){
        this.left = l;
        this.right = r;
    }
}
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> track = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                track.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.gridlandMetro(n, m, k, track);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}






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
     * Complete the 'gridlandMetro' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     *  3. INTEGER k
     *  4. 2D_INTEGER_ARRAY track
     */
     
     static class Pair {
         Integer min;
         Integer max;
         Pair(Integer min, Integer max) {
             this.min = min;
             this.max = max;
         }
         int track() {
             return max - min +1;
         }
     }

    public static long gridlandMetro(int n, int m, int k, List<List<Integer>> track) {
    // Write your code here
    HashMap<Integer,List<Pair>> mr = new HashMap<Integer,List<Pair>>();
    Integer t = 0;
    for (List<Integer> rows : track) {
        if (rows.get(1) > rows.get(2)) {
            int temp = rows.get(1);
            rows.set(1, rows.get(2));
            rows.set(2, temp);
        }
        List<Pair> rexist = mr.get(rows.get(0));
        if (rexist == null) {
            mr.put(rows.get(0), Arrays.asList(new Pair(rows.get(1),rows.get(2))));
        } else {
            boolean set = false;
            for (Pair p:rexist) {
                int min = rows.get(1);
                int max = rows.get(2);
                if (min >= p.min && max <= p.max) {
                    // No action
                    set = true;
                } else if (min <= p.min && max >= p.min && max <= p.max) {
                    p.min = min;
                    set = true;
                } else if (max >= p.max && p.min >= min && p.max <= min) {
                    p.max = max;
                    set = true;
                } else if (max >= p.max && min <= p.min) {
                    p.min = min;
                    p.max = max;
                    set = true;
                }
                if (set) {
                    break;
                }
            }
            if (!set) {
                rexist.add(new Pair(rows.get(1),rows.get(2)));
            } 
        }
    }    
        for (Map.Entry<Integer,List<Pair>> e : mr.entrySet()) {
            for (Pair p: e.getValue()) {
                t += p.track();
            }
        }
        //BigInteger bi3 = BigDecimal.valueOf(num).toBigInteger();
        BigInteger M = BigDecimal.valueOf(m).toBigInteger();
        BigInteger N = BigDecimal.valueOf(n).toBigInteger();
        BigInteger T = BigDecimal.valueOf(t).toBigInteger();
        return M.multiply(N).subtract(T).longValue();
         // 1l;
        //return m*n-t;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int k = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> track = new ArrayList<>();

        IntStream.range(0, k).forEach(i -> {
            try {
                track.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        long result = Result.gridlandMetro(n, m, k, track);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
