package com.android.mymvp.ui.login;

import com.android.mymvp.base.IModel;
import com.android.mymvp.base.IPresenter;
import com.android.mymvp.base.IView;
import com.android.mymvp.ui.login.Listener.LoginListener;
import com.android.mymvp.ui.main.MainActivity;

/**
 * Description
 * <p>
 * {@link MainActivity}契约接口
 *
 * @author qricis on 2020/10/26 14:11
 * @version 1.0.0
 */
public interface LoginContract {

    interface View extends IView {
        /**
         * 获取到用户输入的名称
         * @return 返回用户名称
         */
        String getUserName();

        /**
         * 获取到用户输入的密码
         * @return 返回用户密码
         */
        String getPassword();

        /**
         * 登录成功
         */
        void onLoginSuccess();

        /**
         * 登录失败
         */
        void onLoginFailed();
    }

    interface Presenter extends IPresenter<View> {

        /**
         * 登录验证
         * 成功调用activity的onSuccess(),错误调用onFailed()
         */
        void login();

    }

    interface Model extends IModel {

        /**
         * 登录验证,判断用户输入是否正确
         * @param userName 用户名称
         * @param password 用户密码
         * @param loginListener 登录监听
         */
        void login(String userName, String password, LoginListener loginListener);
    }
}
