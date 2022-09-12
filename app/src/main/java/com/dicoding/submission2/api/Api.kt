package com.dicoding.submission2.api

import com.dicoding.submission2.data.model.DetailUserResponse
import com.dicoding.submission2.data.model.User
import com.dicoding.submission2.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: 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")
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: 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")
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}