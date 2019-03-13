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

/**
 * Class to Test the functionality of methods for class {@link FavouriteDao}
 */
public class FavouriteDaoTest {

    private BigCommerceDatabase bigCommerceDatabase;

    /**
     * Create a InMemory Room Datbase.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        //Init database
        bigCommerceDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                BigCommerceDatabase.class).build();
    }

    /**
     * Close the database.
     */
    @After
    public void tearDown() {
        bigCommerceDatabase.close();
    }

    /**
     * Test to verify that database is updated when favorite button is clicked
     *
     * 1. Create an event
     * 2. Call insertFavoriteEvent method on {@link FavouriteDao} instance.
     * 3. Retrieve list of events from the database
     * 4. Verify retrieved list has a size of 1
     * @throws InterruptedException
     */
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

    /**
     * Test to verify getFavoriteEvents method functionality in {@link FavouriteDao} instance.
     * call the getFavoriteEvents method and verify the size of list
     * List size should be 0 because we create database before starting of every Event.
     * @throws InterruptedException
     */
    @Test
    public void getFavoriteEvents() throws InterruptedException{
        //Get the list of events from database
        List<events> eventsList = LiveDataTestUtil.
                getValue(bigCommerceDatabase.favouriteDao().getFavoriteEvents());

        // Check if size of retrieved list is 0.
        assertThat(eventsList.size(), Is.is(0));
    }

    /**
     * Test to verify functionality of removeEventFromFavorite method of {@link FavouriteDao}
     *
     * 1. create a new event
     * 2. Insert that event using insertFavoriteEvent method.
     * 3. Call removeEventFromFavorite method.
     * 4. Get the list of FavoriteEvents from database.
     * 5. Verify the size of the list.
     *
     * List should be zero since we have added and removed the event from that database table.
     * @throws InterruptedException
     */
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