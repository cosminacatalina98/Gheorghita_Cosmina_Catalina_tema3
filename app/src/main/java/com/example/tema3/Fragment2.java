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


public class Fragment2 extends Fragment {

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;

    public Fragment2() {
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
        View layout=inflater.inflate(R.layout.fragment_2, container, false);
        recyclerView=layout.findViewById(R.id.to_dos);
        layoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        return  layout;
    }

    private void GetToDoItems(int id){
        String url="https://my-json-server.typicode.com/todos?userId="+id;
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                List<ToDo> toDos = new ArrayList<>();

                for (int index = 0; index < response.length(); index++) {
                    try {
                        ToDo item = new ToDo().fromJSON(response.getJSONObject(index));
                        toDos.add(item);
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

