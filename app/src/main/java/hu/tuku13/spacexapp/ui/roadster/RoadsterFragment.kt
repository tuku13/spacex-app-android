package hu.tuku13.spacexapp.ui.roadster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.databinding.FragmentRoadsterBinding
import hu.tuku13.spacexapp.network.Roadster
import hu.tuku13.spacexapp.util.ImageAdapter

class RoadsterFragment : Fragment() {

    private val viewModel: RoadsterViewModel by viewModels()
    private lateinit var binding: FragmentRoadsterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoadsterBinding.inflate(layoutInflater)

        viewModel.getRoadster()

        val adapter = ImageAdapter()
        binding.recycleViewRoadsterImages.let {
            it.adapter = adapter
            LinearSnapHelper().attachToRecyclerView(it)
        }

        viewModel.roadster.observe(viewLifecycleOwner) {
            it?.let {
                render(it)
                adapter.images = it.flickr_images
            }
        }

        return binding.root
    }

    private fun render(roadster: Roadster) {
        binding.tvName.text = roadster.name
        binding.tvDetails.text = roadster.details
        binding.tvLaunchDate.text = roadster.launch_date_utc
        binding.tvMass.text = requireContext()
            .getString(R.string.mass, roadster.launch_mass_kg)

        binding.tvSpeed.text = requireContext()
            .getString(R.string.speed_formatted, roadster.speed_kph)
        binding.tvDistanceFromEarth.text = requireContext()
            .getString(R.string.distance, roadster.earth_distance_km)
        binding.tvDistanceFromMars.text = requireContext()
            .getString(R.string.distance, roadster.mars_distance_km)

        binding.tvLongitude.text = roadster.longitude.toString()
        binding.tvApoapsis.text = requireContext()
            .getString(R.string.astronomical_unit, roadster.apoapsis_au)
        binding.tvPeriapsis.text = requireContext()
            .getString(R.string.astronomical_unit, roadster.periapsis_au)
    }

}