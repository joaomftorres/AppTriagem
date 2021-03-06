package pt.ipg.apptriagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ActivityTriagens extends AppCompatActivity {

    Button buttonconsultar;
    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triagens);
        buttonconsultar = (Button) findViewById(R.id.buttonconsultar);

        mydb = new DatabaseHelper(this);
        viewAll();



    }

    public void buttonnovatriagem(View view) {

        Intent intentnovatriagem = new Intent(this, ActivityNovaTriagem.class);

        startActivity(intentnovatriagem);
    }

    public void viewAll () {

        buttonconsultar.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if(res.getCount() == 0){
                            showMessage("Erro", "Não existem dados");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("Nome :"+ res.getString(1)+"\n");
                            buffer.append("Numero de Utente: "+ res.getString(2)+"\n");
                            buffer.append("Data de Nascimento :"+ res.getString(3)+"\n\n");
                            buffer.append("Data da Triagem :"+ res.getString(5)+"\n");
                            buffer.append("Sintomas :"+ res.getString(4)+"\n\n-----------------------------------------------------------------------\n\n");
                        }

                        showMessage("Resultados das Triagens", buffer.toString());
                    }
                }
        );
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
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
        }
        return super.onOptionsItemSelected(item);
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
