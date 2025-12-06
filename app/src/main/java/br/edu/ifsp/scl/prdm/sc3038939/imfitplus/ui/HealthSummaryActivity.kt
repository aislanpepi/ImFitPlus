package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityHealthSummaryBinding

class HealthSummaryActivity: AppCompatActivity() {
    private lateinit var binding: ActivityHealthSummaryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHealthSummaryBinding.inflate(layoutInflater)

        val extras = intent.extras!!

        val nome = extras.getString("nome_completo")
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
    }
}