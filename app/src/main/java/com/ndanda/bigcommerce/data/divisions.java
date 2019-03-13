package com.ndanda.bigcommerce.data;

public class divisions {
    private String display_name;
    private String short_name;
    private int division_level;
    private String display_type;
    private int taxonomy_id;
    private String slug;

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String input) {
        this.display_name = input;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String input) {
        this.short_name = input;
    }

    public int getDivision_level() {
        return division_level;
    }

    public void setDivision_level(int input) {
        this.division_level = input;
    }

    public String getDisplay_type() {
        return display_type;
    }

    public void setDisplay_type(String input) {
        this.display_type = input;
    }

    public int getTaxonomy_id() {
        return taxonomy_id;
    }

    public void setTaxonomy_id(int input) {
        this.taxonomy_id = input;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String input) {
        this.slug = input;
    }
}
