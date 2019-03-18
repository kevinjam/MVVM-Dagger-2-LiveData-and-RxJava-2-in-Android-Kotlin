package com.kevinjanvier.mvvm.model

data class Test(
    val authentication: String,
    val id: Int,
    val role: List<Role>
)