package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import Health
import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.controller.MainController
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityHealthSummaryBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.PersonSqlite

class HealthSummaryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHealthSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthSummaryBinding.inflate(layoutInflater)

        val mainController: MainController by lazy {
            MainController(this)
        }

        val extras = intent.extras!!

        val nome = extras.getString("nome_completo")
        val altura = extras.getDouble("altura")
        val idade = extras.getInt("idade")
        val sexo = extras.getString("sexo")
        val peso = extras.getDouble("peso")
        val imc = extras.getDouble("imc")
        val catImc = extras.getString("categoria_imc")
        val pesoIdeal = extras.getDouble("peso_ideal")
        val gastoCalorico = extras.getDouble("gasto_calorico")
        val format = DecimalFormat("##.##")

        val ingestaoAgua = (peso * 350.0) / 1000.0

        binding.tvNome.text = "Nome: ${nome}"
        binding.tvImc.text = "IMC: ${imc}"
        binding.tvImcCategoria.text = "Categoria IMC: ${catImc}"
        binding.tvPesoIdeal.text = "Peso Ideal: ${pesoIdeal}"
        binding.tvGastoCalorico.text = "Gasto Calorico Diario: ${gastoCalorico}"
        binding.tvRecAgua.text = "Ingestão de Agua Recomendada: ${format.format(ingestaoAgua)}L"

        val view = binding.root
        setContentView(view)

        binding.btRetornar.setOnClickListener {
            val historyIntent = Intent(this, HistoryActivity::class.java)
            startActivity(historyIntent)
        }

        binding.btSalvar.setOnClickListener {
            val health = Health(imc, catImc.toString(), pesoIdeal, gastoCalorico, ingestaoAgua)

            val person = Person(name= nome.toString(),age=idade,weight=peso, height=altura, gender= sexo.toString(), health=health)

            mainController.insertPerson(person)
            val historyIntent = Intent(this, HistoryActivity::class.java)
            startActivity(historyIntent)
        }
    }
}