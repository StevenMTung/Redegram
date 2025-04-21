package br.com.steventung.redegram.ui.home

import br.com.steventung.redegram.domain.model.Comment
import br.com.steventung.redegram.domain.model.Language
import br.com.steventung.redegram.domain.model.Post
import br.com.steventung.redegram.domain.model.User

data class HomeUiState(
    val postsList: List<Post> = emptyList(),
    val commentsList: List<Comment> = emptyList(),
    val isShowCommentSection: Boolean = false,
    val commentListPostId: Long? = null,
    val userProfile: User? = null,
    val commentText: String = "",
    val localLanguage: Language = Language(languageCode = "pt", languageName = "português"),
    val isScreenRefreshing: Boolean = false,
)
