package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.R
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.controller.MainController
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.ActivityFormBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person
import java.time.LocalDate
import java.time.Period
import kotlin.getValue
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

        var sexo: String

        binding.btCalculoImc.setOnClickListener {
            val nome = binding.etNomeCompleto.text.toString().trim()
            val dataNasc = LocalDate.parse(binding.etDataNasc.text.toString().trim())
            val peso = binding.etPeso.text.toString().trim().toDoubleOrNull()
            val altura = binding.etAltura.text.toString().trim().toDoubleOrNull()
            if (binding.rbMasculino.isChecked) {
               sexo = "Masculino"
            } else {
                sexo = "Feminino"
            }

            val idade = calcularIdade(dataNasc)

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
            usuario.putInt("idade",idade)
            usuario.putString("dataNasc", dataNasc.toString())
            usuario.putDouble("peso",peso)
            usuario.putString("sexo", sexo)
            usuario.putDouble("altura",altura)
            usuario.putString("nivel_atividade",binding.spinnerAtividade.selectedItem.toString())

            val imc = peso / altura.pow(2.0)

            usuario.putDouble("imc",imc)

            resultFormIntent.putExtras(usuario)
            startActivity(resultFormIntent)
        }
    }

    fun calcularIdade(dataNasc: LocalDate): Int {
        val dataAtual = LocalDate.now()
        return Period.between(dataNasc, dataAtual).years
    }
}