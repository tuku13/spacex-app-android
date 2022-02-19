package hu.tuku13.spacexapp.repository

import android.util.Log
import hu.tuku13.spacexapp.datasource.LaunchNetworkDataSource
import hu.tuku13.spacexapp.datasource.LaunchPadNetworkDataSource
import hu.tuku13.spacexapp.datasource.RoadsterNetworkDataSource
import hu.tuku13.spacexapp.datasource.RocketNetworkDataSource
import hu.tuku13.spacexapp.network.Launch
import hu.tuku13.spacexapp.network.LaunchPad
import hu.tuku13.spacexapp.network.Roadster
import hu.tuku13.spacexapp.network.Rocket
import hu.tuku13.spacexapp.util.NetworkErrorResult
import hu.tuku13.spacexapp.util.NetworkResult

object SpaceXRepository {
    private val launchNetworkDataSource = LaunchNetworkDataSource()
    private val rocketNetworkDataSource = RocketNetworkDataSource()
    private val roadsterNetworkDataSource = RoadsterNetworkDataSource()
    private val launchPadNetworkDataSource = LaunchPadNetworkDataSource()

    suspend fun getLaunches(): List<Launch>? {
        return when (val response = launchNetworkDataSource.getLaunches()) {
            is NetworkResult -> {
                (response.result as List<Launch>).reversed()
            }
            is NetworkErrorResult -> {
                Log.d("Repository", "Network Error")
                null
            }
        }
    }

    suspend fun getLaunch(id: String): Launch? {
        return when (val response = launchNetworkDataSource.getLaunch(id)) {
            is NetworkResult -> {
                response.result as Launch
            }
            is NetworkErrorResult -> {
                Log.d("Repository", "Network Error")
                null
            }
        }
    }

    suspend fun getLatestLaunch(): Launch? {
        return when (val response = launchNetworkDataSource.getLatestLaunch()) {
            is NetworkResult -> {
                response.result as Launch
            }
            is NetworkErrorResult -> {
                Log.d("Repository", "Network Error")
                null
            }
        }
    }

    suspend fun getRockets(): List<Rocket>? {
        return when (val response = rocketNetworkDataSource.getRockets()) {
            is NetworkResult -> {
                response.result as List<Rocket>
            }
            is NetworkErrorResult -> {
                Log.d("Repository.getRockets()", "Network Error")
                Log.d("Response", response.toString())
                null
            }
        }
    }

    suspend fun getRocket(id: String): Rocket? {
        return when (val response = rocketNetworkDataSource.getRocket(id)) {
            is NetworkResult -> {
                response.result as Rocket
            }
            is NetworkErrorResult -> {
                Log.d("Repository", "Network Error")
                null
            }
        }
    }

    suspend fun getRoadster(): Roadster? {
        return when (val response = roadsterNetworkDataSource.getRoadster()) {
            is NetworkResult -> {
                response.result as Roadster
            }
            is NetworkErrorResult -> {
                Log.d("Repository", "Network Error")
                null
            }
        }
    }

    suspend fun getLaunchPad(id: String): LaunchPad? {
        return when (val response = launchPadNetworkDataSource.getLaunchpad(id)) {
            is NetworkResult -> {
                response.result as LaunchPad
            }
            is NetworkErrorResult -> {
                Log.d("Repository", "Network Error")
                null
            }
        }
    }
}