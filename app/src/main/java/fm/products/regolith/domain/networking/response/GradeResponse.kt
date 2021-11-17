package fm.products.regolith.domain.networking.response

import com.google.gson.annotations.SerializedName


data class GradeResponse(
    @SerializedName("total")
    var total: Float,
    @SerializedName("raise")
    var raise: Float,
    @SerializedName("stock")
    var stock: Float,
    @SerializedName("balanceUSD")
    var balanceUDS: Float,
    @SerializedName("balanceRUB")
    var balanceRUB: Float,
    @SerializedName("about")
    var about: String
)