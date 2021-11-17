package fm.products.regolith.domain.networking.response

import com.google.gson.annotations.SerializedName

data class RefillResponse(
    @SerializedName("total")
    var total: Float,
    @SerializedName("withdrawn")
    var withdrawn: Float,
    @SerializedName("refillUSD")
    var refillUSD: Float,
    @SerializedName("refillRUB")
    var refillRUB: Float,
    @SerializedName("about")
    var about: String
)