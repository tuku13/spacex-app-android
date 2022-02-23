package hu.tuku13.spacexapp.ui.roadster

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tuku13.spacexapp.network.Roadster
import hu.tuku13.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoadsterViewModel @Inject constructor(private val repository: SpaceXRepository): ViewModel() {
    private val _roadster = MutableLiveData<Roadster?>()
    val roadster : LiveData<Roadster?>
        get() = _roadster

    fun getRoadster() {
        _roadster.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val roadster = repository.getRoadster()
            _roadster.postValue(roadster)
        }
    }
}