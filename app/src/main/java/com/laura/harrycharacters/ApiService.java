package com.laura.harrycharacters;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/api/characters")
    Call<List<CharactersResponse>> getCharacters();

    @GET("api/character/{id}")
    Call<List<CharactersResponse>> getCharacterDetail(@Path("id") String superHeroId);
}


