package br.ufrpe.artemis.Endereco.Dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.ufrpe.artemis.Endereco.Dominio.Endereco;
import br.ufrpe.artemis.Infra.ArtemisApp;
import br.ufrpe.artemis.Infra.DataBase.Dao.DB;

public class EnderecoDao {
    private SQLiteDatabase banco;

    public EnderecoDao(){
        habilitarBanco();
    }

    private SQLiteDatabase habilitarBanco(){
        DB auxDB = new DB(ArtemisApp.getContext());
        banco = auxDB.getWritableDatabase();
        return banco;
    }

    public void inserirEndereço(Endereco endereco){
        ContentValues values = new ContentValues();
        values.put("cep", endereco.getCep());
        values.put("rua", endereco.getRua());
        values.put("numero", endereco.getNumero());
        values.put("cidade", endereco.getCidade());
        banco.insert("endereco", null, values);
        banco.close();
    }

    public Endereco recuperarEndereco(int id){
        Cursor cursor = banco.query("endereco", new String[]{"*"}, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        cursor.moveToFirst();
        Endereco endereco = new Endereco();
        endereco.setId(cursor.getInt(0));
        endereco.setCep(cursor.getString(1));
        endereco.setRua(cursor.getString(2));
        endereco.setNumero(cursor.getString(3));
        endereco.setCidade(cursor.getString(4));
        banco.close();
        cursor.close();
        return endereco;
    }

    public void alterarEndereco(Endereco endereco){
        ContentValues values = new ContentValues();
        values.put("rua", endereco.getRua());
        values.put("numero", endereco.getNumero());
        values.put("cidade", endereco.getCidade());
        values.put("cep", endereco.getCep());
        banco.update("endereco", values, "id = ?", new String[]{String.valueOf(endereco.getId())});
        banco.close();
    }
}
