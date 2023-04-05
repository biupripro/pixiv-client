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

    val request = Request.Builder().apply {
        url("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png")
//        addHeader("Referrer-Policy", "strict-origin-when-cross-origin")
//        addHeader(":authority", "i.pximg.net")
//        addHeader(":method", "GET")
//        addHeader(":path", "/img-original/img/2023/04/05/17/00/06/106888963_p0.png")
//        addHeader(":scheme", "https")
    }.build()
    println("http request created [${request.headers}]")

    val resp = client.newCall(request).execute()
    println("http response got, code is [${resp.code}]")

    val bytes = resp.body?.bytes() ?: ByteArray(0)
    File("D:/tmp/zzz.png").apply {
        parentFile.mkdirs()
        writeBytes(bytes)
    }

    val n = 0

}
