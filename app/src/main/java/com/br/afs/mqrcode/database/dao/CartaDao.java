package com.br.afs.mqrcode.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.br.afs.mqrcode.model.Carta;

import java.util.List;

@Dao
public interface CartaDao {
    @Insert
    void salva(Carta c);

@Query("SELECT * FROM carta")
List<Carta> todas();

@Query("SELECT * from carta WHERE nome = :nome")
Carta porNome(String nome);

@Delete
    void remove(Carta c);

@Update
    void edita(Carta c);
}
