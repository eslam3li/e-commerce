package com.jets.ecommerce.dal.dao;


public class Page {

    private final int start;
    private int currentStart;
    private final int pageSize;

    public Page(int start, int pageSize) {
        this.start = start;
        this.currentStart = start;
        this.pageSize = pageSize;
    }

    public int getStart() {
        return start;
    }

    public int getCurrentStart() {
        return currentStart;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void nextPage() {
        currentStart += pageSize;
    }

    public void nextPage(int pageNumber) {
        currentStart += pageNumber * pageSize;
    }

    public void previousPage() {
        currentStart -= pageSize;
    }

    public void previousPage(int pageNumber) {
        currentStart -= pageNumber * pageSize;
    }

    public void goToPage(int pageNumber) {
        currentStart = start + pageNumber * pageSize;
    }

}
