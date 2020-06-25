package pt.ipg.apptriagem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityNovaTriagem extends AppCompatActivity {

    EditText editTextNomeUtente, editTextIdade, editTextNumeroUtente, editTextSintomas;
    DatabaseHelper mydb;
    Button buttonSubmeterTriagem;
    Button buttonAdicionarSintoma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_triagem);

        mydb = new DatabaseHelper(this);

        editTextNomeUtente = (EditText) findViewById(R.id.editTextNomeUtente);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        editTextNumeroUtente = (EditText) findViewById(R.id.editTextNumeroUtente);
        buttonSubmeterTriagem = (Button) findViewById(R.id.buttonSubmeterTriagem);


        editTextSintomas = (EditText) findViewById(R.id.editTextSintomas);
        /*buttonAdicionarSintoma = (Button) findViewById(R.id.buttonAdicionarSintoma);
        buttonAdicionarSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editTextSintomas.getText().toString();
                if(editTextSintomas.length() != 0){
                    insertDataSintoma(newEntry);
                    editTextSintomas.setText("");
                } else{
                    Toast.makeText(ActivityNovaTriagem.this, "Insira um sintoma", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        AddData();

    }

    /*public void insertDataSintoma(String newEntry){
        boolean insertData = mydb.insertDataSintoma(newEntry);

        if(insertData == true){
            Toast.makeText(ActivityNovaTriagem.this, "Sintoma Inserido", Toast.LENGTH_LONG).show();
        } else{
            Toast.makeText(ActivityNovaTriagem.this, "Erro ao Inserir Sintoma", Toast.LENGTH_LONG).show();
        }
    }*/

    public void AddData(){
        buttonSubmeterTriagem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertData(editTextNomeUtente.getText().toString(),
                                editTextNumeroUtente.getText().toString(),
                                editTextIdade.getText().toString(),
                                editTextSintomas.getText().toString());


                        if(isInserted==true) {
                            Toast.makeText(ActivityNovaTriagem.this, "Guardado com Sucesso", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(ActivityNovaTriagem.this, "Não Guardado", Toast.LENGTH_SHORT).show();
                        }
                        
                        editTextNumeroUtente.setText("");
                        editTextIdade.setText("");
                        editTextNomeUtente.setText("");
                        editTextSintomas.setText("");

                    }


                }
        );

    }




        /*public void validacao() {
            if (editTextNomeUtente.length() == 0) {
                editTextNomeUtente.setError(getString(R.string.erroNomeUtente));
                editTextNomeUtente.requestFocus();
                return;
            }

            if (editTextNumeroUtente.length() != 9) {
                editTextNumeroUtente.setError(getString(R.string.erroNumeroUtente));
                editTextNumeroUtente.requestFocus();
                return;
            }


            String idade = editTextIdade.getText().toString().trim();
            if (editTextIdade.length() == 0) {
                editTextIdade.setError(getString(R.string.erroIntroduzirIdade));
                editTextIdade.requestFocus();
                return;
            }

            int idd;
            idd = Integer.parseInt(idade);

            if (idd > 125) {
                editTextIdade.setError(getString(R.string.erroIdadeInvalida));
                editTextIdade.requestFocus();
                return;
            }
        }*/


        /*try
        {
            DatabaseTriagem db = new DatabaseTriagem(this);
            db.open();
            db.createEntry(nome, numero_utente, idade);
            db.close();
            Toast.makeText(ActivityNovaTriagem.this, "Paciente Guardado!", Toast.LENGTH_SHORT).show();
            editTextNomeUtente.setText("");
            editTextNumeroUtente.setText("");
            editTextIdade.setText("");
        }
            catch (SQLException e)
            {
            Toast.makeText(ActivityNovaTriagem.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }*/

}
