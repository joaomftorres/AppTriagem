package pt.ipg.apptriagem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityNovaTriagem extends AppCompatActivity {

    EditText editTextNomeUtente, editTextIdade, editTextNumeroUtente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_triagem);

        editTextNomeUtente = (EditText) findViewById(R.id.editTextNomeUtente);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        editTextNumeroUtente = (EditText) findViewById(R.id.editTextNumeroUtente);
    }


    public void buttonSubmeterTriagem(View v)
    {
        String nome = editTextNomeUtente.getText().toString().trim();
        String numero_utente = editTextNumeroUtente.getText().toString().trim();
        String idade = editTextIdade.getText().toString().trim();

        if (nome.length() == 0) {
            editTextNomeUtente.setError("Erro: É necessário inserir o Nome do Utente");
            editTextNomeUtente.requestFocus();
            return;
        }

        if (numero_utente.length() != 9) {
            editTextNumeroUtente.setError("Erro: O Numero de Utente deve ser contituido por 9 digitos");
            editTextNumeroUtente.requestFocus();
            return;
        }


        if (idade.length() == 0) {
            editTextIdade.setError("Erro: Introduza a Idade");
            editTextIdade.requestFocus();
            return;
        }

        int idd;
        idd = Integer.parseInt(idade);

        if (idd > 125) {
            editTextIdade.setError("Erro: Idade Inválida");
            editTextIdade.requestFocus();
            return;
        }



        try
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
            }
        }
}
