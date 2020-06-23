package pt.ipg.apptriagem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityPacientes extends AppCompatActivity {

    private List<Data> notesList = new ArrayList<>();
    private RecyclerView recyclerView;
    DatabaseHelper db;
    public static NotesAdapter mAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);
        recyclerView = findViewById(R.id.recyclerview);
        //where you use object of databasehelper (db)
        //you have to initialize it.
        db = new DatabaseHelper(this);
        //get all data from database and store in list (noteslist)
        //then pass to recyclerview.

        notesList.addAll(db.getAllDataFromDb());
        mAdapter1 = new NotesAdapter(this, notesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter1);


    }
}

