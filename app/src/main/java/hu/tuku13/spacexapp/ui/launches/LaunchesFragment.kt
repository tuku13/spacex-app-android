package hu.tuku13.spacexapp.ui.launches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.databinding.FragmentLaunchesBinding

class LaunchesFragment : Fragment() {

    private lateinit var binding: FragmentLaunchesBinding
    private val viewModel : LaunchesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLaunchesBinding.inflate(layoutInflater)
        viewModel.getLaunches()

        val adapter = LaunchAdapter(
            { viewModel.selectLaunch(it) },
            ContextCompat.getColor(requireContext(), R.color.green),
            ContextCompat.getColor(requireContext(), R.color.red),
            ContextCompat.getColor(requireContext(), R.color.blue)
        )

        binding.recycleView.adapter = adapter

        viewModel.launches.observe(viewLifecycleOwner) {
            it?.let {
                adapter.launches = it
            }
        }

        viewModel.navigateToLaunchDetails.observe(viewLifecycleOwner) {
            it?.let {
                this.findNavController().navigate(
                    LaunchesFragmentDirections.actionLaunchesFragmentToLaunchDetailsFragment(it.id)
                )
                viewModel.doneNavigating()
            }
        }

        return binding.root
    }

}