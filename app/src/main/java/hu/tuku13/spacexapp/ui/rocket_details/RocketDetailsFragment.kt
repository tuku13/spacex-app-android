package hu.tuku13.spacexapp.ui.rocket_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import dagger.hilt.android.AndroidEntryPoint
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.databinding.FragmentRocketDetailsBinding
import hu.tuku13.spacexapp.network.Rocket
import hu.tuku13.spacexapp.util.ImageAdapter

@AndroidEntryPoint
class RocketDetailsFragment : Fragment() {
    private lateinit var binding: FragmentRocketDetailsBinding
    private val viewModel: RocketDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketDetailsBinding.inflate(layoutInflater)

        val rocketId = RocketDetailsFragmentArgs.fromBundle(requireArguments()).rocketId
        viewModel.getRocketData(rocketId)

        val adapter = ImageAdapter()

        binding.recycleViewRocketImages.let {
            it.adapter = adapter
            LinearSnapHelper().attachToRecyclerView(it)
        }

        viewModel.rocket.observe(viewLifecycleOwner) {
            it?.let{
                render(it)
                adapter.images = it.flickr_images
            }
        }

        return binding.root
    }

    private fun render(rocket: Rocket) {
        binding.tvName.text = rocket.name

        if (rocket.active) {
            binding.tvStatus.let {
                it.text = "Active"
                it.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
            }
        } else {
            binding.tvStatus.let {
                it.text = "Inactive"
                it.setTextColor(ContextCompat.getColor(requireContext(), R.color.red))
            }
        }

        binding.tvDescription.text = rocket.description
        binding.tvHeight.text = requireContext()
            .getString(R.string.height, rocket.height.meters)
        binding.tvDiameter.text = requireContext()
            .getString(R.string.diameter, rocket.diameter.meters)
        binding.tvMass.text = requireContext()
            .getString(R.string.mass, rocket.mass.kg)

        binding.tvNumberOfEngines.text = requireContext()
            .getString(R.string.engines_formatted, rocket.engines.number)
        binding.tvPropellant1.text = requireContext()
            .getString(R.string.propellant1, rocket.engines.propellant_1)
        binding.tvPropellant2.text = requireContext()
            .getString(R.string.propellant2, rocket.engines.propellant_2)

        binding.tvFirstFlight.text = requireContext()
            .getString(R.string.first_flight, rocket.first_flight)
        binding.tvCost.text = requireContext()
            .getString(R.string.cost_per_launch, rocket.cost_per_launch)
        binding.tvSuccessRate.text = requireContext()
            .getString(R.string.success_rate, rocket.success_rate_pct)
    }

}