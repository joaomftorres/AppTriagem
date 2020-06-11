package pt.ipg.apptriagem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPacientes extends AppCompatActivity {

    TextView textViewDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        textViewDados = (TextView) findViewById(R.id.textViewDados);

        try {
            DatabaseTriagem db = new DatabaseTriagem(this);
            db.open();
            textViewDados.setText(db.getData());
            db.close();
        }
        catch (SQLException e)
        {
            Toast.makeText(ActivityPacientes.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
