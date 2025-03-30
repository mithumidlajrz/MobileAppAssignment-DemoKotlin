package com.example.mobileappassignment_tagntrac.model


data class MockUser(val email: String, val password: String)

// Mock user data for demonstration purposes
val mockUsers = listOf(
    MockUser("user1@example.com", "password123"),
    MockUser("user2@example.com", "password456"),
    MockUser("user3@example.com", "password789")
)

val workspaceIdMockUsers = listOf("W123", "W456", "W789")

