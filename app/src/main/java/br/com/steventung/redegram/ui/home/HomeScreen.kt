package br.com.steventung.redegram.ui.home

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.steventung.redegram.R
import br.com.steventung.redegram.data.samples.Samples
import br.com.steventung.redegram.domain.model.Comment
import br.com.steventung.redegram.domain.model.Post
import br.com.steventung.redegram.ui.components.CommentsSection
import br.com.steventung.redegram.ui.components.PostItem
import br.com.steventung.redegram.ui.components.RedegramTopAppBar
import br.com.steventung.redegram.ui.components.ReplyCommentItem
import br.com.steventung.redegram.utils.rememberWindowHeight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    state: HomeUiState,
    onPostDescriptionExpandedChanged: (Long, Boolean) -> Unit = { _, _ -> },
    onLikePost: (Post) -> Unit = {},
    onOpenComments: (Post) -> Unit = {},
    onHideCommentsSection: () -> Unit = {},
    onCommentLiked: (String) -> Unit = {},
    onCommentTextChanged: (String) -> Unit = {},
    onTranslateComment: (Comment) -> Unit = {},
    onTranslatePostDescription: (Post) -> Unit = {},
    onSendComment: () -> Unit = {},
    onRefreshingScreen: () -> Unit = {},
    onDoubleTapLike: (Post) -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val availableHeight = rememberWindowHeight()
    val sheetPeekHeight by animateDpAsState(
        targetValue = if (state.isShowCommentSection) 450.dp else 0.dp,
        label = "Peek height"
    )
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(scaffoldState.bottomSheetState) {
        snapshotFlow { scaffoldState.bottomSheetState.currentValue }
            .collect { sheetValue ->
                when (sheetValue) {
                    SheetValue.PartiallyExpanded -> keyboardController?.hide()
                    SheetValue.Hidden -> onHideCommentsSection()
                    SheetValue.Expanded -> {}
                }
            }
    }
    val lazyColumnState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isScreenRefreshing,
        onRefresh = {
            if (!state.isShowCommentSection) {
                onRefreshingScreen()
            }
        }
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Column(
            modifier = Modifier.pullRefresh(state = pullRefreshState)
        ) {
            BottomSheetScaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                topBar = { RedegramTopAppBar(scrollBehavior = scrollBehavior) },
                containerColor = Color.White,
                sheetContent = {
                    CommentsSection(
                        lazyColumnState = lazyColumnState,
                        commentsLists = state.commentsList,
                        modifier = Modifier
                            .height(availableHeight),
                        onCommentLiked = onCommentLiked,
                        onTranslateComment = onTranslateComment,
                    )
                },
                sheetPeekHeight = sheetPeekHeight,
                sheetShadowElevation = 1.dp,
                sheetTonalElevation = 1.dp,
                sheetContainerColor = Color.White
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Color.Black.copy(
                                alpha = (if (state.isShowCommentSection) 0.80f else 0f)
                            )
                        )
                        .graphicsLayer(
                            alpha = if (state.isShowCommentSection) 0.3f else 1f,
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            if (state.isShowCommentSection) {
                                onHideCommentsSection()
                            }
                        },
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    userScrollEnabled = !state.isShowCommentSection
                ) {
                    items(state.postsList) { post ->
                        PostItem(
                            post = post,
                            onPostDescriptionExpandedChanged = { postId, isExpanded ->
                                if (!state.isShowCommentSection) {
                                    onPostDescriptionExpandedChanged(postId, isExpanded)
                                } else {
                                    onHideCommentsSection()
                                }
                            },
                            onLikePost = {
                                if (!state.isShowCommentSection) {
                                    onLikePost(post)
                                } else {
                                    onHideCommentsSection()
                                }
                            },
                            onOpenComments = {
                                if (!state.isShowCommentSection) {
                                    onOpenComments(post)
                                } else {
                                    onHideCommentsSection()
                                }
                            },
                            onTranslatePostDescription = {
                                onTranslatePostDescription(post)
                            },
                            onDoubleTapLike = {
                                onDoubleTapLike(post)
                            },
                            onTapImagePost = onHideCommentsSection
                        )
                    }
                }
            }
        }
        if (state.isShowCommentSection) {
            ReplyCommentItem(
                userImage = state.userProfile?.userImage ?: R.drawable.default_profile_picture,
                commentText = state.commentText,
                onCommentTextChanged = onCommentTextChanged,
                onSendComment = {
                    scope.launch {
                        onSendComment()
                        keyboardController?.hide()
                        if (state.commentsList.isNotEmpty()) {
                            lazyColumnState.animateScrollToItem(0)
                        }
                    }
                },
                modifier = Modifier
                    .align(alignment = Alignment.BottomCenter)
            )
        }
        if (!state.isShowCommentSection) {
            PullRefreshIndicator(
                refreshing = state.isScreenRefreshing,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        state = HomeUiState(postsList = Samples.postsList)
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenShowCommentSectionPreview() {
    HomeScreen(
        state = HomeUiState(postsList = Samples.postsList, isShowCommentSection = true)
    )
}