package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import Health
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.controller.MainController
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityHealthSummaryBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person

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
        val tmb = extras.getDouble("tmb")

        val ingestaoAgua = (peso * 350.0) / 1000.0

        binding.tvNome.text = "Nome: ${nome}"
        binding.tvImc.text = "IMC: %.2f".format(imc)
        binding.tvImcCategoria.text = "Categoria IMC: ${catImc}"
        binding.tvPesoIdeal.text = "Peso Ideal: %.2f Kg".format(pesoIdeal)
        binding.tvGastoCalorico.text = "Taxa Metabolica Basal: %.2f kcal".format(tmb)
        binding.tvRecAgua.text = "Ingestão de Agua Recomendada: %.2f L".format(ingestaoAgua)

        val view = binding.root
        setContentView(view)

        binding.btRetornar.setOnClickListener {
            val historyIntent = Intent(this, HistoryActivity::class.java)
            startActivity(historyIntent)
        }

        binding.btSalvar.setOnClickListener {
            val health = Health(imc, catImc.toString(), pesoIdeal, tmb, ingestaoAgua)

            val person = Person(
                name= nome.toString(),
                age=idade,
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