package com.example.practico3.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico3.R
import com.example.practico3.adapters.LikesAdapter
import com.example.practico3.ui.viewmodels.LikesViewModel

class LikesFragment : Fragment() {
    private lateinit var rvListLikes: RecyclerView
    private val likesViewModel: LikesViewModel by viewModels()
    private lateinit var adapter: LikesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_likes, container, false)
        rvListLikes = view.findViewById(R.id.rvListLikes)

        setupRecyclerView()
        observeViewModel()
        return view
    }

    private fun observeViewModel() {
        likesViewModel.getLikes().observe(viewLifecycleOwner) { mascotas ->
            adapter = LikesAdapter(mascotas)
            rvListLikes.adapter = adapter
        }
    }


    private fun setupRecyclerView() {
        rvListLikes.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        likesViewModel.refreshLikes()
    }



    companion object {

        @JvmStatic
        fun newInstance() =
            LikesFragment()
    }
}