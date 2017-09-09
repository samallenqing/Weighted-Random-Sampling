import java.util.Arrays;
import java.util.Random;
import java.util.TreeMap;

public class WeightedRandomSampling {
    public static void main(String[] args) {
        double[] set = {0.2, 0.22, 0.1, 0.28, 0.01, 0.19};
        Arrays.sort(set);
        int sampleTime = 1000;
        getSample(set, sampleTime);
    }

    private static void getSample(double[] set, int sampleTime) {
        TreeMap<Double, Integer> map = new TreeMap<>();
        double[] arr = Arrays.copyOf(set, set.length);
        for (int i = 0; i < set.length - 1; i++) {
            arr[i + 1] += arr[i];
        }
        map.put(arr[0], 0);
        int n = sampleTime;
        for (int i = 0; i < set.length; i++) {
            map.put(arr[i], 0);
        }
        Random r = new Random();
        while (n > 0) {
            double a = r.nextDouble();
            double k = map.ceilingKey(a);
            map.put(k, map.get(k) + 1);
            n--;
        }
        int i = 0;
        for (int val : map.values()) {
            System.out.printf("In %d times samples, the item with the probability %.2g has been picked %d times.\n", sampleTime, set[i++], val);
        }
    }
}
