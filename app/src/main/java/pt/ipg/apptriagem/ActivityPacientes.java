package pt.ipg.apptriagem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityPacientes extends AppCompatActivity {

    DatabaseHelper mydb;
    Button buttonVer;
    Button buttonUpdate;
    Button buttonDelete;
    EditText editTextInserirID, editTextInserirID2;
    EditText editTextNomeUtente, editTextIdade, editTextNumeroUtente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        mydb = new DatabaseHelper(this);

        editTextNomeUtente = (EditText) findViewById(R.id.editTextNomeUtente);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        editTextNumeroUtente = (EditText) findViewById(R.id.editTextNumeroUtente);
        editTextInserirID = (EditText) findViewById(R.id.editTextInserirID);
        editTextInserirID2 = (EditText) findViewById(R.id.editTextInserirID2);
        buttonVer = (Button) findViewById(R.id.buttonVer);
        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        viewAll();
        UpdateData();
        DeleteData();
    }

    public void DeleteData(){
        buttonDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mydb.deleteData(editTextInserirID2.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(ActivityPacientes.this, "Dados Eliminados",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(ActivityPacientes.this, "Falha ao Eliminar",Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void UpdateData(){
        buttonUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = mydb.updateData(editTextInserirID.getText().toString(),
                                editTextNomeUtente.getText().toString(),
                                editTextIdade.getText().toString(),
                                editTextNumeroUtente.getText().toString());
                        if(isUpdated == true) {
                            Toast.makeText(ActivityPacientes.this, "Dados Alterados",Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(ActivityPacientes.this, "Falha na Alteração",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }


    public void buttontriagem(View view) {

        Intent intenttriagem = new Intent(this, ActivityTriagens.class);

        startActivity(intenttriagem);
    }

    public void viewAll () {

        buttonVer.setOnClickListener(
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
                            buffer.append("Idade :"+ res.getString(3)+"\n\n");
                        }

                        showMessage("Lista de Pacientes", buffer.toString());
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

