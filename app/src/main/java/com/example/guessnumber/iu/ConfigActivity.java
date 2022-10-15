package com.example.guessnumber.iu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessnumber.R;
import com.example.guessnumber.data.Juego;
import com.example.guessnumber.databinding.ActivityConfigBinding;

/**
 * En esta activity se introduce el nombre del usuario
 * y el número de intentos para el juego.
 * * @author Ciro León Espinosa
 *  * @version 1.0
 *  * @see android.widget.Button
 *  * @see android.app.Activity
 *  * @see android.content.Intent
 *  * @see android.os.Bundle
 */
public class ConfigActivity extends AppCompatActivity {

private EditText nombre, intentos;
private String nom, intento;
    private ActivityConfigBinding binding;
    private static final String TAG="GuessNumber";
    //region CICLO DE VIDA DE LA ACTIVITY
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityConfigBinding.inflate((getLayoutInflater()));
        setContentView(binding.getRoot());
        nombre=findViewById(R.id.etPlayer);
        intentos=findViewById(R.id.etIntentos);

        binding.btJugar.setOnClickListener(view -> Configuracion());
        Log.d(TAG,"ConfigActivity -> onCreate()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"ConfigActivity -> onStart()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"ConfigActivity -> onStop()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"ConfigActivity -> onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
        Log.d(TAG,"ConfigActivity -> onDestroy()");
    }
    //endregion
    public void Configuracion(View view){

            Configuracion();
    }
    public void Configuracion(){
        //Se comprueba que se ha introducido el número de intentos y el usuario y se envia

        if(Comprobar()) {

            binding.setJuego(new Juego(nom,intento));
            Bundle bundle = new Bundle();
            bundle.putParcelable("juego",binding.getJuego());
            Intent intent = new Intent(this, PlayActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);


        }



    }
    public boolean Comprobar()
    {
        try {
            nom=nombre.getText().toString();
            intento=intentos.getText().toString();
            return Integer.parseInt(intento) > 0 && nom.length() > 0;
        }
        catch (Exception e)
        {
            return false;
        }

    }
    /**
     * Crea el menu de opciones en la vista. Se devuelve true para indicar al SO que se ha realizado la opcion de modificar el menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Este metodo callback
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_aboutus:
                //Toast.makeText(this, "Se ha pulsado sobre About us", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }
}