package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityResultFormBinding

class ResultFormActivity: AppCompatActivity() {
    private lateinit var binding: ActivityResultFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}