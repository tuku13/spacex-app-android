package hu.tuku13.spacexapp.network

data class CrewMember(
    val agency: String = "",
    val id: String = "",
    val image: String = "",
    val launches: List<String> = listOf(),
    val name: String = "",
    val status: String = "",
    val wikipedia: String = ""
)