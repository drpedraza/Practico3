package com.example.practico3.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practico3.R
import com.example.practico3.models.FakeDB
import com.example.practico3.models.Mascota
import com.example.practico3.ui.viewmodels.ImageViewModel

class ImageFragment : Fragment() {
    private lateinit var txtFullName: TextView
    private lateinit var btnLike: ImageButton
    private lateinit var btnDislike: ImageButton
    private lateinit var btnMylike: ImageButton
    private lateinit var imgMascota: ImageView
    private lateinit var barra1: ProgressBar
    private lateinit var barra2: ProgressBar
    private lateinit var barra3: ProgressBar

    private var currentMascotaIndex = 0
    private var currentImageIndex = 0

    private val imageViewModel: ImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_image, container, false)
        txtFullName = view.findViewById(R.id.txtFullName)
        btnLike = view.findViewById(R.id.btnLike)
        btnDislike = view.findViewById(R.id.btnDislike)
        btnMylike = view.findViewById(R.id.btnMylike)
        imgMascota = view.findViewById(R.id.imgMascota)
        barra1 = view.findViewById(R.id.barra1)
        barra2 = view.findViewById(R.id.barra2)
        barra3 = view.findViewById(R.id.barra3)

        observeViewModel()
        setupEventListeners()
        onTouchEventListeners()
        return view
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun onTouchEventListeners() {
        imgMascota.setOnTouchListener { _, event ->
            val width = imgMascota.width
            val touchX = event.x

            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    val mascota = imageViewModel.getMascotas().value?.get(currentMascotaIndex)

                    if (mascota != null) {
                        if (touchX >= width / 2) {
                            currentImageIndex++
                            if (currentImageIndex >= mascota.imagenes.size) {
                                currentImageIndex = 0
                                currentMascotaIndex =
                                    (currentMascotaIndex + 1) % imageViewModel.getMascotas().value!!.size
                                updateUI(imageViewModel.getMascotas().value!![currentMascotaIndex])
                            } else {
                                imgMascota.setImageResource(mascota.imagenes[currentImageIndex])
                                updateProgressBar(currentImageIndex)
                            }
                        } else {
                            if (currentImageIndex > 0) {
                                currentImageIndex--
                                imgMascota.setImageResource(mascota.imagenes[currentImageIndex])
                                updateProgressBar(currentImageIndex)
                            }
                        }
                    }
                }
            }
            true
        }
    }

    private fun updateProgressBar(index: Int) {
        val progressBars = listOf(barra1, barra2, barra3)

        progressBars.forEach {
            it.progressDrawable?.setTint(
                ContextCompat.getColor(requireContext(), android.R.color.darker_gray)
            )
        }

        val color = ContextCompat.getColor(requireContext(), R.color.black)
        progressBars[index].progressDrawable?.setTint(color)
    }

    private fun setupEventListeners() {
        btnMylike.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_imageFragment_to_likesFragment)
        }
        btnLike.setOnClickListener {
            val mascotas = imageViewModel.getMascotas().value
            val currentMascota = mascotas?.get(currentMascotaIndex)

            if (currentMascota != null) {
                FakeDB.addLike(currentMascota)
                imageViewModel.removeMascota(currentMascota)
            }
        }
        btnDislike.setOnClickListener {
            val mascotas = imageViewModel.getMascotas().value
            val currentMascota = mascotas?.get(currentMascotaIndex)

            if (currentMascota != null) {
                imageViewModel.removeMascota(currentMascota)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        imageViewModel.getMascotas().observe(viewLifecycleOwner) { mascotas ->
            if (mascotas.isNotEmpty()) {
                currentMascotaIndex = 0
                updateUI(mascotas[currentMascotaIndex])
            } else {
                txtFullName.text = "No hay más mascotas"
                imgMascota.setImageResource(0)
            }
        }
    }

    private fun updateUI(mascota: Mascota) {
        txtFullName.text = mascota.nombre
        imgMascota.setImageResource(mascota.imagenes[currentImageIndex])
        resetProgressBars()
    }

    private fun resetProgressBars() {
        barra1.progress = 0
        barra2.progress = 0
        barra3.progress = 0
        currentImageIndex = 0
        updateProgressBar(currentImageIndex)
    }
}