package com.android.mymvp.presenter;

import com.android.mymvp.model.IModel;
import com.android.mymvp.view.IView;

import java.lang.ref.WeakReference;

/**
 * Description
 * <p>
 * 所有presenter的父类，因为presenter会持有view以及model部分
 * 索性写到总父类里
 * @author qricis on 2020/10/23 9:24
 * @version 1.0.0
 */
public class PresenterFather {

    protected IModel mIModel;

    /**
     * view最好用一个弱引用
     * */
    protected WeakReference<IView> mViewReference;
}
