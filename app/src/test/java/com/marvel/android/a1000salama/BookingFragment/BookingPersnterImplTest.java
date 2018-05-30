package com.marvel.android.a1000salama.BookingFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ahmedpc on 23/5/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookingPersnterImplTest {
    BookingPersnterImpl bookingPersnter;
    @Mock
    BookingViwe view;



    @Before
    public void setUp() throws Exception {
        bookingPersnter = new BookingPersnterImpl();
    }

    @Test
    public void showErrorInvalidInputs() throws Exception {
        when(view.getServiceItems()).thenReturn(0);
        when(view.getBranchId()).thenReturn(null);
        when(view.getServiceComment()).thenReturn("");
        bookingPersnter.onBookClick();
        verify(view).showErrorInputs();


    }
}