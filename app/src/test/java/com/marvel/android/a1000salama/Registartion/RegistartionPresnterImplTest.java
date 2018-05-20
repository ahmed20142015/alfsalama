package com.marvel.android.a1000salama.Registartion;

import com.marvel.android.a1000salama.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ahmedpc on 20/5/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistartionPresnterImplTest {
    RegistartionPresnterImpl registartionPresnter;

    @Mock
    RegistartionView view;

    @Mock
    RegistartionPresnter presenter;

    @Before
    public void setUp() throws Exception {
        registartionPresnter = new RegistartionPresnterImpl();
    }

    @Test
    public void showErrorWhenAnInputEqualNull() throws Exception {
        when(view.getFirstName()).thenReturn("");
        when(view.getSecondName()).thenReturn("");
        when(view.getSurName()).thenReturn("");
        when(view.getMobileNumber()).thenReturn("");
        when(view.getNationalID()).thenReturn("");
        when(view.getEmail()).thenReturn("");
        when(view.getPass()).thenReturn("");
        when(view.getConfirmPass()).thenReturn("");
        when(view.getDateOFBirth()).thenReturn("");
        when(view.getGender()).thenReturn("");
        registartionPresnter.onRegisterClicked();
        verify(view).showErrorInputs();
    }
}