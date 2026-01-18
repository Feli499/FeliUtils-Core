package de.feli490.utils.core.common.tuple;

public class Trio <A, B, C> extends Pair<A, B> {

    private final C third;

    public Trio(A first, B second, C third) {
        super(first, second);
        this.third = third;
    }

    public C getThird() {
        return third;
    }
}
