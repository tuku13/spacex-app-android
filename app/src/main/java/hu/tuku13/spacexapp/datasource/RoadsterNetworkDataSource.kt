package hu.tuku13.spacexapp.datasource

import hu.tuku13.spacexapp.network.SpaceXApiService
import hu.tuku13.spacexapp.util.NetworkErrorResult
import hu.tuku13.spacexapp.util.NetworkResponse
import hu.tuku13.spacexapp.util.NetworkResult

class RoadsterNetworkDataSource(private val api: SpaceXApiService) {
    suspend fun getRoadster(): NetworkResponse<Any> {
        try {
            val response = api.getRoadster()

            response?.let {
                return NetworkResult(it.body()!!)
            }
            return NetworkErrorResult(Exception("No data"))

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }
    }
}