package jilnesta.com.testmvvm.data.remote

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import jilnesta.com.testmvvm.BASE_URL
import jilnesta.com.testmvvm.BuildConfig.DEBUG
import jilnesta.com.testmvvm.BuildConfig.VERSION_NAME
import jilnesta.com.testmvvm.data.local.LocalData
import jilnesta.com.testmvvm.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import jilnesta.com.testmvvm.data.remote.moshiFactories.MyStandardJsonAdapters
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val timeoutRead = 60L   //In seconds
private const val timeoutConnect = 60L   //In seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val jilPublicKey = "Jil-Public-Key"
private const val jilPublicKeyValue = "com.learningift.askbe.debug"
private const val version = "version"
private const val versionValue = "v1"
private const val appVersion = "App-Version"
private const val appVersionValue = VERSION_NAME
private const val jilToken = "Jil-Token"

@Singleton
class ServiceGenerator @Inject constructor(private val localRepository: LocalData) {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder()
            .header(contentType, contentTypeValue)
            .header(jilPublicKey, jilPublicKeyValue)
            .header(jilToken, localRepository.getUserToken().data.toString())
            .header(version, versionValue)
            .header(appVersion, appVersionValue)
            .method(original.method, original.body)
            .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply {
                level = if (DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }

            return loggingInterceptor
        }

    init {
        okHttpBuilder.connectTimeout(timeoutConnect, TimeUnit.SECONDS)
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.addNetworkInterceptor(StethoInterceptor())
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}