package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    public boolean haveNiceHashCodeSpread(Set<ComplexOomage> oomages) {
        /* TODO: Write a utility function that ensures that the oomages have
         * hashCodes that would distribute them fairly evenly across
         * buckets To do this, mod each's hashCode by M = 10,
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int M = 10; // number of buckets
        int N = oomages.size(); // number of oomages

        ArrayList<ArrayList<Oomage>> buckets = new ArrayList<>(M);
        for (int i = 0; i < M; i++) {
            buckets.add(new ArrayList<Oomage>());
        }

        for (Oomage oom : oomages) {
            int bucketIndex = (oom.hashCode() & 0x7FFFFFFF) % M;
            ArrayList<Oomage> bucket = buckets.get(bucketIndex);
            bucket.add(oom);
            buckets.set(bucketIndex, bucket);
        }

        for (ArrayList bucket : buckets) {
            if (bucket.size() < N / 50 || bucket.size() > N / 2.5) {
                return false;
            }
        }

        return true;
    }


    @Test
    public void testRandomItemsHashCodeSpread() {
        HashSet<ComplexOomage> oomages = new HashSet<ComplexOomage>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(haveNiceHashCodeSpread(oomages));
    }

    @Test
    public void testWithDeadlyParams() {
        /* TODO: Create a Set that shows the flaw in the hashCode function.
         */
        HashSet<ComplexOomage> oomages = new HashSet<ComplexOomage>();
        for (int i = 1; i < 50; i++) {
            ArrayList<Integer> params = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                params.add(1);
            }
            ComplexOomage oom = new ComplexOomage(params);
            oomages.add(oom);
        }
        assertTrue(haveNiceHashCodeSpread(oomages));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
