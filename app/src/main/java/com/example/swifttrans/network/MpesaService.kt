package com.example.swifttrans.network

//import com.example.swifttrans.BuildConfig
//import com.example.swifttrans.api.MpesaService
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.Credentials
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        val loggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        return OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor { chain ->
//                val request = chain.request().newBuilder()
//                    .header(
//                        "Authorization",
//                        Credentials.basic(BuildConfig.MPESA_CONSUMER_KEY, BuildConfig.MPESA_CONSUMER_SECRET)
//                    )
//                    .build()
//                chain.proceed(request)
//            }
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl("https://sandbox.safaricom.co.ke/") // Use production URL for live: https://api.safaricom.co.ke/
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideMpesaService(retrofit: Retrofit): MpesaService {
//        return retrofit.create(MpesaService::class.java)
//    }
//}