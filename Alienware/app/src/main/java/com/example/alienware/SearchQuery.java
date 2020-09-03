package com.example.alienware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchQuery extends AppCompatActivity {

    ListView querylist;
    EditText searchbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_query);

        querylist=findViewById(R.id.querylist);
        searchbar=findViewById(R.id.searchbar);


        Retrofit retrofit = new Retrofit.Builder().baseUrl( "https://images-api.nasa.gov/").addConverterFactory(GsonConverterFactory.create()).build();
        searchQueryHolder searchQueryHolder=retrofit.create(com.example.alienware.searchQueryHolder.class);

        searchbar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().matches("")) {
                    Call<NasaIDObject> nasaIDOutputCall = searchQueryHolder.getOutput("" + charSequence);
                    nasaIDOutputCall.enqueue(new Callback<NasaIDObject>() {
                        @Override
                        public void onResponse(Call<NasaIDObject> call, Response<NasaIDObject> response) {
                         if (!response.isSuccessful())
                         {

                         }
                         else
                         {
                             NasaIDObject Output = response.body();
                             if (Output!= null && Output.collection.items.size()>0)
                             {
                                 List<String> Titles = new ArrayList();
                                 for (int z = 0; z < Output.collection.items.size();z++)
                                 {
                                     Titles.add(Output.collection.items.get(z).data.get(0).title);
                                 }
                                 ArrayAdapter<String> adapter = new ArrayAdapter<String>(SearchQuery.this,
                                         android.R.layout.simple_list_item_1,Titles);
                                 querylist.setAdapter(adapter);

                                 querylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                     @Override
                                     public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                         String nasaid = Output.collection.items.get(i).data.get(0).nasa_id;
                                         Intent intent=new Intent(SearchQuery.this,SEARCHQUERYIMAGE.class);
                                         intent.putExtra("nasaid",nasaid);
                                         intent.putExtra("mediatype",Output.collection.items.get(i).data.get(0).media_type);
                                         startActivity(intent);

                                     }
                                 });

                             }

                         }
                        }

                        @Override
                        public void onFailure(Call<NasaIDObject> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }
}