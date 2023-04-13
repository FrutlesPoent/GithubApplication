package ru.curs.githubapplication.domain.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)