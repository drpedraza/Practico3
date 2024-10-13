package com.example.practico3.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico3.R
import com.example.practico3.models.Mascota

class ImageViewModel : ViewModel() {
    private val mascotas: MutableLiveData<List<Mascota>> = MutableLiveData()

    init {
        mascotas.value = listOf(
            Mascota(
                "Aitor", listOf(
                    R.drawable.img1,
                    R.drawable.img2,
                    R.drawable.img3
                )
            ),
            Mascota(
                "Luna", listOf(
                    R.drawable.img4,
                    R.drawable.img5,
                    R.drawable.img6
                )
            ),
            Mascota(
                "Popeye", listOf(
                    R.drawable.img3,
                    R.drawable.img4,
                    R.drawable.img5
                )
            ),
            Mascota(
                "King", listOf(
                    R.drawable.img2,
                    R.drawable.img4,
                    R.drawable.img6
                )
            ),
            Mascota(
                "Becky", listOf(
                    R.drawable.img6,
                    R.drawable.img1,
                    R.drawable.img2
                )
            ),
            Mascota(
                "Bruma", listOf(
                    R.drawable.img3,
                    R.drawable.img1,
                    R.drawable.img4
                )
            ),
            Mascota(
                "Duquesa", listOf(
                    R.drawable.img5,
                    R.drawable.img2,
                    R.drawable.img6
                )
            ),
            Mascota(
                "Toby", listOf(
                    R.drawable.img1,
                    R.drawable.img3,
                    R.drawable.img5
                )
            ),
            Mascota(
                "Nico", listOf(
                    R.drawable.img2,
                    R.drawable.img4,
                    R.drawable.img6
                )
            ),
            Mascota(
                "Coni", listOf(
                    R.drawable.img1,
                    R.drawable.img6,
                    R.drawable.img2
                )
            ),
        )
    }

    fun getMascotas(): LiveData<List<Mascota>> = mascotas

    fun removeMascota(mascota: Mascota) {
        val currentList = mascotas.value?.toMutableList() ?: mutableListOf()
        currentList.remove(mascota)
        mascotas.value = currentList
    }

}