package com.example.shoptrack;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.shoptrack.ui.Login;
import com.example.shoptrack.ui.LoginModel;
import com.example.shoptrack.ui.LoginPresenter;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicBoolean;

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
	public void testLoginSuccess() {
		LoginPresenter presenter = new LoginPresenter(view, model);
		CompletableFuture<Boolean> loginResult = CompletableFuture.completedFuture(true);
		when(model.login("admin@shoptrack.com", "password")).thenReturn(loginResult);
		presenter.login("admin@shoptrack.com", "password"); // change the credentials to test
		verify(view).showProgressBar();
		verify(view).hideProgressBar();
		verify(view).notify("Login Successful");
		verify(view).goHome();
	}

	// test if the login fails with the wrong credentials
	@Test
	public void testLoginFail() {
		LoginPresenter presenter = new LoginPresenter(view, model);
		CompletableFuture<Boolean> loginResult = CompletableFuture.completedFuture(false);
		when(model.login("ntr@ntr.com", "lmaoooo")).thenReturn(loginResult);
		presenter.login("ntr@ntr.com", "lmaoooo"); // change the credentials to test
		verify(view).showProgressBar();
		verify(view).hideProgressBar();
		verify(view, times(1)).notify("Authentication failed.");
	}

	// test if the login fails with the invalid input
	@Test
	public void testLoginEmptyPassword() {
		LoginPresenter presenter = new LoginPresenter(view, model);
		presenter.login("admin", ""); // password is empty
		verify(view, times(1)).notify("Enter password");
	}

	// test if the login fails with the invalid input
	@Test
	public void testLoginEmptyEmail() {
		LoginPresenter presenter = new LoginPresenter(view, model);
		presenter.login("", "password"); // email is empty
		verify(view, times(1)).notify("Enter email");
	}

	// test if the login fails with the invalid input
	@Test
	public void testLoginNoInput() {
		LoginPresenter presenter = new LoginPresenter(view, model);
		presenter.login("", ""); // both fields empty
		verify(view, times(1)).notify("Enter email");
	}
}
