package hu.tuku13.spacexapp.ui.launch_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.databinding.FragmentLaunchDetailsBinding
import hu.tuku13.spacexapp.databinding.FragmentLaunchesBinding
import hu.tuku13.spacexapp.network.Launch

class LaunchDetailsFragment : Fragment() {

    private val viewModel: LaunchDetailsViewModel by viewModels()
    private lateinit var binding: FragmentLaunchDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchDetailsBinding.inflate(layoutInflater)

        val launchId = LaunchDetailsFragmentArgs.fromBundle(requireArguments()).launchId
        viewModel.getLaunch(launchId)
        viewModel.launch.observe(viewLifecycleOwner) {
            it?.let { render(it) }
        }

        return binding.root
    }

    private fun render(launch: Launch) {
        binding.tvMissionName.text = launch.name
        binding.tvDetails.text = launch.details

        when {
            launch.upcoming -> {
                binding.tvSuccessful.text = "Upcoming"
                binding.tvSuccessful.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.blue)
                )
            }
            launch.success == true -> {
                binding.tvSuccessful.text = "Succesful"
                binding.tvSuccessful.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.green)
                )
            }
            else -> {
                binding.tvSuccessful.text = "Failure"
                binding.tvSuccessful.setTextColor(
                    ContextCompat.getColor(requireContext(), R.color.red)
                )
            }
        }

        launch.links.reddit.launch?.let {
            binding.layoutReddit.visibility = View.VISIBLE
            binding.spaceReddit.visibility = View.VISIBLE
            binding.tvReddit.text = it
        }

        launch.links.article?.let {
            binding.layoutNews.visibility = View.VISIBLE
            binding.spaceNews.visibility = View.VISIBLE
            binding.tvNews.text = it
        }

        launch.links.webcast?.let {
            binding.layoutVideo.visibility = View.VISIBLE
            binding.spaceVideo.visibility = View.VISIBLE
            binding.tvVideo.text = it
        }

    }

}