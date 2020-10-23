package com.android.mymvp.presenter;

import com.android.mymvp.model.Listener.LoginListener;
import com.android.mymvp.model.LoginMode;
import com.android.mymvp.view.ILoginView;
import com.android.mymvp.view.IView;

import java.lang.ref.WeakReference;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/10/23 9:24
 * @version 1.0.0
 */
public class LoginPresenter extends PresenterFather{

    public LoginPresenter(ILoginView loginView) {
        this.mIModel = new LoginMode();
        this.mViewReference = new WeakReference<IView>(loginView);
    }

    public void login() {
        if (mIModel != null && mViewReference != null && mViewReference.get() != null) {
            ILoginView loginView = (ILoginView)mViewReference.get();
            String name = loginView.getUserName();
            String password = loginView.getPassword();
            loginView = null;
            //
            ((LoginMode)mIModel).login(name,password,new LoginListener() {
                @Override
                public void onSuccess() {
                    if (mViewReference.get() != null) {
                        ((ILoginView)mViewReference.get()).onLoginSuccess();
                    }
                }

                @Override
                public void onFailed() {
                    if (mViewReference.get() != null) {
                        ((ILoginView)mViewReference.get()).onLoginFailed();
                    }
                }
            });
        }
    }
}