import okhttp3.*
import java.io.File
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.ProxySelector
import java.net.URI
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    println("Hello, my Pixiv Client")

    val client = OkHttpClient.Builder().apply {
        proxy(Proxy(Proxy.Type.SOCKS, InetSocketAddress("127.0.0.1", 63474)))
        readTimeout(5000, TimeUnit.MINUTES)
    }.build()
    println("http client on socks proxy created")

    val imgPath = "img-original/img/2023/04/05/07/00/17/106879191_p0.png"

    val request = Request.Builder().apply {
        url("https://i.pximg.net/${imgPath}")
        addHeader("referer", "https://www.pixiv.net/")
    }.build()
    println("http request created [${request.headers}]")

    val resp = client.newCall(request).execute()
    println("http response got, code is [${resp.code}]")

    if (resp.code == 200) {
        val bytes = resp.body?.bytes() ?: ByteArray(0)
        File("D:/tmp/zzz.png").apply {
            parentFile.mkdirs()
            writeBytes(bytes)
        }
    }

    println("bye")

}
