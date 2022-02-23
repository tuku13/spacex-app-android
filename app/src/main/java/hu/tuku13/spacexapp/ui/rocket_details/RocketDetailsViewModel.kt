package hu.tuku13.spacexapp.ui.rocket_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tuku13.spacexapp.network.Rocket
import hu.tuku13.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketDetailsViewModel @Inject constructor(private val repository: SpaceXRepository): ViewModel() {
    private val _rocket = MutableLiveData<Rocket?>()
    val rocket: LiveData<Rocket?>
        get() = _rocket

    fun getRocketData(id: String) {
        _rocket.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val rocket = repository.getRocket(id)
            _rocket.postValue(rocket)
        }
    }
}