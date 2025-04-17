package br.com.steventung.redegram.ui.home

import br.com.steventung.redegram.R
import br.com.steventung.redegram.domain.model.Comment
import br.com.steventung.redegram.domain.model.Language
import br.com.steventung.redegram.domain.model.Post

data class HomeUiState(
    val postsList: List<Post> = emptyList(),
    val commentsList: List<Comment> = emptyList(),
    val isShowCommentSection: Boolean = false,
    val commentListPostId: Long = -1L,
    val postAuthorImage: Int = R.drawable.default_profile_picture,
    val commentText: String = "",
    val localLanguage: Language = Language(languageCode = "pt", languageName = "português")
)
