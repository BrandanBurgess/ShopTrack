package com.example.shoptrack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.shoptrack.ui.Login;
import com.example.shoptrack.ui.LoginModel;
import com.example.shoptrack.ui.LoginPresenter;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 *
 * Uses mockito to test the functionality of the app
 * Will only test the login functionality
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
	@Mock
	Login view;

	@Mock
	LoginModel model;

	// test if the login works fine with the correct credentials
	@Test
	public void testLoginSuccess() throws Exception {
		LoginPresenter presenter = new LoginPresenter(view, model);
		presenter.login("admin", "password"); // change the credentials to test
		verify(view, times(1)).notify("Login Successful");
	}

	// test if the login fails with the wrong credentials
	@Test
	public void testLoginFail() throws Exception {
		LoginPresenter presenter = new LoginPresenter(view, model);
		presenter.login("admin", "password"); // change the credentials to test
		verify(view, times(1)).notify("Authentication failed.");
	}

	// test if the login fails with the invalid input
	@Test
	public void testLoginEmptyPassword() throws Exception {
		LoginPresenter presenter = new LoginPresenter(view, model);
		presenter.login("admin", ""); // password is empty
		verify(view, times(1)).notify("Enter password");
	}

	// test if the login fails with the invalid input
	@Test
	public void testLoginEmptyEmail() throws Exception {
		LoginPresenter presenter = new LoginPresenter(view, model);
		presenter.login("", "password"); // email is empty
		verify(view, times(1)).notify("Enter email");
	}

	// test if the login fails with the invalid input
	@Test
	public void testLoginNoInput() throws Exception {
		LoginPresenter presenter = new LoginPresenter(view, model);
		presenter.login("", ""); // both fields empty
		verify(view, times(1)).notify("Enter email");
	}
}
