package pt.ipg.apptriagem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
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
        /*buttonconsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTriagens.this, ConsultaActivity.class);
                startActivity(intent);
            }
        });*/



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
                            buffer.append("Numero de Utente :"+ res.getString(2)+"\n");
                            buffer.append("Idade :"+ res.getString(3)+"\n");
                            buffer.append("Sintomas :"+ res.getString(4)+"\n\n");
                        }

                        showMessage("Resultados das Triagens", buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
