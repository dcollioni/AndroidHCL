package com.example.douglas.livros.db;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import com.example.douglas.livros.Livro;

import java.util.ArrayList;

/**
 * Created by Douglas on 4/8/2015.
 */
public class LivroDao {

    private Db4oHelper db4o;

    public LivroDao(Db4oHelper db4o) {
        this.db4o = db4o;
    }

    private ObjectContainer db() {
        return db4o.db();
    }

    public void inserir(Livro l) {
        db().store(l);
    }

    public void atualizar(Livro l, long id) {
        Livro lAntigo = (Livro) db().ext().getByID(id);

        lAntigo.setTitulo(l.getTitulo());
        lAntigo.setAutor(l.getAutor());
        lAntigo.setGenero(l.getGenero());
        lAntigo.setIsbn(l.getIsbn());

        db().store(lAntigo);
    }

    public void excluir(Livro l) {
        db().delete(l);
    }

    public Livro buscar(long id) {
        Livro l = db().ext().getByID(id);
        db().ext().activate(l);

        return l;
    }

    public ArrayList<Livro> listar(final String filtro) {

        ObjectSet<Livro> resultados;

        if (filtro.isEmpty()) {

            resultados = db().query(Livro.class);

        } else {

            resultados = db().query(
                    new Predicate<Livro>() {
                        public boolean match(Livro l) {
                            return l.getTitulo().toLowerCase().contains(filtro.toLowerCase());
                        }
                    });
        }

        return new ArrayList<>(resultados);
    }

    public long buscarId(Livro l) {
        return db().ext().getID(l);
    }
}
