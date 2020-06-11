package pt.ipg.apptriagem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseTriagem {

    public static final String KEY_NOME = "nome";
    public static final String KEY_NUMEROUTENTE = "numero_utente";
    public static final String KEY_IDADE = "idade";

    private final String DATABASE_NAME = "DatabaseTriagem";
    private final String DATABASE_TABLE = "TabelaPacientes";
    private final int DATABASE_VERSION = 1;

    private DBHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public DatabaseTriagem (Context context)
    {
        ourContext = context;
    }

    private class DBHelper extends SQLiteOpenHelper
    {
        public DBHelper (Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            /*
            CREATE TABLE TabelaPacientes (nome TEXT,
                    numero_utente INTEGER PRIMARY KEY,
                    idade INTEGER);
             */

            String sqlCode= "CREATE TABLE " + DATABASE_TABLE + " (" +
                    KEY_NOME + " TEXT NOT NULL, " +
                    KEY_NUMEROUTENTE + " INTEGER PRIMARY KEY, " +
                    KEY_IDADE + " INTEGER);";

            db.execSQL(sqlCode);
        }
    }

    public DatabaseTriagem open() throws SQLException
    {
        ourHelper = new DBHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        ourHelper.close();;
    }

    public long createEntry(String nome, String numero_utente, String idade)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NOME, nome);
        cv.put(KEY_NUMEROUTENTE, numero_utente);
        cv.put(KEY_IDADE, idade);
        return ourDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public String getData()
    {
        String [] columns = new String [] {KEY_NOME, KEY_NUMEROUTENTE, KEY_IDADE};

        Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null,null, null);

        String result = "";

        int iNome = c.getColumnIndex(KEY_NOME);
        int iNumeroUtente = c.getColumnIndex(KEY_NUMEROUTENTE);
        int iIdade = c.getColumnIndex(KEY_IDADE);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getString(iNome) + ": " + c.getString(iNumeroUtente) + " " +c.getString(iIdade) + "\n";
        }

        c.close();
        return result;
    }

    public long deleteEntry(String NumeroUtente)
    {
        return ourDatabase.delete(DATABASE_TABLE, KEY_NUMEROUTENTE + "=?", new String[]{NumeroUtente});
    }

    public long updateEntry(String Nome, String NumeroUtente, String Idade)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NOME, Nome);
        cv.put(KEY_NUMEROUTENTE, NumeroUtente);
        cv.put(KEY_IDADE, Idade);

        return ourDatabase.update(DATABASE_TABLE, cv, KEY_NUMEROUTENTE + "=?", new String[]{NumeroUtente});
    }
}
