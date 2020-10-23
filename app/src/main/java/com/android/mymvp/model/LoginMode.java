package com.android.mymvp.model;

import com.android.mymvp.model.Listener.LoginListener;

/**
 * Description
 * <p>
 * model负责数据以及业务逻辑
 * @author qricis on 2020/10/23 9:24
 * @version 1.0.0
 */
public class LoginMode implements IModel {

    private String mUserName = "qricis";
    private String mPassward = "123456";

    public void login(String userName, String password, LoginListener loginListener) {
        if (loginListener == null) {
            return;
        }
        if (mUserName.equals(userName) && mPassward.equals(password)) {
            loginListener.onSuccess();
        } else {
            loginListener.onFailed();
        }
    }
}
