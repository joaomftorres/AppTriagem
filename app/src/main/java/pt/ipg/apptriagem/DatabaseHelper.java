package pt.ipg.apptriagem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "covid.db";
    private static final String TABLE_NAME = "Pacientes_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "NOME";
    private static final String COL_3 = "NUMEROUTENTE";
    private static final String COL_4 = "IDADE";
    private static final String COL_5 = "SINTOMAS";
    private static final String COL_6 = "DATA";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Pacientes_table = "create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, NUMEROUTENTE TEXT, IDADE INTEGER, SINTOMAS TEXT, DATA TEXT)";
        db.execSQL(Pacientes_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nome, String nutente, String idade, String sintomas, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, nome);
        contentValues.put(COL_3, nutente);
        contentValues.put(COL_4, idade);
        contentValues.put(COL_5, sintomas);
        contentValues.put(COL_6, data);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == 1) {
            return false;
        } else {
            return true;
        }
    }


    /*public boolean insertDataSintoma(String sintomas){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(SINTOMAS, sintomas);

        long result = db.insert(TABLE_NAME2, null, contentValues1);

        if(result==1){
            return false;
        }else{
            return true;
        }

    }*/

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAME , null);
        return res;
    }

    /*public Cursor getDataSintomas(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor sint = db.rawQuery("select * from "+ TABLE_NAME2, null);
        return sint;
    }*/




    public boolean updateData(String id, String nome, String nutente, String idade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, nome);
        contentValues.put(COL_3, nutente);
        contentValues.put(COL_4, idade);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[] { id });
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }
}

