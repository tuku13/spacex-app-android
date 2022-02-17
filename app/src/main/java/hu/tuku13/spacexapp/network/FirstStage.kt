package hu.tuku13.spacexapp.network

data class FirstStage(
    val burn_time_sec: Int? = 0,
    val engines: Int = 0,
    val fuel_amount_tons: Double = 0.0,
    val reusable: Boolean = false,
    val thrust_sea_level: ThrustSeaLevel = ThrustSeaLevel(),
    val thrust_vacuum: ThrustVacuum = ThrustVacuum()
)