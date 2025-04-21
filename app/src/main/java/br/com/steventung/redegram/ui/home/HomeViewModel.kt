package br.com.steventung.redegram.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.steventung.redegram.data.repository.PostRepository
import br.com.steventung.redegram.domain.model.Comment
import br.com.steventung.redegram.domain.model.Language
import br.com.steventung.redegram.domain.model.Post
import br.com.steventung.redegram.domain.model.TranslateState
import br.com.steventung.redegram.mlkit.TextTranslate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val textTranslate: TextTranslate
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getUserProfile()
        loadPostsList()
        identifyLocalLanguage()
    }

    private fun getUserProfile() {
        viewModelScope.launch {
            postRepository.getUserProfile().collect { user ->
                _uiState.update { it.copy(userProfile = user) }
            }
        }
    }

    private fun loadPostsList() {
        viewModelScope.launch {
            postRepository.getPosts().collect { postsList ->
                _uiState.update { it.copy(postsList = postsList) }
            }
        }
    }

    private fun identifyLocalLanguage() {
        val languageCode = Locale.getDefault().language
        val languageName = Locale.getDefault().displayLanguage
        _uiState.update {
            it.copy(
                localLanguage = Language(
                    languageName = languageName,
                    languageCode = languageCode
                )
            )
        }
    }

    fun setPostDescriptionExpanded(postId: Long, isExpanded: Boolean) {
        val postsList = _uiState.value.postsList.map { post ->
            if (post.postId == postId) {
                post.copy(isPostDescriptionExpanded = !isExpanded)
            } else post
        }
        _uiState.update { it.copy(postsList = postsList) }
    }

    fun setPostLike(selectedPost: Post) {
        postRepository.setPostLike(selectedPost)
        val postsList = _uiState.value.postsList.toMutableList()
        postsList.indexOfFirst { it.postId == selectedPost.postId }
            .takeIf { it != -1 }?.let { index ->
                val updatedPost = postsList[index].let {
                    it.copy(
                        postIsLiked = !it.postIsLiked,
                        postTotalLikes = if (it.postIsLiked) it.postTotalLikes - 1 else it.postTotalLikes + 1
                    )
                }
                postsList[index] = updatedPost
            }
        _uiState.update { it.copy(postsList = postsList) }
    }

    fun loadPostComments(postId: Long) {
        viewModelScope.launch {
            postRepository.getPostById(postId).collect { post ->
                post?.let { selectedPost ->
                    _uiState.update {
                        it.copy(
                            commentsList = selectedPost.comments,
                            isShowCommentSection = true,
                            commentListPostId = selectedPost.postId,
                        )
                    }
                }
            }
        }
    }

    fun hideCommentsSection() {
        _uiState.update {
            it.copy(
                isShowCommentSection = false,
                commentListPostId = null,
                commentText = ""
            )
        }
    }

    fun setCommentLike(commentId: String) {
        val commentsList = _uiState.value.commentsList.toMutableList()
        val index = commentsList.indexOfFirst { it.commentId == commentId }
        if (index != -1) {
            val currentComment = commentsList[index]
            val updatedComment = currentComment.copy(
                isCommentLiked = !currentComment.isCommentLiked,
                commentTotalLikes = if (currentComment.isCommentLiked) {
                    currentComment.commentTotalLikes - 1
                } else {
                    currentComment.commentTotalLikes + 1
                }
            )
            commentsList[index] = updatedComment
        }
        _uiState.update { it.copy(commentsList = commentsList) }

        _uiState.value.commentListPostId?.let {
            postRepository.setCommentLike(commentId = commentId, postId = it)
        }
    }

    fun commentTextUpdate(commentText: String) {
        _uiState.update { it.copy(commentText = commentText) }
    }

    fun commentTranslation(selectedComment: Comment) {
        setCommentTranslationState(selectedComment, TranslateState.IsTranslating)
        identifyLanguageAndTranslate(
            text = selectedComment.content,
            onTranslateSuccess = { translatedComment ->
                val commentsList = _uiState.value.commentsList.toMutableList()
                val index = commentsList.indexOfFirst { it.commentId == selectedComment.commentId }
                if (index != -1) {
                    val currentComment = commentsList[index]
                    val updatedComment = currentComment.copy(
                        translatedContent = translatedComment,
                        translateState = TranslateState.Translated
                    )
                    commentsList[index] = updatedComment
                }
                _uiState.update { it.copy(commentsList = commentsList) }
            }
        )
    }

    fun setCommentTranslationState(selectedComment: Comment, translationState: TranslateState) {
        val commentsList = _uiState.value.commentsList.toMutableList()
        val index = commentsList.indexOfFirst { it.commentId == selectedComment.commentId }
        if (index != -1) {
            val currentComment = commentsList[index]
            val updatedComment = currentComment.copy(
                translateState = translationState
            )
            commentsList[index] = updatedComment
        }
        _uiState.update { it.copy(commentsList = commentsList) }
    }

    fun postDescriptionTranslation(selectedPost: Post) {
        setPostDescriptionTranslationState(
            selectedPost = selectedPost,
            translationState = TranslateState.IsTranslating
        )
        identifyLanguageAndTranslate(
            text = selectedPost.postDescription,
            onTranslateSuccess = { translatedDescription ->
                val postsList = _uiState.value.postsList.toMutableList()
                val index = postsList.indexOfFirst { it.postId == selectedPost.postId }
                if (index != -1) {
                    val currentPost = postsList[index]
                    val updatedPost = currentPost.copy(
                        postDescriptionTranslated = translatedDescription,
                        postDescriptionTranslateState = TranslateState.Translated
                    )
                    postsList[index] = updatedPost
                }
                _uiState.update { it.copy(postsList = postsList) }
            }
        )
    }

    fun setPostDescriptionTranslationState(
        selectedPost: Post,
        translationState: TranslateState
    ) {
        val postsList = _uiState.value.postsList.toMutableList()
        val index = postsList.indexOfFirst { it.postId == selectedPost.postId }
        if (index != -1) {
            val currentPost = postsList[index]
            val updatedPost = currentPost.copy(
                postDescriptionTranslateState = translationState
            )
            postsList[index] = updatedPost
        }
        _uiState.update { it.copy(postsList = postsList) }
    }

    private fun identifyLanguageAndTranslate(
        text: String,
        onTranslateSuccess: (String) -> Unit = {}
    ) {
        textTranslate.identifyLanguage(
            text = text,
            onSuccess = { identifiedLanguage ->
                translateText(
                    text = text,
                    sourceLanguage = identifiedLanguage.languageCode,
                    targetLanguage = _uiState.value.localLanguage.languageCode,
                    onTranslateSuccess = onTranslateSuccess
                )
            },
            onFailure = {
                Log.i("HomeViewModel", "Falha na identificação do idioma")
            }
        )
    }

    private fun translateText(
        text: String,
        sourceLanguage: String,
        targetLanguage: String,
        onTranslateSuccess: (String) -> Unit = {}
    ) {
        textTranslate.translateText(
            text = text,
            sourceLanguage = sourceLanguage,
            targetLanguage = targetLanguage,
            onSuccess = { translatedText ->
                onTranslateSuccess(translatedText)
            },
            onFailure = {
                Log.i("HomeViewModel", "Falha na tradução")
            }
        )
    }

    fun createComment() {
        _uiState.value.userProfile?.let { user ->
            val newComment = Comment(
                authorId = user.userId,
                authorName = user.userName,
                authorImage = user.userImage,
                content = _uiState.value.commentText
            )
            val updatedPostCommentsList = _uiState.value.commentsList.toMutableList()
            updatedPostCommentsList.add(newComment)
            _uiState.update { it.copy(commentsList = updatedPostCommentsList, commentText = "") }

            _uiState.value.commentListPostId?.let {
                postRepository.addNewCommentToPostCommentsList(
                    postId = it,
                    newComment = newComment
                )
            }
        }
    }

    fun reloadPostsList() {
        viewModelScope.launch {
            setRefreshingScreenState(true)
            delay(2000)
            loadPostsList()
            setRefreshingScreenState(false)
        }
    }

    private fun setRefreshingScreenState(isRefreshing: Boolean) {
        _uiState.update { it.copy(isScreenRefreshing = isRefreshing) }
    }
}


