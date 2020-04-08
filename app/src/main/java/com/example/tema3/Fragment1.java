package com.example.tema3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class Fragment1 extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView ListaUtilizatori;


    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_1, container, false);
        ListaUtilizatori=layout.findViewById(R.id.lista_utilizatori);
        layoutManager= new LinearLayoutManager(getContext());
        ListaUtilizatori.setLayoutManager(layoutManager);

        GetUserJson();



        return layout;
    }

    private void GetUserJson(){
        String url="https://my-json-server.typicode.com/MoldovanG/JsonServer/users?fbclid=IwAR2Q898TrBtq2uW9KvL7BMpgcUidO8oPSKw_kc4s_QT3CLzik8EI4MnG7DE";
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<User> users = new ArrayList<>();

                for (int index = 0; index < response.length(); index++) {
                    try {
                        User item = new User().fromJSON(response.getJSONObject(index));
                        users.add(item);
                    } catch (JSONException ex) {
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Volley error:"+ error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        );

        MySingleton.getInstance(getContext()).addToRequestQueue(jsonArrayRequest);
    }



}
