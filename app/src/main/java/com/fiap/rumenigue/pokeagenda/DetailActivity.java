package com.fiap.rumenigue.pokeagenda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fiap.rumenigue.pokeagenda.api.PokeApi;
import com.fiap.rumenigue.pokeagenda.model.Pokemon;
import com.fiap.rumenigue.pokeagenda.model.TypeSlot;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    ImageView ivFirstColor, ivSecondColor, ivPokeImage;
    TextView tvPokeName, tvNational, tvType, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivPokeImage = (ImageView)findViewById(R.id.ivPokeImage);
        ivFirstColor = (ImageView)findViewById(R.id.ivFirstColor);
        ivSecondColor = (ImageView)findViewById(R.id.ivSecondColor);
        tvPokeName = (TextView)findViewById(R.id.tvPokeName);
        tvNational = (TextView)findViewById(R.id.tvNational);
        tvType = (TextView)findViewById(R.id.tvType);
        tvDesc = (TextView)findViewById(R.id.tvDesc);

        if (getIntent() != null){
            // hit the api
            PokeApi api = getRetrofit().create(PokeApi.class);
            api.getPokemon(getIntent().getIntExtra("POKE_NUMBER", 0))
                    .enqueue(new Callback<Pokemon>() {
                        @Override
                        public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                            if (response.body() != null) {
                                String firstType = "", secondType = "";

                                List<TypeSlot> types = response.body().getTypes();

                                for (TypeSlot slot :
                                        types) {
                                    if (slot.getSlot() == 1)
                                        firstType = slot.getType().name;
                                    else if (slot.getSlot() == 2)
                                        secondType = slot.getType().name;
                                }

                                changeBackground(firstType, secondType);

                                String strTypes = firstType;
                                if (!secondType.isEmpty()) strTypes += "/" + secondType;

                                // bind data
                                tvPokeName.setText(response.body().getName());
                                tvType.setText(
                                        String.format(
                                                getResources().getString(R.string.type),
                                                strTypes));
                                tvNational.setText(
                                        String.format(
                                                getResources().getString(R.string.number),
                                                String.valueOf(response.body().getId())));
                                tvPokeName.setText(response.body().getName());
                                Picasso
                                    .with(DetailActivity.this)
                                    .load(response.body().getSprites().getFront_default())
                                    .into(ivPokeImage);
                            }
                        }

                        @Override
                        public void onFailure(Call<Pokemon> call, Throwable t) {
                            Toast.makeText(DetailActivity.this, "Shit happened!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void changeBackground(String firstType, String secondType){
        ivFirstColor.setImageResource(getColorResourceID(firstType));

        if (!secondType.isEmpty())
            ivSecondColor.setImageResource(getColorResourceID(secondType));
        else
            ivSecondColor.setImageResource(getColorResourceID(firstType));
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private int getColorResourceID(String type){
        switch (type.toLowerCase()){
            case "normal":
                return R.color.normal;
            case "fighting":
                return R.color.fighting;
            case "flying":
                return R.color.flying;
            case "poison":
                return R.color.poison;
            case "ground":
                return R.color.ground;
            case "rock":
                return R.color.rock;
            case "bug":
                return R.color.bug;
            case "ghost":
                return R.color.ghost;
            case "steel":
                return R.color.steel;
            case "fire":
                return R.color.fire;
            case "water":
                return R.color.water;
            case "grass":
                return R.color.grass;
            case "electric":
                return R.color.electric;
            case "psychic":
                return R.color.psychic;
            case "ice":
                return R.color.ice;
            case "dragon":
                return R.color.dragon;
            case "dark":
                return R.color.dark;
            case "fairy":
                return R.color.fairy;
            default:
                return R.color.defaultColor;
        }
    }
}
