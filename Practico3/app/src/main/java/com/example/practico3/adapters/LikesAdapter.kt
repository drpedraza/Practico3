package com.example.practico3.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3.R
import com.example.practico3.models.Mascota

class LikesAdapter(private val mascotas: List<Mascota>) : RecyclerView.Adapter<LikesAdapter.LikesViewHolder>() {

    class LikesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgMascotaLike: ImageView = itemView.findViewById(R.id.imgMascotaLike)
        val txtMascotaName: TextView = itemView.findViewById(R.id.txtMascotaName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LikesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_list, parent, false)
        return LikesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LikesViewHolder, position: Int) {
        val mascota = mascotas[position]
        holder.imgMascotaLike.setImageResource(mascota.imagenes[0])
        holder.txtMascotaName.text = mascota.nombre
    }

    override fun getItemCount(): Int {
        return mascotas.size
    }
}
