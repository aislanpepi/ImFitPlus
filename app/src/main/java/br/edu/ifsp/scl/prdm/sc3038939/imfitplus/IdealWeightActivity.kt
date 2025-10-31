package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultFormBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultGastoBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultIdealWeightBinding

class IdealWeightActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultIdealWeightBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultIdealWeightBinding.inflate(layoutInflater)

        val weightFormat = DecimalFormat("###.##")
        val view = binding.root
        val extras = intent.extras!!


        binding.tvNome.text = "Nome Completo: ${extras.getString("nome_completo")}"
        binding.tvPesoIdeal.text = "Peso Ideal: ${weightFormat.format(extras.getDouble("peso_ideal", 0.0))}"
        extras.putDouble("peso_ideal",weightFormat.format(extras.getDouble("peso_ideal", 0.0)).toDouble())

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