package com.roger.paging.model.entity

data class PeopleList(
    val responseCode: String,
    val responseMessage: String,
    val totalPage: String,
    val totalDataSize: String,
    val page: String,
    val pageDataSize: String,
    val responseData: List<People>,
) {
    data class People(
        val statistic_yyymm: String,
        val site_id: String,
        val village: String,
        val birth_total: String,
        val birth_total_m: String,
        val birth_total_f: String,
        val death_total: String,
        val death_m: String,
        val death_f: String,
        val marry_pair: String,
        val divorce_pair: String,
    )
}