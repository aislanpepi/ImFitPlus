package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultFormBinding

class ResultFormActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        val extras = intent.extras!!
        val nome = extras.getString("nome_completo")
        val imc = extras.getDouble("imc")
        val sexo = extras.getString("sexo")
        val peso = extras.getDouble("peso")
        val altura = extras.getDouble("altura")
        val idade = extras.getInt("idade")
        val categoria = imcCategory(imc)

        super.onCreate(savedInstanceState)
        binding = ActivityResultFormBinding.inflate(layoutInflater)
        val view = binding.root

        binding.tvNome.text = "Nome Completo: ${nome}"
        binding.tvImc.text = "IMC: %.2f".format(imc)
        binding.tvCategoriaImc.text = "Categoria IMC: ${categoria}"
        setContentView(view)

        binding.btCalculoGastoCalorico.setOnClickListener {

            var gastoCalorico = 0.0

            if(sexo == "Masculino")
                gastoCalorico = 66 + (13.7 * peso) + (5 * altura * 100) - (6.8 * idade)
            if(sexo == "Feminino")
                gastoCalorico = 655 + (9.6 * peso) + (1.8 * altura * 100) - (4.7 * idade)

            val resulCaloricIntent = Intent(this, ResultCaloricActivity::class.java)
            extras.putString("categoria_imc",categoria)
            extras.putDouble("gasto_calorico",gastoCalorico)
            extras.putDouble("imc",imc)
            resulCaloricIntent.putExtras(extras)
            startActivity(resulCaloricIntent)
        }

        binding.btVoltar.setOnClickListener {
            finish()
        }
    }

    fun imcCategory(imc: Double): String {
        if(imc >= 30) return "Obesidade"
        if(imc >= 25) return "Sobrepeso"
        if(imc >= 18.5) return "Normal"
        return "Abaixo do peso"
    }
}