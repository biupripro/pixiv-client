import okhttp3.*
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

    val request = Request.Builder().apply {
        url("https://i.pximg.net/img-original/img/2023/04/05/17/00/06/106888963_p0.png")
    }.build()
    println("http request created")

    val resp = client.newCall(request).execute()
    println("http response got, code is [${resp.code}]")

    val n = 0

}
