package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.adapter.PersonAdapter
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.controller.HistoryController
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityHistoryBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person

class HistoryActivity: AppCompatActivity() {

    private val personsList: MutableList<Person> = mutableListOf()

    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter(this, personsList)
    }
    private val historyController: HistoryController by lazy {
        HistoryController(this)
    }


    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fillInfoList()

        binding.lHistory.adapter = personAdapter

        binding.btAdicionar.setOnClickListener {
            val formIntent = Intent(this, FormActivity::class.java)
            startActivity(formIntent)
        }
    }



    private fun fillInfoList() {
        personsList.clear()
        Thread {
            personsList.addAll(historyController.getAllPersons())
            personAdapter.notifyDataSetChanged()
        }
    }
}