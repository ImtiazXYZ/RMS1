package com.avijit.rms1.data.remote.responses;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class BaseModel {

    @Expose(serialize = false, deserialize = false)
    private String networkMessage ;

    @Expose(serialize = false, deserialize = false)
    private String networkError ;

    @Expose(serialize = false, deserialize = false)
    private boolean networkIsSuccessful = true;

    public String getNetworkMessage() {
        return networkMessage;
    }

    public void setNetworkMessage(String networkMessage) {
        this.networkMessage = networkMessage;
    }

    public String getNetworkError() {
        return networkError;
    }

    public void setNetworkError(String networkError) {
        this.networkError = networkError;
    }

    public boolean isNetworkIsSuccessful() {
        return networkIsSuccessful;
    }

    public void setNetworkIsSuccessful(boolean networkIsSuccessful) {
        this.networkIsSuccessful = networkIsSuccessful;
    }
    public BaseModel setUnSuccess(boolean a){
        setNetworkIsSuccessful(a);
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    public static class Builder<T extends BaseModel> {
        Class c;
        T t;
        public Builder(Class c){
            this.c = c;
        }
        public T setNetworkIsSuccessful(boolean n){
            try {
                t = (T) c.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            t.setNetworkIsSuccessful(n);
            return t;
        }
    }
}
