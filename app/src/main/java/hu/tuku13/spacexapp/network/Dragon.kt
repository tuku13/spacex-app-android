package hu.tuku13.spacexapp.network

data class Dragon(
    val capsule: String? = "",
    val flight_time_sec: Int = 0,
    val land_landing: Boolean = false,
    val manifest: Any? = null,
    val mass_returned_kg: Any? = null,
    val mass_returned_lbs: Any? = null,
    val water_landing: Boolean = false,
)