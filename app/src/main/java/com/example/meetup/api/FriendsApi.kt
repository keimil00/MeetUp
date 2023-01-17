package com.example.meetup.api

import com.example.meetup.authorization.HttpRoutes
import com.example.meetup.model.Friend
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface FriendsApi {

    @POST(HttpRoutes.ADD_FRIENDS)
    suspend fun addFriend(
        @Query("friendEmail") friendEmail: String
    )

    @GET(HttpRoutes.GET_FRIENDS)
    suspend fun getFriends(): List<Friend>
}

class MockFriendsApi(private var mockFriendsList: MutableList<Friend>) : FriendsApi {


    override suspend fun addFriend(friendEmail: String) {
        val newFriend = Friend(
            mockFriendsList[mockFriendsList.lastIndex].id + 1,
            "Jan",
            "Kowalski",
            friendEmail
        )
        mockFriendsList.add(newFriend)
    }

    override suspend fun getFriends(): List<Friend> {
        return mockFriendsList
    }
}