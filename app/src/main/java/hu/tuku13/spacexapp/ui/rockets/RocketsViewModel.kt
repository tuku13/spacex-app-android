package hu.tuku13.spacexapp.ui.rockets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tuku13.spacexapp.datasource.RocketNetworkDataSource
import hu.tuku13.spacexapp.network.Rocket
import hu.tuku13.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RocketsViewModel @Inject constructor(private val repository: SpaceXRepository) : ViewModel() {

    private val _rockets = MutableLiveData<List<Rocket>?>()
    val rockets: LiveData<List<Rocket>?>
        get() = _rockets

    private val _navigateToRocketDetails = MutableLiveData<Rocket?>()
    val navigateToRocketDetails: LiveData<Rocket?>
        get() = _navigateToRocketDetails

    fun selectRocket(rocket: Rocket) {
        _navigateToRocketDetails.value = rocket
    }

    fun doneNavigating() {
        _navigateToRocketDetails.value = null
    }

    fun getRockets() {
        _rockets.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val rockets = repository.getRockets()
            _rockets.postValue(rockets)
        }
    }
}