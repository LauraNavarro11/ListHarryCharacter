package com.laura.harrycharacters;


import static com.laura.harrycharacters.HarryPotterDetailActivity.EXTRA_ID;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.laura.harrycharacters.databinding.ActivityMainBinding;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private HarryAdapter adapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initRecyclerView();
        searchByName();
        String id = getIntent().getStringExtra(EXTRA_ID);
        Log.d("HarryPotterDetail", "ID recibida en onCreate: " + id);
    }

    private void initRecyclerView() {
        adapter = new HarryAdapter(this::navigateDetail);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://hp-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void searchByName() {
        new CharactersAsyncTask().execute();
    }

    private class CharactersAsyncTask extends AsyncTask<Void, Void, List<CharactersResponse>> {

        @Override
        protected List<CharactersResponse> doInBackground(Void... voids) {
            try {
                Response<List<CharactersResponse>> myResponse = getRetrofit().create(ApiService.class).getCharacters().execute();
                if (myResponse.isSuccessful()) {
                    return myResponse.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<CharactersResponse> response) {
            if (response != null) {
                new Handler(Looper.getMainLooper()).post(() -> adapter.updateList(response));
            }
        }
    }
    private void navigateDetail(String id) {
        Intent intent = new Intent(this, HarryPotterDetailActivity.class);
        intent.putExtra(EXTRA_ID, id);
        startActivity(intent);
    }
}
