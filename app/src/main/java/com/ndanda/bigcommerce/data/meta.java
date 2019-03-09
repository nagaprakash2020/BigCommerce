package com.ndanda.bigcommerce.data;

public class meta {
    private int perPage;
    private int total;
    private int page;
    private geolocation geolocation;

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public com.ndanda.bigcommerce.data.geolocation getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(com.ndanda.bigcommerce.data.geolocation geolocation) {
        this.geolocation = geolocation;
    }
}
