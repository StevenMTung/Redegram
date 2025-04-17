package br.com.steventung.redegram.domain.model

import java.util.UUID

data class Comment(
    val commentId: String = UUID.randomUUID().toString(),
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