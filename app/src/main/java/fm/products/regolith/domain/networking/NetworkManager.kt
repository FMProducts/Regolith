package fm.products.regolith.domain.networking

import fm.products.regolith.domain.networking.response.BonusResponse
import fm.products.regolith.domain.networking.response.GradeResponse
import fm.products.regolith.domain.networking.response.ProfitResponse
import fm.products.regolith.domain.networking.response.RefillResponse
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class NetworkManager(private val serverApi: ServerApi) {

    suspend fun loadGrade(): GradeResponse?{
        return try {
            serverApi.grade()
        } catch (e: IOException){
            null
        }catch (e: HttpException){
            null
        }
    }

    suspend fun loadBonus(): BonusResponse?{
        return try {
            serverApi.bonus()
        } catch (e: IOException){
            null
        }catch (e: HttpException){
            null
        }
    }

    suspend fun loadProfit(): ProfitResponse? {
        return try {
            serverApi.profit()
        } catch (e: IOException){
            null
        }catch (e: HttpException){
            null
        }
    }

    suspend fun loadRefill(): RefillResponse? {
        return try {
            serverApi.refill()
        } catch (e: IOException){
            null
        }catch (e: HttpException){
            null
        }
    }


    companion object{

        const val BASE_URL = "https://sandbox.skill-branch.ru/dashboards/"
        const val AUTH_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYwZTBhNzU2NTBlZmYwM2M4NGU3ZTA1YiIsInJvbGUiOiJzdXBlcmFkbWluIiwib3duZXIiOiJzdXBlcmFkbWluIiwiaWF0IjoxNjI1NDA1NDA1LCJleHAiOjE2MjYwMTAyMDV9.B6Y2JXfF28g62QdQCf577L3sLMcAOY95RSKhcCGWrXU"

        fun createClient(): NetworkManager {
            val client = OkHttpClient.Builder()
                .addNetworkInterceptor {
                    val request = it.request()
                    val newRequest = request.newBuilder()
                        .addHeader("Authorization", AUTH_TOKEN).build()
                    it.proceed(newRequest)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

            val api = retrofit.create(ServerApi::class.java)
            return NetworkManager(api)
        }
    }
}