package pt.ipg.apptriagem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static pt.ipg.apptriagem.ActivityPacientes.mAdapter1;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder> {

    private Context context;
    private List<Data> notesList;
    DatabaseHelper db;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_pacientes, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Data note = notesList.get(position);
        holder.nome.setText(note.getNome());
        holder.id.setText( Integer.toString(note.getId()));
        holder.nutente.setText(note.getNutente());
        holder.idade.setText(note.getIdade());


        holder.nome.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                showActionsDialog(position);
                return false;
            }
        });
    }

    public NotesAdapter(Context context, List<Data> notesList) {
        this.context = context;
        this.notesList = notesList;
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id;
        public TextView nome;
        public TextView nutente;
        public TextView idade;

        public MyViewHolder(View view) {
            super(view);
            id = view.findViewById(R.id.id);
            nome = view.findViewById(R.id.nome);
            nutente = view.findViewById(R.id.nutente);
            idade = view.findViewById(R.id.idade);

        }
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Editar", "Eliminar"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Escolha Opção");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    showDialog(position);
                } else {
                    deleteNote(position);
                }
            }
        });
        builder.show();
    }

    private void deleteNote(int position) {
        // deleting the note from db
        db = new DatabaseHelper(context);
        db.deleteNote(notesList.get(position));

        // removing the note from the list
        notesList.remove(position);
        mAdapter1.notifyItemRemoved(position);
    }


    public void showDialog(final int position){
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(context);
        View view = layoutInflaterAndroid.inflate(R.layout.activity_pacientes, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(context);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputNoteNome = view.findViewById(R.id.nomeUpdate);
        final EditText inputNoteNutente = view.findViewById(R.id.nutenteUpdate);
        final EditText inputNoteIdade = view.findViewById(R.id.idadeUpdate);

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        updateNote(inputNoteNome.getText().toString(),
                                inputNoteNutente.getText().toString(),
                                inputNoteIdade.getText().toString(),
                                position);
                    }
                })
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();
    }

    private void updateNote(String nome, String nutente, int position, String idade) {
        Data n = notesList.get(position);
        // updating note text
        n.setNome(nome);
        n.setNutente(nutente);
        n.setIdade(idade);
        db = new DatabaseHelper(context);
        // updating note in db
        db.updateNote(n);
        // refreshing the list
        notesList.set(position, n);
        mAdapter1.notifyItemChanged(position);
    }
}

