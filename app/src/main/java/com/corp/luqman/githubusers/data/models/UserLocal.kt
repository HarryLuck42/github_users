package com.corp.luqman.githubusers.data.models.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "users")
data class UserLocal(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,
    @ColumnInfo(name = "username")
    var username: String? = "",
    @ColumnInfo(name = "name")
    var name: String? = "",
    @ColumnInfo(name = "avatar_url")
    var avatarUrl: String? = "",
    @ColumnInfo(name = "bio")
    var bio: String? = "",
    @ColumnInfo(name = "blog")
    var blog: String? = "",
    @ColumnInfo(name = "company")
    var company: String? = "",
    @ColumnInfo(name = "email")
    var email: String? = "",
    @ColumnInfo(name = "events_url")
    var eventsUrl: String? = "",
    @ColumnInfo(name = "followers")
    var followers: Int? = 0,
    @ColumnInfo(name = "followers_url")
    var followersUrl: String? = "",
    @ColumnInfo(name = "following")
    var following: Int? = 0,
    @ColumnInfo(name = "following_url")
    var followingUrl: String? = "",
    @ColumnInfo(name = "gists_url")
    var gistsUrl: String? = "",
    @ColumnInfo(name = "gravatar_id")
    var gravatarId: String? = "",
    @ColumnInfo(name = "html_url")
    var htmlUrl: String? = "",
    @ColumnInfo(name = "location")
    var location: String? = "",
    @ColumnInfo(name = "node_id")
    var nodeId: String? = "",
    @ColumnInfo(name = "organizations_url")
    var organizationsUrl: String? = "",
    @ColumnInfo(name = "public_gists")
    var publicGists: Int? = 0,
    @ColumnInfo(name = "public_repos")
    var publicRepos: Int? = 0,
    @ColumnInfo(name = "received_events_url")
    var receivedEventsUrl: String? = "",
    @ColumnInfo(name = "repos_url")
    var reposUrl: String? = "",
    @ColumnInfo(name = "site_admin")
    var siteAdmin: Boolean? = false,
    @ColumnInfo(name = "starred_url")
    var starredUrl: String? = "",
    @ColumnInfo(name = "subscriptions_url")
    var subscriptionsUrl: String? = "",
    @ColumnInfo(name = "twitter_username")
    var twitterUsername: String? = "",
    @ColumnInfo(name = "type")
    var type: String? = "",
    @ColumnInfo(name = "url")
    var url: String? = "",
    @ColumnInfo(name = "created_at")
    var createdAt: String? = "",
    @ColumnInfo(name = "updated_at")
    var updatedAt: String? = ""
)

object ConverterListInt {

    @TypeConverter
    fun fromListIntToString(intList: MutableList<Int>): String = intList.toString()

    @TypeConverter
    fun toListIntFromString(stringList: String): MutableList<Int> {
        val result = ArrayList<Int>()
        val split = stringList.replace("[", "").replace("]", "").replace(" ", "").split(",")
        for (n in split) {
            try {
                result.add(n.toInt())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return result
    }
}
