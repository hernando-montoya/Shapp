import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

class Shapp(elements: Collection<Dev>) : ArrayList<Dev>(elements) {
    fun toJson() = klaxon.toJsonString(this)

    companion object {
        fun fromJson(json: String) = Shapp(klaxon.parseArray<Dev>(json)!!)
    }
}

data class Dev(
    val name: String
)
