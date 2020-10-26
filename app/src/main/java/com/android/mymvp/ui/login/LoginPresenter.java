package com.android.mymvp.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;

import com.android.mymvp.ui.login.Listener.LoginListener;
import com.android.mymvp.base.BasePresenter;

/**
 * Description
 * <p>
 *
 * @author qricis on 2020/10/23 9:24
 * @version 1.0.0
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login() {
        if (mModel != null) {
            String name = getView().getUserName();
            String password = getView().getPassword();
            //此时LoginListener作为匿名内部类是持有外部类的引用的。
            ((LoginMode)mModel).login(name,password,new LoginListener() {
                @Override
                public void onSuccess() {
                    getView().onLoginSuccess();
                }

                @Override
                public void onFailed() {
                     getView().onLoginFailed();
                }
            });
        }
    }
}