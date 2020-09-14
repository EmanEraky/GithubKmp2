package remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class GithubOwner(
    @SerialName("avatar_url") var avatar: String,
    @SerialName("login") var name: String
)

//@Serializable
//data class GithubOwner(val avatar_url: String, val login: String )