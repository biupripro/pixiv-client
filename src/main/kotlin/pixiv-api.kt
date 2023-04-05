import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path

interface PixivApi {

    companion object {

        fun create(client: OkHttpClient): PixivApi {
            val retrofit = Retrofit.Builder().apply {
                baseUrl("https://i.pximg.net/")
                client(client)
            }.build()

            val api = retrofit.create(PixivApi::class.java)
            return api
        }

    }

    @Headers("referer: https://www.pixiv.net/")
    @GET("img-original/img/{imgPath}")
    fun getImgOrigin(@Path("imgPath") imgPath: String): Call<ResponseBody>

}


