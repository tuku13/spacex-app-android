package hu.tuku13.spacexapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceXApiService {

    @GET("/v4/launches")
    suspend fun getLaunches(): Response<List<Launch>>?

    @GET("/v4/launches/{id}")
    suspend fun getLaunch(@Path("id") id: String) : Response<Launch>?

    @GET("/v4/launches/latest")
    suspend fun getLatestLaunch(): Response<Launch>?

    @GET("/v4/rockets")
    suspend fun getRockets() : Response<List<Rocket>>?

    @GET("/v4/rockets/{id}")
    suspend fun getRocket(@Path("id") id: String) : Response<Rocket>?

    @GET("/v4/roadster")
    suspend fun getRoadster(): Response<Roadster>?

    @GET("/v4/payloads")
    suspend fun getPayloads(): Response<List<Payload>>?

    @GET("/v4/payloads/{id}")
    suspend fun getPayload(@Path("id") id: String): Response<Payload>?

    @GET("/v4/launchpads")
    suspend fun getLaunchPads(): Response<List<LaunchPad>>?

    @GET("/v4/launchpads/{id}")
    suspend fun getLaunchPad(@Path("id") id: String): Response<LaunchPad>?

    @GET("/v4/crew/{id}")
    suspend fun getCrewMember(@Path("id") id: String): Response<CrewMember>?

    companion object {
        private const val BASE_URL = "https://api.spacexdata.com"

        fun create(): SpaceXApiService {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(SpaceXApiService::class.java)
        }

    }
}
