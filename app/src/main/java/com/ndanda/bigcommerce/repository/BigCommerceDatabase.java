package com.ndanda.bigcommerce.repository;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ndanda.bigcommerce.data.events;

@Database(entities = {events.class},version = 2,exportSchema = false)
public abstract class BigCommerceDatabase extends RoomDatabase{

    public abstract FavouriteDao favouriteDao();
}
