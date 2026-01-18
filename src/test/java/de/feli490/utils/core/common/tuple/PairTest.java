package de.feli490.utils.core.common.tuple;

import org.junit.Assert;
import org.junit.Test;

public class PairTest {

    @Test
    public void testPair() {

        String first = "Hello";
        Long second = 13123L;

        Pair<String, Long> pair = new Pair<>(first, second);

        Assert.assertEquals(first, pair.getFirst());
        Assert.assertEquals(second, pair.getSecond());
    }
}
