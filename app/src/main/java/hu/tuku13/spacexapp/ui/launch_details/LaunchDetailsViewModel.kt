package hu.tuku13.spacexapp.ui.launch_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.tuku13.spacexapp.datasource.LaunchNetworkDataSource
import hu.tuku13.spacexapp.network.Launch
import hu.tuku13.spacexapp.repository.SpaceXRepository
import hu.tuku13.spacexapp.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaunchDetailsViewModel: ViewModel() {
    private val _launch = MutableLiveData<Launch?>()
    val launch : LiveData<Launch?>
        get() = _launch

    fun getLaunch(id: String) {
        _launch.value = null
        viewModelScope.launch(Dispatchers.IO) {
            val launch = SpaceXRepository.getLaunch(id)
            _launch.postValue(launch)
        }
    }
}