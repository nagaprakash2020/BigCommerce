package com.ndanda.bigcommerce.data;

import java.util.List;

public class SeatGeekEvent {
    private meta meta;
    private List<events> events;

    public com.ndanda.bigcommerce.data.meta getMeta() {
        return meta;
    }

    public void setMeta(com.ndanda.bigcommerce.data.meta meta) {
        this.meta = meta;
    }

    public List<com.ndanda.bigcommerce.data.events> getEvents() {
        return events;
    }

    public void setEvents(List<com.ndanda.bigcommerce.data.events> events) {
        this.events = events;
    }
}
