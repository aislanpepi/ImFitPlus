package br.edu.ifsp.scl.prdm.sc3038939.imfitplus.adapter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.R
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.databinding.TilePersonBinding
import br.edu.ifsp.scl.prdm.sc3038939.imfitplus.model.Person

class PersonAdapter( context: Context, private val personList: MutableList<Person>): ArrayAdapter<Person>(
    context,
    R.layout.tile_person,
    personList
) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val person = personList[position]

        var personTileView = convertView
        if(personTileView == null) {
            TilePersonBinding.inflate(
                context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                parent,
                false
            ).apply {
                personTileView = root
                val tilePersonViewHolder = TilePersonViewHolder(nameTv,alturaTv, pesoTv,imcTv,tmbTv)
                personTileView.tag = tilePersonViewHolder
            }
        }

        val tlViewHolder = personTileView?.tag as TilePersonViewHolder
        tlViewHolder.nameTv.text = person.name
        tlViewHolder.pesoTv.text = "Peso: " + person.weight.toString() + " Kg"
        tlViewHolder.alturaTv.text = "Altura: " + person.height.toString() + " M"
        tlViewHolder.imcTv.text = "IMC: %.2f".format(person.health.imc.toString())
        tlViewHolder.tmbTv.text = "TMB: %.2f".format(person.health.gastoCalorico.toString())

        return personTileView
    }

    private data class TilePersonViewHolder(val nameTv: TextView, val alturaTv: TextView, val pesoTv: TextView, val imcTv: TextView, val tmbTv: TextView)
}