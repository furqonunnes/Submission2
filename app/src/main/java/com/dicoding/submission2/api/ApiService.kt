package com.dicoding.submission2.api

import com.dicoding.submission2.BuildConfig
import com.dicoding.submission2.data.model.DetailUserResponse
import com.dicoding.submission2.data.model.User
import com.dicoding.submission2.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val token = BuildConfig.TOKEN

interface Api {
    @GET("search/users")
    @Headers(token)
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers(token)
    fun getUserDetail(
        @Path("username") username: String
    ): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    @Headers(token)
    fun getUserFollowers(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers(token)
    fun getUserFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}