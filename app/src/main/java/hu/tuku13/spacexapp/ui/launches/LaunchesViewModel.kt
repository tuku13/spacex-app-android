package hu.tuku13.spacexapp.ui.launches

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.tuku13.spacexapp.datasource.LaunchNetworkDataSource
import hu.tuku13.spacexapp.network.Launch
import hu.tuku13.spacexapp.repository.SpaceXRepository
import hu.tuku13.spacexapp.util.NetworkErrorResult
import hu.tuku13.spacexapp.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaunchesViewModel : ViewModel() {
    private val _launches = MutableLiveData<List<Launch>?>()
    val launches : LiveData<List<Launch>?>
        get() = _launches

    private val _navigateToLaunchDetails = MutableLiveData<Launch?>()
    val navigateToLaunchDetails
        get() = _navigateToLaunchDetails

    fun selectLaunch(launch: Launch) {
        _navigateToLaunchDetails.value = launch
    }

    fun doneNavigating() {
        _navigateToLaunchDetails.value = null
    }

    fun getLaunches() {
        _launches.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val launches = SpaceXRepository.getLaunches()
            _launches.postValue(launches)
        }
    }
}