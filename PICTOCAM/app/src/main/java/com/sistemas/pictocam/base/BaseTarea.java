package com.sistemas.pictocam.base;

import android.os.AsyncTask;

/**
 * Created by Luis Puc on 26/02/2018.
 */

public abstract class BaseTarea extends AsyncTask<String, String, Object> {

    @Override
    protected abstract void onPreExecute();

    @Override
    protected abstract Object doInBackground(String... params);

    @Override
    protected abstract void onPostExecute(Object result);

    public abstract void onSuccess(Object response);

    public abstract void onError(Object error);

}
