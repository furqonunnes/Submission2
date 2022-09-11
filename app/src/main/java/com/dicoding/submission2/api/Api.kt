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
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getUserFollowers(
        @Path("followers") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getUserFollowing(
        @Path("following") username: String
    ): Call<ArrayList<User>>
}