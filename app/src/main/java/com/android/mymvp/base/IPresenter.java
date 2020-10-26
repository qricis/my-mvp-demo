package com.android.mymvp.base;

import androidx.lifecycle.LifecycleOwner;

/**
 * Description
 * <p>
 * MVP的M
 * @author qricis on 2020/10/26 14:18
 * @version 1.0.0
 */
public interface IPresenter<V extends IView> {

    /**
     * 注入{@link IView}对象
     *
     * @param view  {@link IView}对象
     * @param owner {@link LifecycleOwner}对象
     */
    void attachView(V view, LifecycleOwner owner);

    /**
     * 销毁{@link IView}对象
     */
    void detachView();
}
