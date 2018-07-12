package sample.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;

import java.util.*;

public class BloomFilterTest {
    @Test
    public void test() {
        int insertions = 1000000;
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions, 0.001);
        Set<String> sets = new HashSet<>(insertions);
        List<String> list = new ArrayList<>(insertions);
        for (int i = 0; i < insertions; i++) {
            String uuid = UUID.randomUUID().toString();
            bloomFilter.put(uuid);
            sets.add(uuid);
            list.add(uuid);
        }
        int wrong = 0;
        int right = 0;
        for (int i = 0; i < insertions; i++) {
            String test = i % 100 == 0 ? list.get(i / 100) : UUID.randomUUID().toString();
            if (!bloomFilter.mightContain(test))continue;
            if (sets.contains(test)) {
                right++;
            } else {
                wrong++;
            }
        }
        System.out.printf("right:%d，wrong:%d",right,wrong);
    }
}
