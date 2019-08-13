package com.br.afs.mqrcode.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.br.afs.mqrcode.database.dao.CartaDao;
import com.br.afs.mqrcode.model.Carta;

@Database(entities = {Carta.class}, version = 1, exportSchema = false)
public abstract class MQRCodeDatabase extends RoomDatabase {
private static final String NOME_DO_BANCO_DE_DADOS = "cartas.db";
public abstract CartaDao getCartaDao();

public static MQRCodeDatabase getInstance(Context context) {
    return Room
            .databaseBuilder(context, MQRCodeDatabase.class, NOME_DO_BANCO_DE_DADOS)
            .allowMainThreadQueries()
            .build();
}
}
