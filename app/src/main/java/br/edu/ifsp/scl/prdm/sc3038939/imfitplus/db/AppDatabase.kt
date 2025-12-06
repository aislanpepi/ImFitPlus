package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.dao.PersonDao

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun personDao(): PersonDao

    val db = Room.databaseBuilder(
        applicationContext,
        AppDatabase::class.java, "imfitplus"
    ).build()
}