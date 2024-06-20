package trungmvph44499.fpoly.kot104_trungmvph44499.service


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import trungmvph44499.fpoly.kot104_trungmvph44499.model.MovieRequest
import trungmvph44499.fpoly.kot104_trungmvph44499.model.StatusResponse
import trungmvph44499.fpoly.kot104_trungmvph44499.response.ItemResponse

interface ItemService {
    @GET("Film")
    suspend fun getListFilms(): Response<List<ItemResponse>>

    @GET("Film/{id}")
    suspend fun getFilmDetail(@Path("id") id: String): Response<ItemResponse>

    @POST("Film")
    suspend fun addFilm(@Body filmRequest: MovieRequest): Response<StatusResponse>

    @PUT("Film/{id}")
    suspend fun updateFilm(
        @Path("id") id: String,
        @Body filmRequest: MovieRequest
    ): Response<StatusResponse>

    @DELETE("Film/{id}")
    suspend fun deleteFilm(@Path("id") id: String): Response<StatusResponse>
}
