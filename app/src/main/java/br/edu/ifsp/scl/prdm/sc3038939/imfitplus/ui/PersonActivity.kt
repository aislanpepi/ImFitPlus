package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityPersonBinding

class PersonActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras!!

        val nome = extras.getString("nome_completo")
        val peso = extras.getDouble("peso")
        val imc = extras.getDouble("imc")
        val catImc = extras.getString("categoria_imc")
        val pesoIdeal = extras.getDouble("peso_ideal")
        val gastoCalorico = extras.getDouble("gasto_calorico")

        val format = DecimalFormat("##.##")

        // Recomendação de ingestão de água
        val ingestaoAgua = (peso * 350.0) / 1000.0

        // ---- PREENCHIMENTO EXATO QUE O XML POSSUI ----
        binding.tvNome.text = "Nome: $nome"
        binding.tvImc.text = "IMC: ${format.format(imc)}"
        binding.tvImcCategoria.text = "Categoria IMC: $catImc"
        binding.tvPesoIdeal.text = "Peso Ideal: ${format.format(pesoIdeal)}"
        binding.tvGastoCalorico.text = "Gasto Calórico Diário: ${format.format(gastoCalorico)}"
        binding.tvRecAgua.text = "Ingestão de Água Recomendada: ${format.format(ingestaoAgua)}L"

        // Botão de retorno
        binding.btRetornar.setOnClickListener {
            val historyIntent = Intent(this, HistoryActivity::class.java)
            startActivity(historyIntent)
        }
    }
}
