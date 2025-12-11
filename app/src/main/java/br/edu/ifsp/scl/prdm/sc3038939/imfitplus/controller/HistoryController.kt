package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.controller

import android.app.Activity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.PersonSqlite
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.dao.PersonDao
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui.FormActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui.HistoryActivity

class HistoryController(activity: Activity) {

    private val personDao: PersonDao = PersonSqlite(activity)

    fun getAllPersons(): MutableList<Person> = personDao.getAllPersons()


    fun getPersonById(id: Long) = personDao.getPersonById(id)

    fun insertPerson(person: Person) = personDao.insertPerson(person)


    fun updatePerson(person: Person) = personDao.updatePerson(person)

    fun deletePerson(person: Person) = personDao.deletePerson(person)
}