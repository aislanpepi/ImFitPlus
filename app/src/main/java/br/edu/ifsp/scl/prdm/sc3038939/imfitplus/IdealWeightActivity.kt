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
        setContentView(view)
    }
}