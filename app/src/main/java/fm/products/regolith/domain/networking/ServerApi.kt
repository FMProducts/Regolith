package fm.products.regolith.domain.networking

import fm.products.regolith.domain.networking.response.BonusResponse
import fm.products.regolith.domain.networking.response.GradeResponse
import fm.products.regolith.domain.networking.response.ProfitResponse
import fm.products.regolith.domain.networking.response.RefillResponse
import retrofit2.http.GET

interface ServerApi {
    @GET("bonus")
    suspend fun bonus(): BonusResponse

    @GET("refill")
    suspend fun refill(): RefillResponse

    @GET("profit")
    suspend fun profit(): ProfitResponse

    @GET("grade")
    suspend fun grade(): GradeResponse
}