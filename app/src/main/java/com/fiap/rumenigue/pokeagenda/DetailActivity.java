package com.fiap.rumenigue.pokeagenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
            getIntent().getIntExtra("POKE_NUMBER", 0);

            // hit the api

            String firstType, secondType;

            firstType = "";
            secondType = "";

            // bind api's return
            changeBackground(firstType, secondType);
        }
    }

    private void changeBackground(String firstType, String secondType){
        ivFirstColor.setImageResource(getColorResourceID(firstType));
        ivSecondColor.setImageResource(getColorResourceID(secondType));
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
