package br.com.steventung.redegram.domain.model

data class Post(
    val postId: Long,
    val authorPostId: String,
    val authorPostImage: Int,
    val authorPostName: String,
    val postImage: Int,
    val postDescription: String,
    val postDescriptionTranslated: String = "",
    val postDescriptionTranslateState: TranslateState = TranslateState.NotTranslated,
    val isPostDescriptionExpanded: Boolean = false,
    val postTotalLikes: Int,
    val postIsLiked: Boolean = false,
    val comments: List<Comment>
)
