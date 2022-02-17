package hu.tuku13.spacexapp.ui.rocket_details

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.BundleCompat
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.databinding.FragmentLaunchesBinding
import hu.tuku13.spacexapp.databinding.FragmentRocketDetailsBinding
import hu.tuku13.spacexapp.databinding.FragmentRocketsBinding

class RocketDetailsFragment : Fragment() {
    private lateinit var binding: FragmentRocketDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketDetailsBinding.inflate(layoutInflater)

//        val rocketId = RocketDetailsFragmentArgs.fromBundle(requireArguments()).rocketId
//        binding.tvRocketId.text = rocketId

        return binding.root
    }


}