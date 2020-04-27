package pt.ipg.apptriagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttontriagem(View view) {

        Intent intenttriagem = new Intent(this, ActivityTriagens.class);

        startActivity(intenttriagem);
    }

    public void buttonpacientes (View view) {

        Intent intentpacientes = new Intent(this, ActivityPacientes.class);

        startActivity(intentpacientes);
    }
}
