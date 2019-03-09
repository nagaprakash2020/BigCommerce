package com.ndanda.bigcommerce.repository;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;

import com.ndanda.bigcommerce.data.events;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import com.ndanda.bigcommerce.testUtils.LiveDataTestUtil;

import static org.junit.Assert.*;

public class FavouriteDaoTest {

    private BigCommerceDatabase bigCommerceDatabase;

    @Before
    public void setUp() throws Exception {

        //Init database
        bigCommerceDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                BigCommerceDatabase.class).build();
    }

    @After
    public void tearDown() {
        bigCommerceDatabase.close();
    }

    @Test
    public void insertFavoriteEventTest() throws InterruptedException{

        events event = new events();

        // Insert a new event in database
        bigCommerceDatabase.favouriteDao().insertFavoriteEvent(event);

        // Retrieve events from database
        List<events> eventsList = LiveDataTestUtil.
                getValue(bigCommerceDatabase.favouriteDao().getFavoriteEvents());

        // Check if size of retrieved list is 1.
        assertThat(eventsList.size(), Is.is(1));

    }

    @Test
    public void getFavoriteEvents() throws InterruptedException{
        //Get the list of events from database
        List<events> eventsList = LiveDataTestUtil.
                getValue(bigCommerceDatabase.favouriteDao().getFavoriteEvents());

        // Check if size of retrieved list is 0.
        assertThat(eventsList.size(), Is.is(0));
    }

    @Test
    public void removeEventFromFavorite() throws InterruptedException{

        events event = new events();

        // Insert a new event in database
        bigCommerceDatabase.favouriteDao().insertFavoriteEvent(event);

        // Now remove the event
        bigCommerceDatabase.favouriteDao().removeEventFromFavorite(event);

        //Get the list of events from database
        List<events> eventsList = LiveDataTestUtil.
                getValue(bigCommerceDatabase.favouriteDao().getFavoriteEvents());

        // Check if size of retrieved list is 0.
        assertThat(eventsList.size(), Is.is(0));

    }
}