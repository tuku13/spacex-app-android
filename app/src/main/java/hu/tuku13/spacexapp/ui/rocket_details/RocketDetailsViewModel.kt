package hu.tuku13.spacexapp.ui.rocket_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.tuku13.spacexapp.network.Rocket
import hu.tuku13.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RocketDetailsViewModel: ViewModel() {
    private val _rocket = MutableLiveData<Rocket?>()
    val rocket: LiveData<Rocket?>
        get() = _rocket

    fun getRocketData(id: String) {
        _rocket.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val rocket = SpaceXRepository.getRocket(id)
            _rocket.postValue(rocket)

            //TODO egyéb adatok betöltése
        }
    }
}