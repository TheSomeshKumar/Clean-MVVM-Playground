package com.example.playground.ui.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.playground.data.common.onError
import com.example.playground.data.common.onLoading
import com.example.playground.data.common.onSuccess
import com.example.playground.databinding.FragmentTvShowBinding
import com.example.playground.utils.autoCleared
import com.example.playground.utils.getError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TvShowFragment : Fragment() {
    private val viewModel: TvShowViewModel by viewModels()
    private var binding: FragmentTvShowBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTvShow.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvTvShow.setHasFixedSize(true)

        lifecycleScope.launchWhenStarted {
            viewModel.resultTvShow.collect { result ->
                result.onLoading { binding.progressLoading.show() }.onSuccess {
                    binding.progressLoading.hide()
                    val adapter = TvShowAdapter(it) { clickedShow ->
                        findNavController().navigate(
                            TvShowFragmentDirections.actionNavigationTvShowToNavigationDetail(
                                name = clickedShow.name, pictureUrl = clickedShow.posterPath, description = clickedShow.overview
                            )
                        )
                    }

                    binding.rvTvShow.adapter = adapter
                }.onError {
                    Toast.makeText(requireContext(), it.getError(requireContext()), Toast.LENGTH_LONG).show()
                    binding.progressLoading.hide()
                }
            }
        }

        viewModel.getTvShow()
    }
}
