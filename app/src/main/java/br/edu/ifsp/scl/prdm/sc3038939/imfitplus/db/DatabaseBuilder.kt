package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.db

import android.content.Context
import androidx.room.Room

object DatabaseBuilder {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "imfitplus"
            ).build()

            INSTANCE = instance
            instance
        }
    }
}
