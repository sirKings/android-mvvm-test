package co.king.chasetest.util

fun getIdFromUrl(url: String): Int {
    //"https://swapi.dev/api/planets/2/"
    val pattern = Regex("""/(\d+)/[^/]*$""")
    val matchResult = pattern.find(url)
    return if (matchResult != null) {
        val lastGroup = matchResult.groupValues.lastOrNull()
        lastGroup?.toInt() ?: 0
    } else {
        0
    }
}