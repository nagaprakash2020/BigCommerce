package com.ndanda.bigcommerce.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

@Entity
public class events implements Parcelable {

    @Ignore
    private links[] links;
    @NonNull
    @PrimaryKey
    private int id;
    @Ignore
    private stats stats;
    private String title;
    private String announceDate;
    private double score;
    private boolean dateTbd;
    private String type;
    private String datetime_local;
    private String visibleUntilUtc;
    private boolean timeTbd;
    @Ignore
    private List<taxonomies> taxonomies;
    @Ignore
    private List<performers> performers;
    private String url;
    private String createdAt;
    @Ignore
    private venue venue;
    private String shortTitle;
    private String datetimeUtc;
    private boolean datetimeTbd;
    @Ignore
    private boolean favorite;

    public events() {
    }

    @Ignore
    protected events(Parcel in) {
        id = in.readInt();
        title = in.readString();
        announceDate = in.readString();
        score = in.readDouble();
        dateTbd = in.readByte() != 0;
        type = in.readString();
        datetime_local = in.readString();
        visibleUntilUtc = in.readString();
        timeTbd = in.readByte() != 0;
        url = in.readString();
        createdAt = in.readString();
        shortTitle = in.readString();
        datetimeUtc = in.readString();
        datetimeTbd = in.readByte() != 0;
    }

    public static final Creator<events> CREATOR = new Creator<events>() {
        @Override
        public events createFromParcel(Parcel in) {
            return new events(in);
        }

        @Override
        public events[] newArray(int size) {
            return new events[size];
        }
    };

    public links[] getLinks() {
        return links;
    }

    public void setLinks(links[] links) {
        this.links = links;
    }

    public int getId() {
        return id;
    }

    public void setId(int input) {
        this.id = input;
    }

    public stats getStats() {
        return stats;
    }

    public void setStats(stats input) {
        this.stats = input;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String input) {
        this.title = input;
    }

    public String getAnnounceDate() {
        return announceDate;
    }

    public void setAnnounceDate(String input) {
        this.announceDate = input;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double input) {
        this.score = input;
    }

    public boolean getDateTbd() {
        return dateTbd;
    }

    public void setDateTbd(boolean input) {
        this.dateTbd = input;
    }

    public String getType() {
        return type;
    }

    public void setType(String input) {
        this.type = input;
    }

    public String getDatetime_local() {
        return datetime_local;
    }

    public void setDatetime_local(String datetime_local) {
        this.datetime_local = datetime_local;
    }

    public String getVisibleUntilUtc() {
        return visibleUntilUtc;
    }

    public void setVisibleUntilUtc(String input) {
        this.visibleUntilUtc = input;
    }

    public boolean getTimeTbd() {
        return timeTbd;
    }

    public void setTimeTbd(boolean input) {
        this.timeTbd = input;
    }

    public List<taxonomies> getTaxonomies() {
        return taxonomies;
    }

    public void setTaxonomies(List<taxonomies> taxonomies) {
        this.taxonomies = taxonomies;
    }

    public List<com.ndanda.bigcommerce.data.performers> getPerformers() {
        return performers;
    }

    public void setPerformers(List<com.ndanda.bigcommerce.data.performers> performers) {
        this.performers = performers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String input) {
        this.url = input;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String input) {
        this.createdAt = input;
    }

    public venue getVenue() {
        return venue;
    }

    public void setVenue(venue input) {
        this.venue = input;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String input) {
        this.shortTitle = input;
    }

    public String getDatetimeUtc() {
        return datetimeUtc;
    }

    public void setDatetimeUtc(String input) {
        this.datetimeUtc = input;
    }

    public boolean getDatetimeTbd() {
        return datetimeTbd;
    }

    public void setDatetimeTbd(boolean input) {
        this.datetimeTbd = input;
    }

    public boolean getFavorite() {
        return this.favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(announceDate);
        dest.writeDouble(score);
        dest.writeByte((byte) (dateTbd ? 1 : 0));
        dest.writeString(type);
        dest.writeString(datetime_local);
        dest.writeString(visibleUntilUtc);
        dest.writeByte((byte) (timeTbd ? 1 : 0));
        dest.writeString(url);
        dest.writeString(createdAt);
        dest.writeString(shortTitle);
        dest.writeString(datetimeUtc);
        dest.writeByte((byte) (datetimeTbd ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        events events = (events) o;

        return id == events.id;
    }

    @Override
    public int hashCode() {

        int result = 17;
        result = 31 * result + this.id;

        return result;
    }
}
