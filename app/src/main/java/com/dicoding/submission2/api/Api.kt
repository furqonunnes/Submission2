package com.dicoding.submission2.api

import com.dicoding.submission2.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>
}