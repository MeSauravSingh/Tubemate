package com.example.tubemate.fragments.fullScreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.tubemate.R
import com.example.tubemate.databinding.FragmentFullScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullScreenFragment : Fragment(R.layout.fragment_full_screen){

    private lateinit var binding: FragmentFullScreenBinding
    private val videModel : FullScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFullScreenBinding.bind(view)
        binding.apply {


        }

    }
}