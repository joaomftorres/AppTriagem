package pt.ipg.apptriagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityNovaTriagem extends AppCompatActivity {

    EditText editTextNomeUtente, editTextIdade, editTextNumeroUtente, editTextSintomas, editTextData;
    DatabaseHelper mydb;
    Button buttonSubmeterTriagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_triagem);

        mydb = new DatabaseHelper(this);

        editTextNomeUtente = (EditText) findViewById(R.id.editTextNomeUtente);
        editTextIdade = (EditText) findViewById(R.id.editTextIdade);
        editTextNumeroUtente = (EditText) findViewById(R.id.editTextNumeroUtente);
        editTextData = (EditText) findViewById(R.id.editTextData);
        buttonSubmeterTriagem = (Button) findViewById(R.id.buttonSubmeterTriagem);
        editTextSintomas = (EditText) findViewById(R.id.editTextSintomas);


        editTextNomeUtente.addTextChangedListener(textWatcher);
        editTextNumeroUtente.addTextChangedListener(textWatcher);
        editTextIdade.addTextChangedListener(textWatcher);
        editTextData.addTextChangedListener(textWatcher);

        checkFieldsForEmptyValues();

        AddData();

    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
        {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

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



    public void AddData(){
        buttonSubmeterTriagem.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertData(editTextNomeUtente.getText().toString(),
                                editTextNumeroUtente.getText().toString(),
                                editTextIdade.getText().toString(),
                                editTextSintomas.getText().toString(),
                                editTextData.getText().toString()
                        );


                        if(isInserted == true)
                            Toast.makeText(ActivityNovaTriagem.this,"Triagem guardada com sucesso!",Toast.LENGTH_LONG).show();




                        editTextNumeroUtente.setText("");
                        editTextIdade.setText("");
                        editTextNomeUtente.setText("");
                        editTextSintomas.setText("");
                        editTextData.setText("");

                    }


                }
        );

    }

    private  void checkFieldsForEmptyValues(){
        Button button = (Button) findViewById(R.id.buttonSubmeterTriagem);

        String s1 = editTextNomeUtente.getText().toString();
        String s2 = editTextNumeroUtente.getText().toString();
        String s3 = editTextIdade.getText().toString();
        String s4 = editTextData.getText().toString();

        if(s1.equals("") || s3.equals("") || s2.length() < 9 || s4.equals("")) {
            button.setEnabled(false);

            editTextIdade.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityNovaTriagem.this, "Deve Introduzir a Data de Nascimento do Paciente", Toast.LENGTH_SHORT).show();
                }
            });

            editTextNomeUtente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityNovaTriagem.this, "Deve Introduzir o Nome do Paciente", Toast.LENGTH_SHORT).show();
                }
            });

            editTextNumeroUtente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityNovaTriagem.this, "Deve confirmar se o Numero de Utente que introduziu tem 9 digitos!", Toast.LENGTH_SHORT).show();
                }
            });

            editTextData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityNovaTriagem.this, "Indique em que dia esta triagem foi feita.", Toast.LENGTH_SHORT).show();
                }
            });

            editTextSintomas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ActivityNovaTriagem.this, "Indique os Sintomas do Utente. Se não quiser introduzir Sintomas pode deixar este opção em branco.", Toast.LENGTH_SHORT).show();
                }
            });

        }


        else
        {
            button.setEnabled(true);
        }
    }

}



