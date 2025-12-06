package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM PERSONS")
    fun getAllPersons(): Flow<List<Person>>

    @Query("SELECT * FROM Persons WHERE id = :id")
    fun getPersonById(id:Long): Person?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPerson(person: Person): Long

    @Update
    fun updatePerson(person: Person)

    @Delete
    fun deletePerson(person: Person)
}