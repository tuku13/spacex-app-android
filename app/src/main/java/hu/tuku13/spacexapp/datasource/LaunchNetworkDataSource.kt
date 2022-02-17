package hu.tuku13.spacexapp.datasource

import hu.tuku13.spacexapp.network.SpaceXApi
import hu.tuku13.spacexapp.util.NetworkErrorResult
import hu.tuku13.spacexapp.util.NetworkResponse
import hu.tuku13.spacexapp.util.NetworkResult
import kotlin.Exception

class LaunchNetworkDataSource {
    suspend fun getLaunches(): NetworkResponse<Any> {
        try {
            val response = SpaceXApi.retrofitService.getLaunches()

            response?.let {
                return NetworkResult(it.body()!!)
            }
            return NetworkErrorResult(Exception("No data"))

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }
    }

    suspend fun getLaunch(id: String): NetworkResponse<Any> {
        try {
            val response = SpaceXApi.retrofitService.getLaunch(id)

            response?.let {
                return NetworkResult(it.body()!!)
            }
            return NetworkErrorResult(Exception("No data"))

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }
    }

    suspend fun getLatestLaunch(): NetworkResponse<Any> {
        try {
            val response = SpaceXApi.retrofitService.getLatestLaunch()

            response?.let {
                return NetworkResult(it.body()!!)
            }
            return NetworkErrorResult(Exception("No data"))

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }
    }
}