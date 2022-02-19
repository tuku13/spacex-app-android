package hu.tuku13.spacexapp.ui.launch_details

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.databinding.FragmentLaunchDetailsBinding
import hu.tuku13.spacexapp.databinding.FragmentLaunchesBinding
import hu.tuku13.spacexapp.network.Launch
import hu.tuku13.spacexapp.network.LaunchPad
import hu.tuku13.spacexapp.network.Rocket

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

        binding.imgRocketImage.setOnClickListener {
            viewModel.rocket.value?.also {
                findNavController().navigate(
                    LaunchDetailsFragmentDirections
                        .actionLaunchDetailsFragmentToRocketDetailsFragment(it.id)
                )
            }
        }

        subscribeRenderers()

        return binding.root
    }

    private fun subscribeRenderers() {
        viewModel.launch.observe(viewLifecycleOwner) {
            it?.let { render(it) }
        }

        viewModel.launchPad.observe(viewLifecycleOwner) {
            it?.let { render(it) }
        }

        viewModel.rocket.observe(viewLifecycleOwner) {
            it?.let {render(it)}
        }

    }

    private fun render(rocket: Rocket) {
        binding.tvRocketName.text = rocket.name
        Glide
            .with(binding.imgRocketImage)
            .load(rocket.flickr_images[0])
            .into(binding.imgRocketImage)
    }

    private fun render(launchPad: LaunchPad) {
        binding.layoutLaunchPad.visibility = View.VISIBLE
        binding.tvLaunchSite.text = launchPad.full_name
    }

    private fun render(launch: Launch) {
        binding.tvMissionName.text = launch.name
        binding.tvDetails.text = if(launch.details.isNullOrBlank()) "-" else launch.details

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
            val text = "<a href=\"${it}\"></a>"
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