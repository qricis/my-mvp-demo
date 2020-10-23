package com.android.mymvp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.mymvp.R;
import com.android.mymvp.presenter.LoginPresenter;
import com.android.mymvp.view.ILoginView;

/**
* Description
* <p>
*
* 展现了一个页面，实现了ILoginView，是V
* @author qricis on 2020/10/23 13:27
* @version 1.0.0
*/
public class LoginActivity extends AppCompatActivity implements ILoginView {
    private EditText mUserNameEdit;
    private EditText mPasswordEdit;
    private Button mLoginBtn;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setView();
        setData();
    }

    private void setView() {
        this.mUserNameEdit = findViewById(R.id.login_act_edit_user_name);
        this.mPasswordEdit = findViewById(R.id.login_act_edit_password);
        this.mLoginBtn = findViewById(R.id.login_act_edit_button);

        mLoginBtn.setOnClickListener(v -> {
            //隐藏软键盘
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(),0);
            mLoginPresenter.login();
        });
    }

    private void setData() {
        this.mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    public String getUserName() {
        return mUserNameEdit.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordEdit.getText().toString();
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(getApplicationContext(),R.string.login_success,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed() {
        Toast.makeText(getApplicationContext(),R.string.login_failed,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得在销毁的时候断掉引用链，养车良好的习惯
        this.mLoginPresenter = null;
    }
}
