package com.example.guessnumber.iu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessnumber.R;
import com.example.guessnumber.data.Juego;
import com.example.guessnumber.databinding.ActivityPlayBinding;

/**
 * En esta clase se realizan las principales operaciones del juego (acertar el número).
 */
public class PlayActivity extends AppCompatActivity {
    private TextView tvAyuda;
    private EditText etNum;
    int intentosh;
    int num;
    int numP;
    private int restantes;
    private ActivityPlayBinding binding;
    private static final String TAG="GuessNumber";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPlayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        Juego juego=bundle.getParcelable("juego");
        binding.setJuego(juego);
        binding.btnProbar.setOnClickListener(view -> Probar());
        tvAyuda=findViewById(R.id.tvAyuda);
        etNum=findViewById(R.id.etNum);
         num=(int)(Math.random()*100+1);
        int intentos =  Integer.parseInt(juego.getIntentos());
         restantes=intentos;

        Log.d(TAG,"PlayActivity -> onCreate()");

    }
    public void Probar(View view){Probar();}
    public void Probar(){
        restantes-=1;
        intentosh+=1;
        try {

            numP=Integer.parseInt(etNum.getText().toString());

        }
        catch (Exception e)
        {
            tvAyuda.setText("Introduce un número");restantes+=1;
            intentosh-=1;
        }
        if (restantes==0||num==numP){
            Final();
        }
        if (numP<num)
        {
            tvAyuda.setText("El número introducido es menor");
        }
        if (numP>num)
        {tvAyuda.setText("El número introducido es mayor");}
        etNum.setText("");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"PlayActivity -> onDestroy()");
    }
    public void Final(){

        Bundle bundle=new Bundle();
        bundle.putParcelable("juego",binding.getJuego());
        bundle.putInt("número",num);
        bundle.putInt("intentos",intentosh);
        bundle.putInt("restantes",restantes);
        Intent intent = new Intent(PlayActivity.this, EndPlayActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
