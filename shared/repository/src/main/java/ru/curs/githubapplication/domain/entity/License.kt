package ru.curs.githubapplication.domain.entity

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class License(
    val html_url: String,
    val key: String,
    val name: String,
    val node_id: String,
    val spdx_id: String,
    val url: String
)