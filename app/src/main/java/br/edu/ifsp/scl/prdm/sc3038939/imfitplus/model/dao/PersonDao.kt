package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.dao

import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person

interface PersonDao {

    fun getAllPersons(): MutableList<Person>

    fun getPersonById(id:Long): Person?

    fun insertPerson(person: Person): Long

    fun updatePerson(person: Person)

    fun deletePerson(person: Person)
}