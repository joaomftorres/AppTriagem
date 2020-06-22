package pt.ipg.apptriagem;

public class Data {
    int id;
    String nome;
    String nutente;
    String idade;

    public Data(){

    }
    //Create table (query)

    public static final String CREATE_TABLE=
            "CREATE TABLE " + "Pacientes" + "("+
                    "Id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "Nome" + " TexT, " +
                    "Numero de Utente" + " TexT "+
                    "Idade" + " TexT "+ ")";

    //    public static  String CREATE_TABLE = "CREATE TABLE " + "New" + "("
//            + "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//            + "email" + " TEXT,"
//            + "pass" + " TEXT,"
//            +  ")";
    public Data(int id, String nome, String nutente, String idade) {
        this.id = id;
        this.nome = nome;
        this.nutente = nutente;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNutente() {
        return nutente;
    }

    public void setNutente(String nutente) {
        this.nutente = nutente;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }
}