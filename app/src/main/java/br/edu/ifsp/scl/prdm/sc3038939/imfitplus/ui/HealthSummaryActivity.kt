package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import Health
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.controller.MainController
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityHealthSummaryBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import java.time.LocalDate

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
        val dataNasc = LocalDate.parse(extras.getString("dataNasc"))
        val sexo = extras.getString("sexo")
        val peso = extras.getDouble("peso")
        val idade = extras.getInt("idade")
        val imc = extras.getDouble("imc")
        val catImc = extras.getString("categoria_imc")
        val pesoIdeal = extras.getDouble("peso_ideal")
        val tmb = extras.getDouble("tmb")

        val fcMax = 220 - idade

        val ingestaoAgua = (peso * 350.0) / 1000.0

        binding.tvNome.text = "Nome: ${nome}"
        binding.tvImc.text = "IMC: %.2f".format(imc)
        binding.tvImcCategoria.text = "Categoria IMC: ${catImc}"
        binding.tvPesoIdeal.text = "Peso Ideal: %.2f Kg".format(pesoIdeal)
        binding.tvGastoCalorico.text = "Taxa Metabolica Basal: %.2f kcal".format(tmb)
        binding.tvRecAgua.text = "Ingestão de Agua Recomendada: %.1f L".format(ingestaoAgua)
        binding.tvFcmax.text = "Frequencia cardiaca maxima: %d BPM".format(fcMax)
        binding.tvLeve.text = "Zona Leve: %.1f BPM".format(fcMax * 0.5) + " - %.1f BPM".format(fcMax * 0.6)
        binding.tvQueima.text = "Zona Queima de Gordura: %.1f BPM".format(fcMax * 0.6) + " - %.1f BPM".format(fcMax * 0.7)
        binding.tvAerobica.text = "Zona aeróbica: %.1f BPM".format(fcMax * 0.7) + " - %.1f BPM".format(fcMax * 0.8)
        binding.tvAnaerobica.text = "Zona anaeróbica: %.1f BPM".format(fcMax * 0.8) + " - %.1f BPM".format(fcMax * 0.9)

        val view = binding.root
        setContentView(view)

        binding.btRetornar.setOnClickListener {
            val historyIntent = Intent(this, HistoryActivity::class.java)
            startActivity(historyIntent)
        }

        binding.btSalvar.setOnClickListener {
            val health = Health(imc, catImc.toString(), pesoIdeal, tmb, ingestaoAgua, fcMax)

            val person = Person(
                name= nome.toString(),
                dataNasc = dataNasc,
                weight=peso,
                height=altura,
                gender= sexo.toString(),
                health=health)

            mainController.insertPerson(person)
            val historyIntent = Intent(this, HistoryActivity::class.java)
            startActivity(historyIntent)
        }
    }
}