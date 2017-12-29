package com.ljb.fire.base;

/**
 * Created by meloon on 2017/12/29.
 */

public class BasePresenter<V extends IBaseView> {

    private V mView;

    public void attachView(V view) {
        mView = view;
    }

    public void dettachView() {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }
}
