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
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_triagem);

        db = new DatabaseHelper(this);

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
            editTextNomeUtente.setError(getString(R.string.erroNomeUtente));
            editTextNomeUtente.requestFocus();
            return;
        }

        if (numero_utente.length() != 9) {
            editTextNumeroUtente.setError(getString(R.string.erroNumeroUtente));
            editTextNumeroUtente.requestFocus();
            return;
        }


        if (idade.length() == 0) {
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
}
