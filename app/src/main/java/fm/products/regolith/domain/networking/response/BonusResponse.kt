package fm.products.regolith.domain.networking.response

import com.google.gson.annotations.SerializedName

data class BonusResponse(
    @SerializedName("totalRUB")
    var totalRUB: Float,
    @SerializedName("totalUSD")
    var totalUSD: Float,
    @SerializedName("team")
    var team: Int,
    @SerializedName("refillUSD")
    var refillUSD: Float,
    @SerializedName("refillRUB")
    var refillRUB: Float,
    @SerializedName("about")
    var about: String
)