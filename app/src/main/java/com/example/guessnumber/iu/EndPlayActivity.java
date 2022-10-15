package com.example.guessnumber.iu;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessnumber.R;
import com.example.guessnumber.data.Juego;
import com.example.guessnumber.databinding.ActivityEndplayactivityBinding;

/**
 * Esta es la clase final muestra el resultado del juego.
 */
public class EndPlayActivity extends AppCompatActivity {
    private static final String TAG="GuessNumber";
    private ActivityEndplayactivityBinding binding;
    private TextView info;
    String s;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
        Log.d(TAG,"EndPlayActivity -> onDestroy()");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEndplayactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        Juego juego=bundle.getParcelable("juego");
        binding.setJuego(juego);
        info=findViewById(R.id.tvRes);

        if (Integer.parseInt(juego.getIntentos())==1)
        {
            s=".";
        }
        else
        {s="s.";}

        if (bundle.getInt("restantes")==0)
        {
            String texto1="El jugador " + juego.getNombre() +
                " no ha hacertado el " +
                "número (" + bundle.getInt("número") + ") " +
                "a pesar de haber tenido "+juego.getIntentos()+" intento"+s;
            info.setText(texto1);
        }
        else {
            String texto2="El jugador " + juego.getNombre() + " ha hacertado el número " + bundle.getInt("número") + " en " + bundle.getInt("intentos") + " intento"+s;
            info.setText(texto2);
        }
        Log.d(TAG,"EndPlayActivity -> onCreate()");
    }

}
