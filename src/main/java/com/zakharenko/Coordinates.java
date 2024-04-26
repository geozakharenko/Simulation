package com.zakharenko;

public class Coordinates {
    private final Integer y;
    private final Integer x;

    public Coordinates(Integer y, Integer x) {
        this.x = x;
        this.y = y;
    }

    public Integer getCordX() {
        return x;
    }

    public Integer getCordY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (!y.equals(that.y)) return false;
        return x.equals(that.x);
    }

    @Override
    public int hashCode() {
        int result = y.hashCode();
        result = 31 * result + x.hashCode();
        return result;
    }
}
