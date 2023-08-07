package com.example.shoptrack;

import com.example.shoptrack.Contract;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

import com.example.shoptrack.data.Auth;
import com.example.shoptrack.data.Store;
import com.example.shoptrack.managers.LoginPresenter;
import com.example.shoptrack.ui.Login;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Mock
    Login mockView;

    @Mock
    Auth mockModel;

    @Mock
    Contract.View view;

    LoginPresenter presenter;

    @Before
    public void setUp(){
        presenter = new LoginPresenter(mockModel, mockView);
    }

    // Test interaction b/w the presenter and the model //

    @Test
    public void testPresenter() throws Exception {
        String email = "";
        String password = "pwd";

        Mockito.doNothing().when(mockModel).login(email, password, mockView, presenter);

        presenter.recieveData(mockView, email, password);

        Mockito.verify(mockModel, Mockito.times(1)).login(email, password, mockView, presenter);
    }

    @Test
    public void presenterSuccess() throws Exception {

        String email = "mystoreowner@gmail.com"; // Store
        String password = "storeownerpwd";
        String id = "1";
        Store store = new Store("StoreOwner", email, id);

        Mockito.doNothing().when(mockModel).login(email, password, mockView, presenter);
        presenter.recieveData(mockView, email, password);
        presenter.successDecider(true, mockView, store);
        Mockito.verify(mockView, Mockito.times(1)).handleGetUser(store);
    }

    @Test
    public void presenterFail() throws Exception {
        String email = "mystoreowner@gmail.com"; // Store
        String password = "storeownerpwd";
        String id = "1";
        Store store = new Store("StoreOwner", email, id);

        Mockito.doNothing().when(mockModel).login(email, password, mockView, presenter);
        presenter.recieveData(mockView, email, password);
        presenter.successDecider(false, mockView, store);
        Mockito.verify(mockView, Mockito.times(1)).handleFailure();
    }

    @Test
    public void testHandleFinishLoginSuccess() {
        String email = "mystoreowner@gmail.com"; // Store
        String password = "storeownerpwd";
        String id = "1";
        Store store = new Store("StoreOwner", email, id);

        Mockito.doNothing().when(mockModel).login(email, password, mockView, presenter);
        presenter.recieveData(mockView, email, password);
        presenter.successDecider(true, mockView, store);
        presenter.handleFinishLogin(true, "");
        Mockito.verify(mockView, Mockito.times(1)).handleGetUser(store);
    }

    @Test
    public void testHandleFinishLoginFail() {
        String toastMsg = "User does not exist";
        presenter.handleFinishLogin(false, toastMsg);
        Mockito.verify(mockView, Mockito.times(1)).handleFinishLoading(toastMsg);
    }

    @Test
    public void testHandleLogin() {
        presenter.handleLogin();
    }
}