package br.com.dlweb.maternidade.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.dlweb.maternidade.bebe.Bebe;
import br.com.dlweb.maternidade.mae.Mae;
import br.com.dlweb.maternidade.medico.Medico;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "maternidade";
    private static final String TABLE_MAE = "mae";
    private static final String TABLE_MEDICO = "medico";
    private static final String TABLE_BEBE = "bebe";

    private static final String CREATE_TABLE_MAE = "CREATE TABLE " + TABLE_MAE + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "logradouro VARCHAR(200), " +
            "numero INTEGER, " +
            "bairro VARCHAR(50), " +
            "cidade VARCHAR(100), " +
            "cep VARCHAR(9), " +
            "fixo VARCHAR(14), " +
            "celular VARCHAR(15), " +
            "comercial VARCHAR(15), " +
            "data_nascimento DATE);";

    private static final String DROP_TABLE_MAE = "DROP TABLE IF EXISTS " + TABLE_MAE;

    private static final String CREATE_TABLE_MEDICO = "CREATE TABLE " + TABLE_MEDICO + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "crm INTEGER UNIQUE, " +
            "nome VARCHAR(100), " +
            "especialidade VARCHAR(200), " +
            "celular VARCHAR(15));";

    private static final String DROP_TABLE_MEDICO = "DROP TABLE IF EXISTS " + TABLE_MEDICO;

    private static final String CREATE_TABLE_BEBE = "CREATE TABLE " + TABLE_BEBE + " (" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "peso VARCHAR(15), " +
            "altura VARCHAR(15), " +
            "data_nascimento DATE);";

    private static final String DROP_TABLE_BEBE = "DROP TABLE IF EXISTS " + TABLE_BEBE;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MAE);
        db.execSQL(CREATE_TABLE_MEDICO);
        db.execSQL(CREATE_TABLE_BEBE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_MAE);
        db.execSQL(DROP_TABLE_MEDICO);
        db.execSQL(DROP_TABLE_BEBE);


        onCreate(db);
    }

    public void closeDBConnection() {
        db.close();
    }

    /* Início CRUD Mãe */
    public long createMae (Mae m) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("logradouro", m.getLogradouro());
        cv.put("numero", m.getNumero());
        cv.put("bairro", m.getBairro());
        cv.put("cidade", m.getCidade());
        cv.put("cep", m.getCep());
        cv.put("fixo", m.getFixo());
        cv.put("celular", m.getCelular());
        cv.put("comercial", m.getComercial());
        cv.put("data_nascimento", m.getData_nascimento());
        long id = db.insert(TABLE_MAE, null, cv);
        db.close();
        return id;
    }
    public long createMedico (Medico m) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("especialidade", m.getEspecialidade());
        cv.put("celular", m.getCelular());
        long id = db.insert(TABLE_MEDICO, null, cv);
        db.close();
        return id;
    }
    public long createBebe (Bebe m) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("data_nascimento", m.getData_nascimento());
        cv.put("peso", m.getPeso());
        cv.put("altura", m.getAltura());
        long id = db.insert(TABLE_BEBE, null, cv);
        db.close();
        return id;
    }
    public long updateMae (Mae m) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("logradouro", m.getLogradouro());
        cv.put("numero", m.getNumero());
        cv.put("bairro", m.getBairro());
        cv.put("cidade", m.getCidade());
        cv.put("cep", m.getCep());
        cv.put("fixo", m.getFixo());
        cv.put("celular", m.getCelular());
        cv.put("comercial", m.getComercial());
        cv.put("data_nascimento", m.getData_nascimento());
        long id = db.update(TABLE_MAE, cv, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }
    public long updateMedico (Medico m) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("especialidade", m.getEspecialidade());
        cv.put("celular", m.getCelular());
        long id = db.update(TABLE_MEDICO, cv, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }
    public long updateBebe (Bebe m) {
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", m.getNome());
        cv.put("data_nascimento", m.getData_nascimento());
        cv.put("peso", m.getPeso());
        cv.put("altura", m.getAltura());
        long id = db.update(TABLE_BEBE, cv, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public long deleteMae (Mae m) {
        db = this.getWritableDatabase();
        long id = db.delete(TABLE_MAE, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }
    public long deleteMedico (Medico m) {
        db = this.getWritableDatabase();
        long id = db.delete(TABLE_MEDICO, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }
    public long deleteBebe (Bebe m) {
        db = this.getWritableDatabase();
        long id = db.delete(TABLE_BEBE, "_id = ?", new String[]{String.valueOf(m.getId())});
        db.close();
        return id;
    }

    public Mae getByIdMae (int id) {
        db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "logradouro", "numero", "bairro", "cidade", "cep", "fixo", "celular", "comercial", "data_nascimento"};
        Cursor data = db.query(TABLE_MAE, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        Mae m = new Mae();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setLogradouro(data.getString(2));
        m.setNumero(data.getInt(3));
        m.setBairro(data.getString(4));
        m.setCidade(data.getString(5));
        m.setCep(data.getString(6));
        m.setFixo(data.getString(7));
        m.setCelular(data.getString(8));
        m.setComercial(data.getString(9));
        m.setData_nascimento(data.getString(10));
        data.close();
        db.close();
        return m;
    }
    public Medico getByIdMedico (int id) {
        db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "especialidade","celular"};
        Cursor data = db.query(TABLE_MEDICO, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        Medico m = new Medico();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setEspecialidade(data.getString(2));
        m.setCelular(data.getString(3));

        data.close();
        db.close();
        return m;
    }
    public Bebe getByIdBebe (int id) {
        db = this.getReadableDatabase();
        String[] columns = {"_id","nome", "data_nascimento", "peso","altura"};
        Cursor data = db.query(TABLE_BEBE, columns, "_id = ?", new String[]{String.valueOf(id)}, null, null, null);
        data.moveToFirst();
        Bebe m = new Bebe();
        m.setId(data.getInt(0));
        m.setNome(data.getString(1));
        m.setData_nascimento(data.getString(2));
        m.setPeso(data.getString(3));
        m.setAltura(data.getString(4));

        data.close();
        db.close();
        return m;
    }

    public Cursor getAllMae () {
        db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "celular"};
        return db.query(TABLE_MAE, columns, null, null, null, null, null);
    }
    public Cursor getAllMedico () {
        db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "celular"};
        return db.query(TABLE_MEDICO, columns, null, null, null, null, null);
    }
    public Cursor getAllBebe () {
        db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "Data_nascimento"};
        return db.query(TABLE_BEBE, columns, null, null, null, null, null);
    }
    /* Fim CRUD Mãe */

}

