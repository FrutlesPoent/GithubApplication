package ru.curs.githubapplication.domain.entity

data class RequiredStatusChecks(
    val contexts: List<String>,
    val enforcement_level: String
)