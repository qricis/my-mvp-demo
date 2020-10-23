package com.android.mymvp.view;

/**
 * Description
 * <p>
 * 继承自IView
 * @author qricis on 2020/10/23 9:23
 * @version 1.0.0
 */
public interface ILoginView extends IView{

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
     * 登陆失败
     */
    void onLoginFailed();
}
