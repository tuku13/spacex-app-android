package hu.tuku13.spacexapp.ui.roadster

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import hu.tuku13.spacexapp.R
import hu.tuku13.spacexapp.databinding.FragmentRoadsterBinding

class RoadsterFragment : Fragment() {

    private val viewModel: RoadsterViewModel by viewModels()
    private lateinit var binding: FragmentRoadsterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoadsterBinding.inflate(layoutInflater)

        viewModel.getRoadster()

        return binding.root
    }

}