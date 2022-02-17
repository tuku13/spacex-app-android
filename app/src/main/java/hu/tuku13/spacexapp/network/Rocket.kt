package hu.tuku13.spacexapp.network

data class Rocket(
    val active: Boolean = false,
    val boosters: Int = 0,
    val company: String = "",
    val cost_per_launch: Int = 0,
    val country: String = "",
    val description: String = "",
    val diameter: Diameter = Diameter(),
    val engines: Engines = Engines(),
    val first_flight: String = "",
    val first_stage: FirstStage = FirstStage(),
    val flickr_images: List<String> = listOf(),
    val height: Height = Height(),
    val id: String = "",
    val landing_legs: LandingLegs = LandingLegs(),
    val mass: Mass = Mass(),
    val name: String = "",
    val payload_weights: List<PayloadWeight> = listOf(),
    val second_stage: SecondStage = SecondStage(),
    val stages: Int = 0,
    val success_rate_pct: Int = 0,
    val type: String = "",
    val wikipedia: String = ""
)