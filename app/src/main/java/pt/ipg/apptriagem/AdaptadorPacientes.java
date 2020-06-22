package pt.ipg.apptriagem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorPacientes extends RecyclerView.Adapter<AdaptadorPacientes.ViewHolder> {

    private List<Pacientes> pacientes;
    ItemClicked activity;

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public AdaptadorPacientes (Context context, List<Pacientes> list){
        pacientes = list;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewNomePaciente, textViewUtente, textViewLetra;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(pacientes.indexOf((Pacientes)v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public AdaptadorPacientes.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_pacientes, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPacientes.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(pacientes.get(i));

        viewHolder.textViewLetra.setText(pacientes.get(i).getNome().charAt(0) + " ");
        viewHolder.textViewNomePaciente.setText(pacientes.get(i).getNome());
        viewHolder.textViewUtente.setText(pacientes.get(i).getNutente() + " ( " + pacientes.get(i).getIdade() + " ");
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }
}
