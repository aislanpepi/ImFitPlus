package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityFormBinding
import kotlin.math.pow

class FormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val spinner: Spinner = binding.spinnerAtividade
        ArrayAdapter.createFromResource(
            this,
            R.array.niveis_atividade_fisica,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.btCalculoImc.setOnClickListener {
            val imc = binding.etPeso.text.toString().toDouble() / binding.etAltura.text.toString().toDouble().pow(2.0)
            val resultFormIntent = Intent(this, ResultFormActivity::class.java)
            val usuario = Bundle()

            usuario.putString("nome_completo",binding.etNomeCompleto.text.toString())
            usuario.putDouble("imc",imc)
            usuario.putString("nivel_atividade",binding.spinnerAtividade.selectedItem.toString())
            usuario.putInt("idade", binding.etIdade.text.toString().toInt())
            usuario.putString("sexo",if(binding.rbMasculino.isChecked) binding.rbMasculino.text.toString() else binding.rbFeminino.text.toString())
            usuario.putDouble("peso",binding.etPeso.text.toString().toDouble())
            usuario.putDouble("altura",binding.etAltura.text.toString().toDouble())
            resultFormIntent.putExtras(usuario)

            startActivity(resultFormIntent)
        }
    }
}