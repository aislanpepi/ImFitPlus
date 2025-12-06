package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.repository

import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getAllPersons(): Flow<List<Person>>

    suspend fun getPersonById(id:Long): Person?

    suspend fun insertPerson(person: Person): Long

    suspend fun updatePerson(person: Person)

    suspend fun deletePerson(person: Person)
}