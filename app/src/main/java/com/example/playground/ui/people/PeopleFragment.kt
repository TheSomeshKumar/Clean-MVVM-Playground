package com.example.playground.ui.people

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
import com.example.playground.databinding.FragmentPeopleBinding
import com.example.playground.utils.autoCleared
import com.example.playground.utils.getError
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class PeopleFragment : Fragment() {
    private val viewModel: PeopleViewModel by viewModels()
    private var binding: FragmentPeopleBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPeople.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvPeople.setHasFixedSize(true)

        lifecycleScope.launchWhenStarted {
            viewModel.resultPeople.collect { result ->
                result.onLoading { binding.progressLoading.show() }.onSuccess {
                    binding.progressLoading.hide()
                    val adapter = PeopleAdapter(it) { clickedPerson ->
                        findNavController().navigate(
                            PeopleFragmentDirections.actionNavigationPeopleToNavigationDetail(
                                name = clickedPerson.name, pictureUrl = clickedPerson.profilePath, description = clickedPerson.name
                            )
                        )
                    }
                    binding.rvPeople.adapter = adapter
                }.onError {
                    Toast.makeText(
                        requireContext(), it.getError(requireContext()), Toast.LENGTH_LONG
                    ).show()
                    binding.progressLoading.hide()
                }
            }
        }

        viewModel.getPeople()
    }
}
