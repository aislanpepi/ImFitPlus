package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultFormBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultGastoBinding
import java.lang.Math.pow
import kotlin.math.pow

class ResultCaloricActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultGastoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultGastoBinding.inflate(layoutInflater)
        val view = binding.root
        val gastoFormat = DecimalFormat("####.##")

        val extras = intent.extras!!
        binding.tvNome.text = "Nome Completo: ${extras.getString("nome_completo")}"
        binding.tvGastoCalorico.text = "Gasto Calorico: ${gastoFormat.format(extras.getDouble("gasto_calorico"))}"

        setContentView(view)

        val pesoIdeal = 22 * (extras.getDouble("altura").pow(2.0))

        binding.btCalculoPesoIdeal.setOnClickListener{
            val calculoPeso = Intent(this, IdealWeightActivity::class.java)
            extras.putDouble("gasto_calorico",gastoFormat.format(extras.getDouble("gasto_calorico")).toDouble())
            extras.putDouble("peso_ideal",pesoIdeal)
            calculoPeso.putExtras(extras)
            startActivity(calculoPeso)
        }

        binding.btVoltar.setOnClickListener {
            finish()
        }
    }
}