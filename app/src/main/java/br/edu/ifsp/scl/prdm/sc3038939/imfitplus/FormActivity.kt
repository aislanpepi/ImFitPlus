package br.edu.ifsp.scl.prdm.sc3038939.imfitplus

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
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
            val nome = binding.etNomeCompleto.text.toString().trim()
            val idadeStr = binding.etIdade.text.toString().trim()
            val pesoStr = binding.etPeso.text.toString().trim()
            val alturaStr = binding.etAltura.text.toString().trim()

            val resultFormIntent = Intent(this, ResultFormActivity::class.java)

            if(nome.isEmpty()) {
                Toast.makeText(this,"Campo nome não pode ser vazio",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(idadeStr.isEmpty() || idadeStr.toInt() <= 0 || idadeStr.toInt() > 150){
                Toast.makeText(this,"Campo idade é invalido.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(idadeStr.isEmpty() || pesoStr.toDouble() <= 0){
                Toast.makeText(this,"Campo peso é invalido.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(alturaStr.toDouble() <= 0){
                Toast.makeText(this,"Campo altura é invalido.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuario = Bundle()

            usuario.putString("nome_completo",nome)
            usuario.putInt("idade", idadeStr.toInt())
            usuario.putDouble("peso",pesoStr.toDouble())
            usuario.putString("sexo",if(binding.rbMasculino.isChecked) binding.rbMasculino.text.toString()
                                    else binding.rbFeminino.text.toString())
            usuario.putDouble("altura",alturaStr.toDouble())
            usuario.putString("nivel_atividade",binding.spinnerAtividade.selectedItem.toString())
            val imc = pesoStr.toDouble() / alturaStr.toDouble().pow(2.0)
            usuario.putDouble("imc",imc)

            resultFormIntent.putExtras(usuario)

            startActivity(resultFormIntent)
        }
    }
}