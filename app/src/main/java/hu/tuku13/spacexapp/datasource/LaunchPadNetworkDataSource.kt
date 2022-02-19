package hu.tuku13.spacexapp.datasource

import android.util.Log
import hu.tuku13.spacexapp.network.SpaceXApi
import hu.tuku13.spacexapp.util.NetworkErrorResult
import hu.tuku13.spacexapp.util.NetworkResponse
import hu.tuku13.spacexapp.util.NetworkResult

class LaunchPadNetworkDataSource {
    suspend fun getLaunchpad(id: String): NetworkResponse<Any> {
        try {
            val response = SpaceXApi.retrofitService.getLaunchPad(id)

            response?.let {
                return NetworkResult(it.body()!!)
            }
            return NetworkErrorResult(Exception("No data"))

        } catch (e: Exception) {
            Log.d("DataSource", e.toString())
            return NetworkErrorResult(e)
        }
    }
}