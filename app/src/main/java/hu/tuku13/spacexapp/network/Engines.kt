package hu.tuku13.spacexapp.network

data class Engines(
    val engine_loss_max: Any? = Any(),
    val isp: Isp = Isp(),
    val layout: String? = "",
    val number: Int = 0,
    val propellant_1: String = "",
    val propellant_2: String = "",
    val thrust_sea_level: ThrustSeaLevel = ThrustSeaLevel(),
    val thrust_to_weight: Double = 0.0,
    val thrust_vacuum: ThrustVacuum = ThrustVacuum(),
    val type: String = "",
    val version: String = ""
)