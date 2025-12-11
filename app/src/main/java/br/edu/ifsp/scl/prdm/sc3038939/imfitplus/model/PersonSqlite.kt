package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model

import Health
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
        private const val GENDER_COLUMN = "gender"
        private const val WEIGHT_COLUMN = "weight"
        private const val HEIGHT_COLUMN = "height"

        // ✅ NOVAS CONSTANTES
        private const val IMC_COLUMN = "imc"
        private const val CATEGORIA_COLUMN = "categoria"
        private const val PESOIDEAL_COLUMN = "pesoIdeal"
        private const val GASTOCALORICO_COLUMN = "gastoCalorico"
        private const val RECAGUA_COLUMN = "recAgua"

        const val CREATE_PERSON_TABLE_STATEMENT = """
            CREATE TABLE IF NOT EXISTS $PERSON_TABLE (
                $ID_COLUMN INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
                $NAME_COLUMN TEXT NOT NULL,
                $AGE_COLUMN INTEGER NOT NULL,
                $WEIGHT_COLUMN REAL NOT NULL,
                $HEIGHT_COLUMN REAL NOT NULL,
                $GENDER_COLUMN TEXT NOT NULL,
                $IMC_COLUMN REAL NOT NULL,
                $CATEGORIA_COLUMN TEXT NOT NULL,
                $PESOIDEAL_COLUMN REAL NOT NULL,
                $GASTOCALORICO_COLUMN REAL NOT NULL,
                $RECAGUA_COLUMN REAL NOT NULL
            );
        """
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
                Person(
                    id = 0,
                    name = "",
                    age = 0,
                    weight = 0.0,
                    height = 0.0,
                    gender = "",
                    health = Health(0.0, "", 0.0, 0.0, 0.0)
                )
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
        put(GENDER_COLUMN, gender)

        put(IMC_COLUMN, health.imc)
        put(CATEGORIA_COLUMN, health.categoria)
        put(PESOIDEAL_COLUMN, health.pesoIdeal)
        put(GASTOCALORICO_COLUMN, health.gastoCalorico)
        put(RECAGUA_COLUMN, health.recAgua)
    }

    private fun Cursor.toPerson() = Person(
        id = getLong(getColumnIndexOrThrow(ID_COLUMN)),
        name = getString(getColumnIndexOrThrow(NAME_COLUMN)),
        age = getInt(getColumnIndexOrThrow(AGE_COLUMN)),
        weight = getDouble(getColumnIndexOrThrow(WEIGHT_COLUMN)),
        height = getDouble(getColumnIndexOrThrow(HEIGHT_COLUMN)),
        gender = getString(getColumnIndexOrThrow(GENDER_COLUMN)),
        health = Health(
            imc = getDouble(getColumnIndexOrThrow(IMC_COLUMN)),
            categoria = getString(getColumnIndexOrThrow(CATEGORIA_COLUMN)),
            pesoIdeal = getDouble(getColumnIndexOrThrow(PESOIDEAL_COLUMN)),
            gastoCalorico = getDouble(getColumnIndexOrThrow(GASTOCALORICO_COLUMN)),
            recAgua = getDouble(getColumnIndexOrThrow(RECAGUA_COLUMN))
        )
    )
}
