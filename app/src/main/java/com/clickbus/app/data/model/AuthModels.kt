package com.clickbus.app.data.model

data class LoginRequest(
    val email: String,
    val senhaHash: String
)

data class RegisterRequest(
    val nome: String,
    val email: String,
    val cpf: String,
    val senhaHash: String,
    val necessitaAcessibilidade: Boolean
)

data class AuthResponse(
    val id: Int,
    val nome: String,
    val email: String,
    val necessitaAcessibilidade: Boolean,
    val token: String? = null
)
