package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultFormBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultGastoBinding

class ResultCaloricActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultGastoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultGastoBinding.inflate(layoutInflater)
        val view = binding.root

        val extras = intent.extras!!
        binding.tvNome.text = getString(R.string.nome_completo_variable,extras.getString("nome_completo"))
        binding.tvGastoCalorico.text = getString(R.string.gasto_calorico_ideal,extras.getDouble("gasto_calorico"))


        setContentView(view)
    }
}