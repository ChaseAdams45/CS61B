package hw3.hash;

import java.util.*;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        double scale = 0.5;
        int N = 50;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        Set<Oomage> oomies = new HashSet<Oomage>();
        for (int i = 0; i < N; i += 1) {
            oomies.add(ComplexOomage.randomComplexOomage());
        }

        visualize(oomies, M, scale);
    }

    public static void visualize(Set<Oomage> set, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);

        /* TODO: Create a visualization of the given hash table. Use
           du.xCoord and du.yCoord to figure out where to draw
           Oomages.
         */

        /* When done with visualizer, be sure to try 
           scale = 0.5, N = 2000, M = 100. */
        ArrayList<ArrayList<Oomage>> buckets = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Oomage oom : set) {
            int bucketIndex = (oom.hashCode() & 0x7FFFFFFF) % M;
            ArrayList<Oomage> bucket = buckets.get(bucketIndex);
            bucket.add(oom);
            buckets.set(bucketIndex, bucket);
        }

        for (ArrayList<Oomage> bucket : buckets) {
            int y = buckets.indexOf(bucket);
            for (Oomage oom : bucket) {
                int x = bucket.indexOf(oom);
                oom.draw(
                        HashTableDrawingUtility.xCoord(x),
                        HashTableDrawingUtility.yCoord(y, M));
            }
        }
    }
} 
