import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PixivIllustrationApi {

    companion object {

        fun create(client: OkHttpClient): PixivIllustrationApi {
            val retrofit = Retrofit.Builder().apply {
                baseUrl("https://app-api.pixiv.net/v1/illust/")
                client(client)
            }.build()
            return retrofit.create(PixivIllustrationApi::class.java)
        }

    }

    @GET("detail")
    fun detailOf(@Query("illust_id") illustrationID: String): Call<ResponseBody>

}

interface PixivTestApi{

    companion object {

        fun create(client: OkHttpClient): PixivTestApi {
            val retrofit = Retrofit.Builder().apply {
                baseUrl("https://i.pximg.net/")
                client(client)
            }.build()
            return retrofit.create(PixivTestApi::class.java)
        }

    }

    @Headers("referer: https://www.pixiv.net/")
    @GET("img-original/img/{imgPath}")
    fun getImgOrigin(@Path("imgPath") imgPath: String): Call<ResponseBody>

}


