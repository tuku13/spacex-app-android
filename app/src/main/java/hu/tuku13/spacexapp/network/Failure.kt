package hu.tuku13.spacexapp.network

data class Failure(
    val time: Int = 0,
    val altitude: Int? = 0,
    val reason: String = ""
)
