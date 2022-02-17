package hu.tuku13.spacexapp.network

data class SecondStage(
    val burn_time_sec: Int? =0,
    val engines: Int = 0,
    val fuel_amount_tons: Double = 0.0,
    val payloads: Payloads = Payloads(),
    val reusable: Boolean = false,
    val thrust: Thrust = Thrust()
)