package com.ndanda.bigcommerce.data;

import java.util.List;

public class performers {
    private String image;
    private boolean primary;
    private images images;
    private boolean hasUpcomingEvents;
    private int id;
    private stats stats;
    private double score;
    private List<taxonomies> taxonomies;
    private String type;
    private String shortName;
    private int homeVenueId;
    private String slug;
    private List<divisions> divisions;
    private boolean homeTeam;
    private String name;
    private String url;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public com.ndanda.bigcommerce.data.images getImages() {
        return images;
    }

    public void setImages(com.ndanda.bigcommerce.data.images images) {
        this.images = images;
    }

    public boolean isHasUpcomingEvents() {
        return hasUpcomingEvents;
    }

    public void setHasUpcomingEvents(boolean hasUpcomingEvents) {
        this.hasUpcomingEvents = hasUpcomingEvents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public com.ndanda.bigcommerce.data.stats getStats() {
        return stats;
    }

    public void setStats(com.ndanda.bigcommerce.data.stats stats) {
        this.stats = stats;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<com.ndanda.bigcommerce.data.taxonomies> getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(List<com.ndanda.bigcommerce.data.taxonomies> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getHomeVenueId() {
        return homeVenueId;
    }

    public void setHomeVenueId(int homeVenueId) {
        this.homeVenueId = homeVenueId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<com.ndanda.bigcommerce.data.divisions> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<com.ndanda.bigcommerce.data.divisions> divisions) {
        this.divisions = divisions;
    }

    public boolean isHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(boolean homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
