package hu.tuku13.spacexapp.datasource

import hu.tuku13.spacexapp.network.SpaceXApiService
import hu.tuku13.spacexapp.util.NetworkErrorResult
import hu.tuku13.spacexapp.util.NetworkResponse
import hu.tuku13.spacexapp.util.NetworkResult

class CrewNetworkDataSource(private val api: SpaceXApiService) {
    suspend fun getCrewMember(id: String): NetworkResponse<Any> {
        try {
            val response = api.getCrewMember(id)

            response?.let {
                return NetworkResult(it.body()!!)
            }
            return NetworkErrorResult(Exception("No data"))

        } catch (e: Exception) {
            return NetworkErrorResult(e)
        }
    }
}