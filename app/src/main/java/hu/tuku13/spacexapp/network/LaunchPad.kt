package hu.tuku13.spacexapp.network

data class LaunchPad(
    val details: String = "",
    val full_name: String = "",
    val id: String = "",
    val images: Images = Images(),
    val landing_attempts: Int = 0,
    val landing_successes: Int = 0,
    val latitude: Double = 0.0,
    val launches: List<String> = listOf(),
    val locality: String = "",
    val longitude: Double = 0.0,
    val name: String = "",
    val region: String = "",
    val status: String = "",
    val type: String = "",
    val wikipedia: String = ""
)