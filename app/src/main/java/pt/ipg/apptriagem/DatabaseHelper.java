package pt.ipg.apptriagem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 99;
    private static final String DATABASE_NAME = "covid";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataPaciente.CREATE_TABLE);
        //db.execSQL(DataSintomas.CREATE_TABLE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertDataPaciente(String nome,String nutente, String idade){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Nome",nome);
        values.put("Numero de Utente",nutente);
        values.put("Idade",idade);
        long id=db.insert("Pacientes",null,values);
        db.close();
        return id;
    }

    public List<Data> getAllDataFromDb(){
        List<Data> notes = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + "Pacientes" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Data note = new Data();
                note.setId(cursor.getInt(cursor.getColumnIndex("Id")));
                note.setNome(cursor.getString(cursor.getColumnIndex("Nome")));
                note.setNutente(cursor.getString(cursor.getColumnIndex("Numero de Utente")));
                note.setIdade(cursor.getString(cursor.getColumnIndex("Idade")));
                notes.add(note);
            } while (cursor.moveToNext());
        }
        db.close();
        return notes;
    }

    /*public int updateNote(Data note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Password", note.getPass());

        // updating row
        return db.update("HelloNew", values, "Id" + " = ?",
                new String[]{String.valueOf(note.getId())});
    }*/


    /*public void deleteNote(Data note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("HelloNew", "Id" + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }*/
}