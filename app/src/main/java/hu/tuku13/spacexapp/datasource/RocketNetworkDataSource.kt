package hu.tuku13.spacexapp.datasource

import android.util.Log
import hu.tuku13.spacexapp.network.SpaceXApiService
import hu.tuku13.spacexapp.util.NetworkErrorResult
import hu.tuku13.spacexapp.util.NetworkResponse
import hu.tuku13.spacexapp.util.NetworkResult
import kotlin.Exception

class RocketNetworkDataSource(private val api: SpaceXApiService) {
    suspend fun getRockets(): NetworkResponse<Any> {
        try {
            val response = api.getRockets()

            response?.let {
                return NetworkResult(it.body()!!)
            }
            return NetworkErrorResult(Exception("No data"))
        } catch (e: Exception) {
            Log.d("Exception", e.toString())
            return NetworkErrorResult(e)
        }
    }

    suspend fun getRocket(id: String): NetworkResponse<Any> {
        try {
            val response = api.getRocket(id)

            response?.let {
                return NetworkResult(it.body()!!)
            }
            return NetworkErrorResult(Exception("No data"))

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }
    }
}