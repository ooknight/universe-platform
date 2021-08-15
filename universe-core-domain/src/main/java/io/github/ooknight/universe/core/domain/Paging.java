package io.github.ooknight.universe.core.domain;

import java.io.Serializable;

public final class Paging implements Serializable {

    private int number;
    private int size;
    private String[] sort;

    public Paging() {}

    public Paging(int number, int size, String... sort) {
        this.number = number;
        this.size = size;
        this.sort = sort;
    }

    public static Paging standard() {
        return new Paging(1, 20);
    }

    public int number() {
        return number;
    }

    public int first() {
        return ((number - 1) * size);
    }

    public int size() {
        return size;
    }

    public String[] sort() {
        return sort;
    }

    /*
    @JsonIgnore
    public int getOffset() {
        return (number - 1) * size;
    }
    */

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getSort() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
    }
}
