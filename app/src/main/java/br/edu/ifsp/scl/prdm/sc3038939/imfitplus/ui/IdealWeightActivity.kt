package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultIdealWeightBinding

class IdealWeightActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultIdealWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultIdealWeightBinding.inflate(layoutInflater)
        val view = binding.root
        val extras = intent.extras!!
        val nome = extras.getString("nome_completo")
        val pesoIdeal = extras.getDouble("peso_ideal", 0.0)


        binding.tvNome.text = "Nome Completo: ${nome}"
        binding.tvPesoIdeal.text = "Peso Ideal: %.2f".format(pesoIdeal)
        extras.putDouble("peso_ideal",pesoIdeal)

        setContentView(view)

        binding.btResumo.setOnClickListener {
            val healthSummaryIntent = Intent(this,HealthSummaryActivity::class.java)
            healthSummaryIntent.putExtras(extras)
            startActivity(healthSummaryIntent)
        }

        binding.btVoltar.setOnClickListener {
            finish()
        }
    }
}