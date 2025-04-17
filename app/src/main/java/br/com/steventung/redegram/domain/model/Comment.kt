package br.com.steventung.redegram.domain.model

data class Comment(
    val commentId: Long,
    val authorId: String,
    val authorImage: Int,
    val authorName: String,
    val content: String,
    val translatedContent: String = "",
    val translateState: TranslateState = TranslateState.NotTranslated,
    val commentTotalLikes: Int = 0,
    val isCommentLiked: Boolean = false
)

sealed class TranslateState() {
    data object NotTranslated: TranslateState()
    data object IsTranslating: TranslateState()
    data object Translated: TranslateState()
}