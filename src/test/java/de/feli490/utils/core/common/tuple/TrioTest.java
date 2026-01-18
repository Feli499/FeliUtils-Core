package de.feli490.utils.core.common.tuple;

import org.junit.Assert;
import org.junit.Test;

public class TrioTest {

    @Test
    public void testTrio() {

        String first = "Hello";
        Long second = 13123L;
        Integer third = 9234;

        Trio<String, Long, Integer> trio = new Trio<>(first, second, third);

        Assert.assertEquals(first, trio.getFirst());
        Assert.assertEquals(second, trio.getSecond());
        Assert.assertEquals(third, trio.getThird());
    }
}
