package com.android.mymvp.base;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description
 * <p>
 * 所有presenter的父类，因为presenter会持有view以及model部分
 * 索性写到总父类里
 * @author qricis on 2020/10/23 9:24
 * @version 1.0.0
 */
public class BasePresenter<V extends IView> implements IPresenter<V>{

    protected IModel mModel;
    /**
     * V层相关
     */
    private WeakReference<V> mViewRef;
    private V mProxyView;
    private WeakReference<LifecycleOwner> mOwnerRef;
    private LifecycleOwner mOwnerProxy;

    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    @Override
    public void attachView(@NonNull final V view, @NonNull final LifecycleOwner owner) {
        // 弱引用
        this.mViewRef = new WeakReference<>(view);
        this.mOwnerRef = new WeakReference<>(owner);

        // 动态代理
        this.mProxyView = getViewProxy(view);
        this.mOwnerProxy = getOwnerProxy();

    }

    @Override
    public void detachView() {
        // 销毁View
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
        }

        // 清空当前Handler的消息队列
        mUiHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 获取V层{@link IView}对象
     *
     * @return {@link IView}对象
     */
    protected V getView() {
        return mProxyView;
    }

    /**
     * 获取{@link LifecycleOwner}对象
     *
     * @return {@link LifecycleOwner}对象
     */
    protected LifecycleOwner getLifecycleOwner() {
        return mOwnerProxy;
    }

    /**
     * 获取主线程Handler
     *
     * @return 主线程Handler
     */
    @NonNull
    public Handler getUiHandler() {
        return mUiHandler;
    }

    /**
     * 在Ui主线程执行某些任务
     *
     * @param action 任务
     */
    protected void runOnUiThread(@NonNull final Runnable action) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            mUiHandler.post(action);

        } else {
            action.run();
        }
    }

    /**
     * P层和V层是否关联
     *
     * @return {@code true}: 是 <br> {@code false}: 否
     */
    private boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    /**
     * P层和{@link LifecycleOwner}是否关联
     *
     * @return {@code true}: 是 <br> {@code false}: 否
     */
    private boolean isOwnerAttached() {
        return mOwnerRef != null && mOwnerRef.get() != null;
    }

    /**
     * 获取{@code view}的动态代理, 在P层不用每次都判断V层是否为空
     *
     * @param view MVP View层
     * @return {@code view}的动态代理
     */
    @SuppressWarnings("unchecked")
    @NonNull
    private V getViewProxy(@NonNull final V view) {
        return (V) Proxy.newProxyInstance(view.getClass().getClassLoader(),
                view.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 如果V层没被销毁, 执行V层的方法
                        if (isViewAttached()) {
                            return method.invoke(mViewRef.get(), args);
                        }

                        return null;
                    }
                });
    }

    /**
     * 获取{@link LifecycleOwner}的动态代理, 避免每次要判空的麻烦
     *
     * @return {@link LifecycleOwner}的动态代理
     */
    @NonNull
    private LifecycleOwner getOwnerProxy() {
        return (LifecycleOwner) Proxy.newProxyInstance(
                LifecycleOwner.class.getClassLoader(),
                new Class<?>[]{LifecycleOwner.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 如果LifecycleOwner没被销毁, 执行其方法
                        if (isOwnerAttached()) {
                            return method.invoke(mOwnerRef.get(), args);
                        }

                        return null;
                    }
                });
    }

}
