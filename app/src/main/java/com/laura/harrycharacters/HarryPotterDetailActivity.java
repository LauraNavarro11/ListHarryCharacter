package com.laura.harrycharacters;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.laura.harrycharacters.databinding.ActivityHarryPotterDetailBinding;

import kotlinx.coroutines.Dispatchers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HarryPotterDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "extra_id";
    private ActivityHarryPotterDetailBinding binding;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHarryPotterDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String id = getIntent().getStringExtra(EXTRA_ID);
        getHarryDetailInformation(id);
        Log.d("HarryPotterDetail", "ID recibida: " + id);
    }

    private void getHarryDetailInformation(String id) {
        executorService.execute(() -> {
            try {
                // Verificar que el valor de "id" no sea nulo
                if (id != null) {
                    Call<List<CharactersResponse>> harryDetailCall = getRetrofit().create(ApiService.class).getCharacterDetail(id);
                    Response<List<CharactersResponse>> response = harryDetailCall.execute();

                    handler.post(() -> {
                        if (response.isSuccessful() && response.body() != null) {
                            createdUi(response.body());
                        } else {
                            Log.e("HarryPotterDetail", "Error en la respuesta o cuerpo nulo");
                        }
                    });
                } else {
                    Log.e("HarryPotterDetail", "El valor de 'id' es nulo");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("HarryPotterDetail", "Excepción durante la llamada a la API");
            }
        });
    }



    private void createdUi(List<CharactersResponse> body) {
        if (body != null && !body.isEmpty()) {
            CharactersResponse character = body.get(0);
            binding.tvHarryDetailName.setText(character.getName());
            binding.tvHarryDetailAncestry.setText(character.getAncestry());
            binding.tvHarryDetailEyes.setText(character.getEyeColour() + " eyes");
            binding.tvHarryDetailYear.setText(character.getYearOfBirth() + " year Of Birth");
            binding.tvHarryDetailactor.setText(character.getActor() + " actor");
            binding.tvHarryDetailPatronus.setText(character.getPatronus() + " patronus");
        } else {
            Log.e("HarryPotterDetail", "No se encontraron datos válidos");
        }
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://hp-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}