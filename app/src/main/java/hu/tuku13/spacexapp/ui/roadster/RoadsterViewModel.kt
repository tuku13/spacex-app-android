package hu.tuku13.spacexapp.ui.roadster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.tuku13.spacexapp.network.Roadster
import hu.tuku13.spacexapp.network.SpaceXApi
import hu.tuku13.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoadsterViewModel: ViewModel() {
    private val _roadster = MutableLiveData<Roadster?>()
    val roadster : LiveData<Roadster?>
        get() = _roadster

    fun getRoadster() {
        _roadster.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val roadster = SpaceXRepository.getRoadster()
            _roadster.postValue(roadster)
        }
    }
}