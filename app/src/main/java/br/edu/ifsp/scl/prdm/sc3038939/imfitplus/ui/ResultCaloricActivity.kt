package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultGastoBinding
import kotlin.math.pow

class ResultCaloricActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultGastoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultGastoBinding.inflate(layoutInflater)
        val view = binding.root
        val extras = intent.extras!!

        val nome = extras.getString("nome_completo")
        val gastoCalorico = extras.getDouble("gasto_calorico")

        binding.tvNome.text = "Nome Completo: ${nome}"
        binding.tvGastoCalorico.text = "Gasto Calorico: %.2f".format(gastoCalorico)

        setContentView(view)

        val pesoIdeal = 22 * (extras.getDouble("altura").pow(2.0))

        binding.btCalculoPesoIdeal.setOnClickListener{
            val calculoPeso = Intent(this, IdealWeightActivity::class.java)
            extras.putDouble("gasto_calorico",gastoCalorico)
            extras.putDouble("peso_ideal",pesoIdeal)
            calculoPeso.putExtras(extras)
            startActivity(calculoPeso)
        }

        binding.btVoltar.setOnClickListener {
            finish()
        }
    }
}