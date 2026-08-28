package com.clickbus.app.data.api

import com.clickbus.app.data.model.AuthResponse
import com.clickbus.app.data.model.LoginRequest
import com.clickbus.app.data.model.RegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<AuthResponse>
}
