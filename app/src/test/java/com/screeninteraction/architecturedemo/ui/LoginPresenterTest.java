package com.screeninteraction.architecturedemo.ui;

import com.screeninteraction.architecturedemo.io.usecases.LoginInteractor;
import com.screeninteraction.architecturedemo.system.StringProvider;

import junit.framework.TestCase;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class LoginPresenterTest extends TestCase {

    @Mock
    private LoginContract.View view;

    @Mock
    private LoginInteractor interactor;

    @Mock
    private StringProvider stringProvider;

    private LoginContract.Actions presenter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MockitoAnnotations.initMocks(this);
        presenter = new LoginPresenter(view, interactor, stringProvider);
    }

    public void testLoginButtonEnabled() throws Exception {
        // given
        when(view.getUsername()).thenReturn("some_username");
        when(view.getPassword()).thenReturn("some_password");

        // when
        presenter.enableLoginButtonIfPossible();

        // then
        verify(view).setLoginButtonEnabled(true);
    }

    public void testLoginButtonEnabled_RequestsNeededFields() throws Exception {
        // given

        // when
        presenter.enableLoginButtonIfPossible();

        // then
        verify(view).getUsername();
        verify(view).getPassword();
    }

    public void testLoginButtonEnabled_MissingFieldsShouldNotEnable() throws Exception {
        // given
        when(view.getUsername()).thenReturn(null);
        when(view.getPassword()).thenReturn(null);

        // when
        presenter.enableLoginButtonIfPossible();

        // then
        verify(view).setLoginButtonEnabled(false);
    }

    public void testToggleLoading_ShouldShowLoading() throws Exception {
        // given

        // when
        presenter.toggleLoadingState(true);

        // then
        verify(view).setInputEnabled(false);
        verify(view).showLoadingIndicator(true);
        verify(view).setLoginButtonEnabled(false);

        verifyNoMoreInteractions(view);
    }

    public void testToggleLoading_ShouldShowNormal() throws Exception {
        // given

        // when
        presenter.toggleLoadingState(false);

        // then
        verify(view).setInputEnabled(true);
        verify(view).showLoadingIndicator(false);
        verify(view).setLoginButtonEnabled(true);

        verifyNoMoreInteractions(view);
    }
}