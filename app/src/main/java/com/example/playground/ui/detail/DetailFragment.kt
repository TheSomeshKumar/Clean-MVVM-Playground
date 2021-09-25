package com.example.playground.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.playground.Constants
import com.example.playground.databinding.FragmentDetailBinding
import com.example.playground.utils.autoCleared

class DetailFragment : Fragment() {
    private var binding by autoCleared<FragmentDetailBinding>()
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = args.name
        binding.tvDesc.text = args.description
        Glide.with(requireContext()).load(Constants.TMDB_POSTER_PATH + args.pictureUrl).into(binding.ivMedia)
    }
}
