package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model

import android.content.ContentValues
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.dao.PersonDao
import java.sql.SQLException

class PersonSqlite(context: Context): PersonDao {

    companion object {
        private const val IMFITPLUS_DATABASE_FILE = "imFitPlus"
        private const val PERSON_TABLE = "person"
        private const val ID_COLUMN = "id"
        private const val NAME_COLUMN = "name"
        private const val AGE_COLUMN = "age"
        private const val WEIGHT_COLUMN = "weight"
        private const val HEIGHT_COLUMN = "height"

        const val CREATE_PERSON_TABLE_STATEMENT = "CREATE TABLE IF NOT EXISTS $PERSON_TABLE (" +
                "$ID_COLUMN INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "$NAME_COLUMN TEXT NOT NULL, " +
                "$AGE_COLUMN INTEGER NOT NULL, " +
                "$WEIGHT_COLUMN REAL NOT NULL, " +
                "$HEIGHT_COLUMN REAL NOT NULL);"
    }

    private val imfitplusDatabase: SQLiteDatabase = context.openOrCreateDatabase(
        IMFITPLUS_DATABASE_FILE,
        MODE_PRIVATE,
        null
    )

    init {
        try {
            imfitplusDatabase.execSQL(CREATE_PERSON_TABLE_STATEMENT)
        }
        catch (se: SQLException) {
            Log.e("ImFitPlus", se.message.toString())
        }
    }

    override fun getAllPersons(): MutableList<Person> {
        val personList: MutableList<Person> = mutableListOf()
        val cursor = imfitplusDatabase.rawQuery("SELECT * FROM $PERSON_TABLE;", null)

        cursor.use {
            while (it.moveToNext()) {
                personList.add(it.toPerson())
            }
        }

        return personList
    }

    override fun getPersonById(id: Long): Person {
        val cursor = imfitplusDatabase.query(
            PERSON_TABLE,
            null,
            "$ID_COLUMN = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        return cursor.use {
            if (it.moveToFirst()) {
                it.toPerson()
            } else {
                Person()
            }
        }
    }

    override fun insertPerson(person: Person): Long {
        return imfitplusDatabase.insert(PERSON_TABLE, null, person.toContentValues())
    }

    override fun updatePerson(person: Person) {
        imfitplusDatabase.update(
            PERSON_TABLE,
            person.toContentValues(),
            "$ID_COLUMN = ?",
            arrayOf(person.id.toString())
        )
    }

    override fun deletePerson(person: Person) {
        imfitplusDatabase.delete(
            PERSON_TABLE,
            "$ID_COLUMN = ?",
            arrayOf(person.id.toString())
        )
    }

    private fun Person.toContentValues() = ContentValues().apply {
        put(NAME_COLUMN, name)
        put(AGE_COLUMN, age)
        put(WEIGHT_COLUMN, weight)
        put(HEIGHT_COLUMN, height)
    }

    private fun Cursor.toPerson() = Person(
        getLong(getColumnIndexOrThrow(ID_COLUMN)),
        getString(getColumnIndexOrThrow(NAME_COLUMN)),
        getInt(getColumnIndexOrThrow(AGE_COLUMN)),
        getDouble(getColumnIndexOrThrow(WEIGHT_COLUMN)),
        getDouble(getColumnIndexOrThrow(HEIGHT_COLUMN))
    )
}