package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

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
        val view = binding.root
        val extras = intent.extras!!
        binding.tvNome.text = getString(R.string.nome_completo_variable,extras.getString("nome_completo"))
        binding.tvPesoIdeal.text = extras.getDouble("peso_ideal",0.0).toString()
        setContentView(view)
    }
}