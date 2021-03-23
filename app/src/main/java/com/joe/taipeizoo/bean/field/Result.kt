package com.joe.taipeizoo.bean.field

data class Result(
    val limit: Int,
    val offset: Int,
    val count: Int,
    val sort: String,
    val results: List<ResultX>
)