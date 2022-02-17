package hu.tuku13.spacexapp.ui.rockets

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import hu.tuku13.spacexapp.databinding.FragmentRocketsBinding

class RocketsFragment : Fragment() {

    private val viewModel: RocketsViewModel by viewModels()
    private lateinit var binding: FragmentRocketsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketsBinding.inflate(layoutInflater)
        viewModel.getRockets()
        
        val adapter = RocketAdapter {
            viewModel.selectRocket(it)
        }

        binding.recycleView.adapter = adapter

        viewModel.rockets.observe(viewLifecycleOwner) {
            it?.let {
                adapter.rockets = it
            }
        }

        viewModel.navigateToRocketDetails.observe(viewLifecycleOwner) {
            it?.also {
                this.findNavController().navigate(
                    RocketsFragmentDirections.actionRocketsFragmentToRocketDetailsFragment(it.id)
                )
            }
            viewModel.doneNavigating()
        }

        return binding.root
    }

}