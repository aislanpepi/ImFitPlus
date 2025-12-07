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
                val tilePersonViewHolder = TilePersonViewHolder(nameTv,alturaTv, pesoTv)
                personTileView.tag = tilePersonViewHolder
            }
        }

        val tlViewHolder = personTileView?.tag as TilePersonViewHolder
        tlViewHolder.nameTv.text = person.name
        tlViewHolder.pesoTv.text = person.weight.toString()
        tlViewHolder.alturaTv.text = person.height.toString()

        return personTileView
    }

    private data class TilePersonViewHolder(val nameTv: TextView, val alturaTv: TextView, val pesoTv: TextView)
}