package com.example.playground.ui.movie

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
import com.example.playground.databinding.FragmentMoviesBinding
import com.example.playground.utils.autoCleared
import com.example.playground.utils.getError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private val viewModel: MoviesViewModel by viewModels()
    private var binding: FragmentMoviesBinding by autoCleared()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvMovies.setHasFixedSize(true)

        lifecycleScope.launchWhenStarted {
            viewModel.resultMovies.collect { result ->
                result.onLoading { binding.progressLoading.show() }.onSuccess {
                    binding.progressLoading.hide()
                    val adapter = MovieAdapter(it) { clickedMovie ->
                        findNavController().navigate(
                            MoviesFragmentDirections.actionNavigationMoviesToNavigationDetail(
                                name = clickedMovie.title, pictureUrl = clickedMovie.posterPath, description = clickedMovie.overview
                            )
                        )
                    }
                    binding.rvMovies.adapter = adapter
                }.onError {
                    Toast.makeText(
                        requireContext(), it.getError(requireContext()), Toast.LENGTH_LONG
                    ).show()
                    binding.progressLoading.hide()
                }
            }
        }
        viewModel.getMovies()
    }
}
