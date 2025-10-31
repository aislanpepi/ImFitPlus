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
            val idade = binding.etIdade.text.toString().trim().toIntOrNull()
            val peso = binding.etPeso.text.toString().trim().toDoubleOrNull()
            val altura = binding.etAltura.text.toString().trim().toDoubleOrNull()

            val resultFormIntent = Intent(this, ResultFormActivity::class.java)

            if(nome.isEmpty()) {
                Toast.makeText(this,"Campo nome não pode ser vazio",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(idade == null || idade <= 0 || idade > 150){
                Toast.makeText(this,"Campo idade é invalido.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(peso == null || peso <= 0){
                Toast.makeText(this,"Campo peso é invalido.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(altura == null || altura <= 0){
                Toast.makeText(this,"Campo altura é invalido.",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usuario = Bundle()

            usuario.putString("nome_completo",nome)
            usuario.putInt("idade", idade)
            usuario.putDouble("peso",peso)
            usuario.putString("sexo",if(binding.rbMasculino.isChecked) binding.rbMasculino.text.toString()
                                    else binding.rbFeminino.text.toString())
            usuario.putDouble("altura",altura)
            usuario.putString("nivel_atividade",binding.spinnerAtividade.selectedItem.toString())
            val imc = peso / altura.pow(2.0)
            usuario.putDouble("imc",imc)

            resultFormIntent.putExtras(usuario)

            startActivity(resultFormIntent)
        }
    }
}