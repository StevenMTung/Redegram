package br.com.steventung.redegram.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import br.com.steventung.redegram.domain.model.TranslateState
import br.com.steventung.redegram.ui.home.HomeScreen
import br.com.steventung.redegram.ui.home.HomeViewModel

fun NavGraphBuilder.homeGraph(
    onHideBottomAppBar: () -> Unit = {},
    onShowBottomAppBar: () -> Unit = {}
) {
    composable(route = AppDestination.Home.route) {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        LaunchedEffect(state.isShowCommentSection) {
            if (state.isShowCommentSection) {
                onHideBottomAppBar()
            } else {
                onShowBottomAppBar()
            }
        }
        HomeScreen(
            state = state,
            onPostDescriptionExpandedChanged = { postId, isDescriptionExpanded ->
                viewModel.setPostDescriptionExpanded(postId, isDescriptionExpanded)
            },
            onLikePost = { post ->
                viewModel.setLikePost(post)
            },
            onOpenComments = { post ->
                viewModel.loadPostComments(post.postId)
            },
            onHideCommentsSection = {
                viewModel.hideCommentsSection()
            },
            onCommentLiked = { commentId ->
                viewModel.setCommentLike(commentId)
            },
            onCommentTextChanged = { commentText ->
                viewModel.commentTextUpdate(commentText)
            },
            onTranslateComment = { comment ->
                when (comment.translateState) {
                    TranslateState.IsTranslating -> viewModel.setCommentTranslationState(
                        selectedComment = comment,
                        translationState = TranslateState.NotTranslated
                    )

                    TranslateState.NotTranslated -> viewModel.commentTranslation(comment)

                    TranslateState.Translated -> viewModel.setCommentTranslationState(
                        selectedComment = comment,
                        translationState = TranslateState.NotTranslated
                    )
                }
            },
            onTranslatePostDescription = { post ->
                when (post.postDescriptionTranslateState) {
                    TranslateState.IsTranslating -> viewModel.setPostDescriptionTranslationState(
                        selectedPost = post,
                        translationState = TranslateState.NotTranslated
                    )

                    TranslateState.NotTranslated -> viewModel.postDescriptionTranslation(
                        selectedPost = post
                    )

                    TranslateState.Translated -> viewModel.setPostDescriptionTranslationState(
                        selectedPost = post,
                        translationState = TranslateState.NotTranslated
                    )
                }
            },
            onSendComment = {
                viewModel.createComment()
            }
        )
    }
}