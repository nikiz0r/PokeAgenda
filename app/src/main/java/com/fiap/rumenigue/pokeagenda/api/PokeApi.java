package com.fiap.rumenigue.pokeagenda.api;

import com.fiap.rumenigue.pokeagenda.model.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApi {

    @GET("/pokemon/{number}")
    Call<Pokemon> getPokemon(@Path("number") int number);
}
