package com.marvel.android.a1000salama.Login;

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
 * Created by ahmedpc on 19/5/2018.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginPresneterImplTest {
    @Test
    public void onLoginClicked() throws Exception {
    }
    @Mock
    LoginView view;
    @Mock
    LoginPresenter presenter;
    LoginPresneterImpl loginPresneter;
    @Before
    public void setUp() throws Exception {
        loginPresneter = new LoginPresneterImpl();
    }

    @Test
    public void showErrorMessageWhenUsernameIsEmpty() throws Exception {
        when(view.edEmail()).thenReturn("");
        loginPresneter.onLoginClicked();
        verify(view).showUserNameError(R.string.user_name_error);
    }

    @Test
    public void showErrorMessageWhenPasswordIsEmpty() throws Exception {
        when(view.edPassword()).thenReturn("");
        loginPresneter.onLoginClicked();
        verify(view).showPasswordError(R.string.password_error);
    }


    @Test
    public void shouldStartHomeActivityWhenUsernameAndPasswordAreCorrect() throws Exception {
       when(presenter.RequestLogin(view.edEmail(),view.edPassword())).thenReturn(75);
        loginPresneter.onLoginClicked();
        verify(view).NavigateToHome(75);
    }

    @Test
    public void shouldShowLoginErrorWhenUsernameAndPasswordAreInvalid() throws Exception {
        when(presenter.RequestLogin(view.edEmail(),view.edPassword())).thenReturn(-23);
        loginPresneter.onLoginClicked();
        verify(view).showalert("الإيميل أو رقم الموبايل غير مسجل");
    }
}