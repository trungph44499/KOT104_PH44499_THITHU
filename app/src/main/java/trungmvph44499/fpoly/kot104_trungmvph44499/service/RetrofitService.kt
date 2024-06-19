package trungmvph44499.fpoly.kot104_trungmvph44499.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class RetrofitService {
    private val retrofit: Retrofit = Retrofit.Builder()
        //.baseUrl("http://10.0.2.2:3000/")
        .baseUrl("https://65df0c77ff5e305f32a149da.mockapi.io/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val movieService: MovieService by lazy {
        retrofit.create(MovieService::class.java)
    }
}