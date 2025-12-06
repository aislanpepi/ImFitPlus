package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.controller

import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.repository.PersonRepository
import kotlinx.coroutines.flow.Flow

class MainController(private val personRepository: PersonRepository) {

    fun getAllPersons(): Flow<List<Person>> {
        return personRepository.getAllPersons()
    }

    suspend fun getPersonById(id: Long): Person? {
        return personRepository.getPersonById(id)
    }

    suspend fun insertPerson(person: Person): Long {
        return personRepository.insertPerson(person)
    }

    suspend fun updatePerson(person: Person) {
        personRepository.updatePerson(person)
    }

    suspend fun deletePerson(person: Person) {
        personRepository.deletePerson(person)
    }
}