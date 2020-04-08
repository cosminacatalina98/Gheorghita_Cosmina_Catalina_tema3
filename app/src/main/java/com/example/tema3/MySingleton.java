package com.example.tema3;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private MySingleton(Context context){
        ctx=context;
        requestQueue=getRequestQueque();
    }

    public static synchronized MySingleton getInstance(Context context){
        if (instance==null){
            instance=new MySingleton(context);

        }
        return instance;
    }

    private RequestQueue getRequestQueque() {
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(ctx.getApplicationContext());

        }
        return requestQueue;
    }
    public <T> void addToRequestQueue(Request<T> req){
        getRequestQueque().add(req);
    }
}
