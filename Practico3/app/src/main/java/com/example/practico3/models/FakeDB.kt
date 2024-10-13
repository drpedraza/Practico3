package com.example.practico3.models

object FakeDB {
    private val likesMascotas: MutableList<Mascota> = mutableListOf()

    fun addLike(mascota: Mascota) {
        if (!likesMascotas.contains(mascota)) {
            likesMascotas.add(mascota)
        }
    }

    fun getLikes(): List<Mascota> {
        return likesMascotas
    }
}
