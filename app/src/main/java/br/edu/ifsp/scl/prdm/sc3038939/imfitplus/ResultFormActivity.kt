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

        super.onCreate(savedInstanceState)
        binding = ActivityResultFormBinding.inflate(layoutInflater)
        val view = binding.root
        binding.tvNome.text = nome
        binding.tvImc.text = imcFormat.format(imc)
        binding.tvCategoriaImc.text = getImcCategory(imcFormat.format(imc))
        setContentView(view)

        binding.btCalculoGastoCalorico.setOnClickListener {
            val resulCaloricIntent = Intent(this, ResultCaloricActivity::class.java)
            resulCaloricIntent.putExtras(extras)
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