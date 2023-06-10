package com.g26.myeducationalapp.util.callback;

public abstract class Callback<T> {
    public abstract void onSuccess(T result);

    public void onFailed() {}
}
