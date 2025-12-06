package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.repository

import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.dao.PersonDao
import kotlinx.coroutines.flow.Flow

class PersonRepositoryImpl(private val personDao: PersonDao): PersonRepository {

    override fun getAllPersons(): Flow<List<Person>> {
        return personDao.getAllPersons()
    }

    override suspend fun getPersonById(id: Long): Person? {
        return personDao.getPersonById(id)
    }

    override suspend fun insertPerson(person: Person): Long {
        return personDao.insertPerson(person)
    }

    override suspend fun updatePerson(person: Person) {
        personDao.updatePerson(person)
    }

    override suspend fun deletePerson(person: Person) {
        personDao.deletePerson(person)
    }
}