package com.ndanda.bigcommerce.data;

public class stats {
    private double listing_count;
    private double average_price;
    private double lowest_price_good_deals;
    private double lowest_price;
    private double highest_price;

    public double getListing_count() {
        return listing_count;
    }

    public void setListing_count(double listing_count) {
        this.listing_count = listing_count;
    }

    public double getAverage_price() {
        return average_price;
    }

    public void setAverage_price(double average_price) {
        this.average_price = average_price;
    }

    public double getLowest_price_good_deals() {
        return lowest_price_good_deals;
    }

    public void setLowest_price_good_deals(double lowest_price_good_deals) {
        this.lowest_price_good_deals = lowest_price_good_deals;
    }

    public double getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(double lowest_price) {
        this.lowest_price = lowest_price;
    }

    public double getHighest_price() {
        return highest_price;
    }

    public void setHighest_price(double highest_price) {
        this.highest_price = highest_price;
    }
}
