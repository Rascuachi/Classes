package com.ita.myapp.classes.data.model.network

import com.ita.myapp.classes.data.model.ServiceModel
import retrofit2.Response // Cambiado de okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("service")
    suspend fun getService(): Response<List<ServiceModel>>

    @GET("service/{id}")
    suspend fun getService(@Path("id") id: Int): Response<ServiceModel>

    @POST("service")
    suspend fun createService(@Body service: ServiceModel): Response<ServiceModel>

    @PUT("service/{id}")
    suspend fun updateService(@Path("id") id: Int, @Body service: ServiceModel): Response<ServiceModel>

    @DELETE("service/{id}")
    suspend fun deleteService(@Path("id") id: Int): Response<ServiceModel>
}
