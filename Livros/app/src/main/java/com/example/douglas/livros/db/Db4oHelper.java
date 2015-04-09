package com.example.douglas.livros.db;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * Created by Douglas on 4/8/2015.
 */
public class Db4oHelper {

    private final String DB4O_FILE = "bd.db4o";

    private String dir;
    private ObjectContainer db;

    public Db4oHelper(String dir) {
        this.dir = dir;
    }

    public ObjectContainer db() {
        return db;
    }

    public void abrirConexao() {
        String dbFile = dir + DB4O_FILE;
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), dbFile);
    }

    public void fecharConexao() {
        if (db != null) {
            db.close();
        }
    }
}
