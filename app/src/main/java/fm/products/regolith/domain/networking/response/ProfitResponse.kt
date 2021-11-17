package fm.products.regolith.domain.networking.response

import com.google.gson.annotations.SerializedName

data class ProfitResponse(
    @SerializedName("total")
    var total: Float,
    @SerializedName("raise")
    var raise: Float,
    @SerializedName("invest")
    var invest: Float,
    @SerializedName("price")
    var price: Float,
    @SerializedName("about")
    var about: String
)