package io.github.ooknight.universe.core.domain;

import io.ebean.PagedList;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public final class Pagination<E> implements Serializable {

    private List<E> data;
    private int total;

    public static <E> Pagination<E> of(List<E> dataList, int totalCount) {
        Pagination<E> pagination = new Pagination<>();
        pagination.setData(dataList);
        pagination.setTotal(totalCount);
        return pagination;
    }

    public static <E> Pagination<E> of(PagedList<E> pagedList) {
        Pagination<E> pagination = new Pagination<>();
        pagination.setData(pagedList.getList());
        pagination.setTotal(pagedList.getTotalCount());
        return pagination;
    }

    public static <E> Pagination<E> empty() {
        Pagination<E> pagination = new Pagination<>();
        pagination.setData(Collections.emptyList());
        pagination.setTotal(0);
        return pagination;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
