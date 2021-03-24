package com.joe.taipeizoo.bean.animals

data class AnimalResult(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<AnimalDetailResult>
)