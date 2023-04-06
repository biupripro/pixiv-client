import okhttp3.*
import java.io.File
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    println("Hello, my Pixiv Client")

    val client = OkHttpClient.Builder().apply {
        proxy(Proxy(Proxy.Type.SOCKS, InetSocketAddress("127.0.0.1", 63474)))
        readTimeout(5000, TimeUnit.MINUTES)
    }.build()
    println("http client on socks proxy created")

//    val api = PixivApi.create(client)
//    val resp = api.getImgOrigin("2023/04/05/07/00/17/106879191_p0.png").execute()
    val api = PixivIllustrationApi.create(client)
    val resp = api.detailOf("106902572").execute()
    println("http response got, code is [${resp.code()}]")

    if (resp.code() == 200) {
        val bytes = resp.body()?.bytes() ?: ByteArray(0)
        File("D:/tmp/zzz.png").apply {
            parentFile.mkdirs()
            writeBytes(bytes)
        }
    }

    println("bye")

}
