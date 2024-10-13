package com.example.practico3.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico3.models.FakeDB
import com.example.practico3.models.Mascota

class LikesViewModel : ViewModel() {
    private val likes: MutableLiveData<List<Mascota>> = MutableLiveData()

    init {
        likes.value = FakeDB.getLikes()
    }

    fun getLikes(): LiveData<List<Mascota>> = likes

    fun refreshLikes() {
        likes.value = FakeDB.getLikes()
    }
}
