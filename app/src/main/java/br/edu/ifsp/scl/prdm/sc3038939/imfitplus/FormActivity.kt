package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.FormBinding

class FormActivity : AppCompatActivity() {

    private lateinit var binding: FormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}