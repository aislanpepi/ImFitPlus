package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultFormBinding

class ResultFormActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        val extras = intent.extras!!
        val imcFormat = DecimalFormat("##.##")
        val nome = extras.getString("nome_completo")
        val imc = extras.getDouble("imc")
        val sexo = extras.getString("sexo")
        val peso = extras.getDouble("peso")
        val altura = extras.getDouble("altura")
        val idade = extras.getInt("idade")

        super.onCreate(savedInstanceState)
        binding = ActivityResultFormBinding.inflate(layoutInflater)
        val view = binding.root
        binding.tvNome.text = "Nome Completo: ${extras.getString("nome_completo")}"
        binding.tvImc.text = "IMC: ${imcFormat.format(imc)}"
        binding.tvCategoriaImc.text = "Categoria IMC: ${getImcCategory(imcFormat.format(imc))}"
        setContentView(view)

        binding.btCalculoGastoCalorico.setOnClickListener {

            var gastoCalorico = 0.0
            if(sexo == "Masculino") gastoCalorico = 66 + (13.7 * peso) + (5 * altura * 100) - (6.8 * idade)
            else gastoCalorico = 655 + (9.6 * peso) + (1.8 * altura * 100) - (4.7 * idade)

            val resulCaloricIntent = Intent(this, ResultCaloricActivity::class.java)
            extras.putDouble("gasto_calorico",gastoCalorico)
            resulCaloricIntent.putExtras(extras)
            startActivity(resulCaloricIntent)
        }

        binding.btVoltar.setOnClickListener {
            finish()
        }
    }

    fun getImcCategory(imc: String): String {
        val imcValue = imc.toDouble()

        if(imcValue >= 30) return "Obesidade"
        if(imcValue >= 25) return "Sobrepeso"
        if(imcValue >= 18.5) return "Normal"
        return "Abaixo do peso"
    }
}