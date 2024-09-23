package com.corp.luqman.githubusers.data.models.response

import com.squareup.moshi.Json
import java.io.Serializable

data class User(
    @field:Json(name = "id")
    var id: Int?,
    @field:Json(name = "login")
    var username: String?,
    @field:Json(name = "avatar_url")
    var avatarUrl: String?,
    @field:Json(name = "events_url")
    var eventsUrl: String?,
    @field:Json(name = "followers_url")
    var followersUrl: String?,
    @field:Json(name = "following_url")
    var followingUrl: String?,
    @field:Json(name = "gists_url")
    var gistsUrl: String?,
    @field:Json(name = "gravatar_id")
    var gravatarId: String?,
    @field:Json(name = "html_url")
    var htmlUrl: String?,
    @field:Json(name = "node_id")
    var nodeId: String?,
    @field:Json(name = "organizations_url")
    var organizationsUrl: String?,
    @field:Json(name = "received_events_url")
    var receivedEventsUrl: String?,
    @field:Json(name = "repos_url")
    var reposUrl: String?,
    @field:Json(name = "site_admin")
    var siteAdmin: Boolean?,
    @field:Json(name = "starred_url")
    var starredUrl: String?,
    @field:Json(name = "subscriptions_url")
    var subscriptionsUrl: String?,
    @field:Json(name = "type")
    var type: String?,
    @field:Json(name = "url")
    var url: String?
): Serializable{
    fun convert(): UserLocal = UserLocal(
        id = id ?: 0,
        username = username,
        avatarUrl = avatarUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        gravatarId = gravatarId,
        htmlUrl = htmlUrl,
        nodeId = nodeId,
        organizationsUrl = organizationsUrl,
        receivedEventsUrl = receivedEventsUrl,
        reposUrl = reposUrl,
        siteAdmin = siteAdmin,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        type = type,
        url = url)
}

data class UserDetail(
    @field:Json(name = "id")
    var id: Int?,
    @field:Json(name = "login")
    var username: String?,
    @field:Json(name = "name")
    var name: String?,
    @field:Json(name = "avatar_url")
    var avatarUrl: String?,
    @field:Json(name = "bio")
    var bio: String?,
    @field:Json(name = "blog")
    var blog: String?,
    @field:Json(name = "company")
    var company: String?,
    @field:Json(name = "email")
    var email: String?,
    @field:Json(name = "events_url")
    var eventsUrl: String?,
    @field:Json(name = "followers")
    var followers: Int?,
    @field:Json(name = "followers_url")
    var followersUrl: String?,
    @field:Json(name = "following")
    var following: Int?,
    @field:Json(name = "following_url")
    var followingUrl: String?,
    @field:Json(name = "gists_url")
    var gistsUrl: String?,
    @field:Json(name = "gravatar_id")
    var gravatarId: String?,
    @field:Json(name = "html_url")
    var htmlUrl: String?,
    @field:Json(name = "location")
    var location: String?,
    @field:Json(name = "node_id")
    var nodeId: String?,
    @field:Json(name = "organizations_url")
    var organizationsUrl: String?,
    @field:Json(name = "public_gists")
    var publicGists: Int?,
    @field:Json(name = "public_repos")
    var publicRepos: Int?,
    @field:Json(name = "received_events_url")
    var receivedEventsUrl: String?,
    @field:Json(name = "repos_url")
    var reposUrl: String?,
    @field:Json(name = "site_admin")
    var siteAdmin: Boolean?,
    @field:Json(name = "starred_url")
    var starredUrl: String?,
    @field:Json(name = "subscriptions_url")
    var subscriptionsUrl: String?,
    @field:Json(name = "twitter_username")
    var twitterUsername: String?,
    @field:Json(name = "type")
    var type: String?,
    @field:Json(name = "url")
    var url: String?,
    @field:Json(name = "created_at")
    var createdAt: String?,
    @field:Json(name = "updated_at")
    var updatedAt: String?
): Serializable{
    fun convert(): UserLocal = UserLocal(
        id = id ?: 0,
        username = username,
        name = name,
        avatarUrl = avatarUrl,
        bio =  bio,
        blog = blog,
        company = company,
        email = email,
        eventsUrl = eventsUrl,
        followers = followers,
        followersUrl = followersUrl,
        following = following,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        gravatarId = gravatarId,
        htmlUrl = htmlUrl,
        location = location,
        nodeId = nodeId,
        organizationsUrl = organizationsUrl,
        publicGists = publicGists,
        publicRepos = publicRepos,
        receivedEventsUrl = receivedEventsUrl,
        reposUrl = reposUrl,
        siteAdmin = siteAdmin,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        twitterUsername = twitterUsername,
        type = type,
        url = url,
        createdAt = createdAt,
        updatedAt = updatedAt)
}
