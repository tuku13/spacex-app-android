package hu.tuku13.spacexapp.ui.launch_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.tuku13.spacexapp.network.CrewMember
import hu.tuku13.spacexapp.network.Launch
import hu.tuku13.spacexapp.network.LaunchPad
import hu.tuku13.spacexapp.network.Rocket
import hu.tuku13.spacexapp.repository.SpaceXRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchDetailsViewModel @Inject constructor(
    private val repository: SpaceXRepository
) : ViewModel() {
    private val _launch = MutableLiveData<Launch?>()
    val launch: LiveData<Launch?>
        get() = _launch

    private val _launchPad = MutableLiveData<LaunchPad?>()
    val launchPad: LiveData<LaunchPad?>
        get() = _launchPad

    private val _rocket = MutableLiveData<Rocket?>()
    val rocket: LiveData<Rocket?>
        get() = _rocket

    private val _crew = MutableLiveData<List<CrewMember>>()
    val crew: LiveData<List<CrewMember>>
        get() = _crew

    fun getLaunch(id: String) {
        _launch.value = null
        viewModelScope.launch(Dispatchers.IO) {

            val launch = repository.getLaunch(id)
            _launch.postValue(launch)

            if (launch != null) {
                val launchPad = repository.getLaunchPad(launch.launchpad)
                _launchPad.postValue(launchPad)

                val rocket = repository.getRocket(launch.rocket)
                _rocket.postValue(rocket)

                launch.crew.forEach {
                    val member = repository.getCrewMember(it)
                    if (member != null) {
                        val list = _crew.value?.toMutableList() ?: mutableListOf()
                        list.add(member)
                        _crew.postValue(list)
                    }
                }
            }

        }
    }
}