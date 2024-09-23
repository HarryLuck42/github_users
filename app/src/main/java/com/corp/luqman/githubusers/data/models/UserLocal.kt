package com.corp.luqman.githubusers.data.models.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserLocal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int?,
    @ColumnInfo(name = "username")
    var username: String?,
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String?,
    @ColumnInfo(name = "events_url")
    var eventsUrl: String?,
    @ColumnInfo(name = "followers_url")
    var followersUrl: String?,
    @ColumnInfo(name = "following_url")
    var followingUrl: String?,
    @ColumnInfo(name = "gists_url")
    var gistsUrl: String?,
    @ColumnInfo(name = "gravatar_id")
    var gravatarId: String?,
    @ColumnInfo(name = "html_url")
    var htmlUrl: String?,
    @ColumnInfo(name = "node_id")
    var nodeId: String?,
    @ColumnInfo(name = "organizations_url")
    var organizationsUrl: String?,
    @ColumnInfo(name = "received_events_url")
    var receivedEventsUrl: String?,
    @ColumnInfo(name = "repos_url")
    var reposUrl: String?,
    @ColumnInfo(name = "site_admin")
    var siteAdmin: Boolean?,
    @ColumnInfo(name = "starred_url")
    var starredUrl: String?,
    @ColumnInfo(name = "subscriptions_url")
    var subscriptionsUrl: String?,
    @ColumnInfo(name = "type")
    var type: String?,
    @ColumnInfo(name = "url")
    var url: String?
)
