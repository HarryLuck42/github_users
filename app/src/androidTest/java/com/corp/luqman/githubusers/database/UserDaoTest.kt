package com.corp.luqman.githubusers.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.corp.luqman.githubusers.data.database.UserDao
import com.corp.luqman.githubusers.data.database.UserDatabase
import com.corp.luqman.githubusers.data.models.response.UserLocal
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            UserDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.userDao()
    }

    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun testInsertUser() = runTest {
        val user = UserLocal(
            id = 1,
            username = "mojombo",
            nodeId = "MDQ6VXNlcjE=",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatarId = "",
            url = "https://api.github.com/users/mojombo",
            htmlUrl = "https://github.com/mojombo",
            followersUrl = "https://api.github.com/users/mojombo/followers",
            followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
            gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
            starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
            organizationsUrl = "https://api.github.com/users/mojombo/orgs",
            reposUrl = "https://api.github.com/users/mojombo/repos",
            eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
            receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
            type = "User",
            siteAdmin = false,
            name = "Tom Preston-Werner",
            company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
            blog = "http://tom.preston-werner.com",
            location = "San Francisco",
            email = "tom@mojombo.com",
            bio = null,
            twitterUsername = "mojombo",
            publicRepos = 66,
            publicGists = 62,
            followers = 24017,
            following = 11,
            createdAt = "2007-10-20T05:24:19Z",
            updatedAt = "2024-09-04T23:40:30Z"
        )
        dao.insertUser(user)
        val users = dao.getUsersById(user.id) ?: listOf()
        assertThat(users).isNotEmpty()
    }

    @Test
    fun testDeleteUser() = runTest {
        val user = UserLocal(
            id = 1,
            username = "mojombo",
            nodeId = "MDQ6VXNlcjE=",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatarId = "",
            url = "https://api.github.com/users/mojombo",
            htmlUrl = "https://github.com/mojombo",
            followersUrl = "https://api.github.com/users/mojombo/followers",
            followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
            gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
            starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
            organizationsUrl = "https://api.github.com/users/mojombo/orgs",
            reposUrl = "https://api.github.com/users/mojombo/repos",
            eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
            receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
            type = "User",
            siteAdmin = false,
            name = "Tom Preston-Werner",
            company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
            blog = "http://tom.preston-werner.com",
            location = "San Francisco",
            email = "tom@mojombo.com",
            bio = null,
            twitterUsername = "mojombo",
            publicRepos = 66,
            publicGists = 62,
            followers = 24017,
            following = 11,
            createdAt = "2007-10-20T05:24:19Z",
            updatedAt = "2024-09-04T23:40:30Z"
        )
        dao.insertUser(user)
        dao.deleteUser(user)
        val users = dao.getUsers()
        assertThat(users).doesNotContain(users)
    }


    @Test
    fun testSearchUsersNotFound() = runTest {
        val users = mutableListOf(
            UserLocal(
                id = 11,
                username = "bella",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Bella Safira",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 12,
                username = "jirayut",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Jirayut",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 13,
                username = "abdur",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Abdur Malik",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 14,
                username = "jonathan",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Jonathan Christy",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
        )
        for (user in users) {
            dao.insertUser(user)
        }
        val results = dao.searchUsers("aqua")
        assertThat(results).containsNoneIn(users)
    }

    @Test
    fun testSearchUserFound() = runTest {
        val users = mutableListOf(
            UserLocal(
                id = 11,
                username = "bella",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Bella Safira",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 12,
                username = "jirayut",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Jirayut",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 13,
                username = "abdur",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Abdur Malik",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 14,
                username = "jonathan",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Jonathan Christy",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
        )
        for (user in users) {
            dao.insertUser(user)
        }
        val results = dao.searchUsers("jirayut")
        assertThat(results).isNotEmpty()
    }

    @Test
    fun testGetUsersByIdNotFound() = runTest {
        val users = mutableListOf(
            UserLocal(
                id = 11,
                username = "bella",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Bella Safira",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 12,
                username = "jirayut",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Jirayut",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 13,
                username = "abdur",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Abdur Malik",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 14,
                username = "jonathan",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Jonathan Christy",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
        )
        for (user in users) {
            dao.insertUser(user)
        }
        val results = dao.getUsersById(22)
        assertThat(results).containsNoneIn(users)
    }

    @Test
    fun testGetUserByIdFound() = runTest {
        val users = mutableListOf(
            UserLocal(
                id = 11,
                username = "bella",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Bella Safira",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 12,
                username = "jirayut",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Jirayut",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 13,
                username = "abdur",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Abdur Malik",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
            UserLocal(
                id = 14,
                username = "jonathan",
                nodeId = "MDQ6VXNlcjE=",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
                gravatarId = "",
                url = "https://api.github.com/users/mojombo",
                htmlUrl = "https://github.com/mojombo",
                followersUrl = "https://api.github.com/users/mojombo/followers",
                followingUrl = "https://api.github.com/users/mojombo/following{/other_user}",
                gistsUrl = "https://api.github.com/users/mojombo/gists{/gist_id}",
                starredUrl = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
                subscriptionsUrl = "https://api.github.com/users/mojombo/subscriptions",
                organizationsUrl = "https://api.github.com/users/mojombo/orgs",
                reposUrl = "https://api.github.com/users/mojombo/repos",
                eventsUrl = "https://api.github.com/users/mojombo/events{/privacy}",
                receivedEventsUrl = "https://api.github.com/users/mojombo/received_events",
                type = "User",
                siteAdmin = false,
                name = "Jonathan Christy",
                company = "@chatterbugapp, @redwoodjs, @preston-werner-ventures ",
                blog = "http://tom.preston-werner.com",
                location = "San Francisco",
                email = "tom@mojombo.com",
                bio = null,
                twitterUsername = "mojombo",
                publicRepos = 66,
                publicGists = 62,
                followers = 24017,
                following = 11,
                createdAt = "2007-10-20T05:24:19Z",
                updatedAt = "2024-09-04T23:40:30Z"
            ),
        )
        for (user in users) {
            dao.insertUser(user)
        }
        val results = dao.getUsersById(12)
        assertThat(results).isNotEmpty()
    }
}