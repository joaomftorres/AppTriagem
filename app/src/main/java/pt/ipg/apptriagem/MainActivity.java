package pt.ipg.apptriagem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    Button buttonpacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);

        buttonpacientes = (Button) findViewById(R.id.buttonpacientes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sair:
                System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    public void buttontriagem(View view) {

        Intent intenttriagem = new Intent(this, ActivityTriagens.class);

        startActivity(intenttriagem);
    }

    public void buttonpacientes(View view) {

        Intent intentpacientes = new Intent(this, ActivityPacientes.class);

        startActivity(intentpacientes);
    }


}
