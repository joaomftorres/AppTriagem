package pt.ipg.apptriagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityTriagens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triagens);
    }

    public void buttonnovatriagem(View view) {

        Intent intentnovatriagem = new Intent(this, ActivityNovaTriagem.class);

        startActivity(intentnovatriagem);
    }
}
