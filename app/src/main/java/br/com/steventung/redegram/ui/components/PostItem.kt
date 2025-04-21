package br.com.steventung.redegram.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ModeComment
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.steventung.redegram.R
import br.com.steventung.redegram.domain.model.Post
import br.com.steventung.redegram.domain.model.TranslateState
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: Post,
    onPostDescriptionExpandedChanged: (Long, Boolean) -> Unit = { _, _ -> },
    onLikePost: () -> Unit = {},
    onOpenComments: () -> Unit = {},
    onTranslatePostDescription: () -> Unit = {},
    onDoubleTapLike: () -> Unit = {},
    onTapImagePost: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    var showHeart by remember { mutableStateOf(false) }
    val heartScale = remember { Animatable(0f) }

    Column(modifier = modifier.fillMaxWidth()) {
        PostHeader(modifier, post.authorPostImage, post.authorPostName)

        Box(
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        onDoubleTapLike()
                        scope.launch {
                            showHeart = true
                            heartScale.snapTo(0f)
                            heartScale.animateTo(
                                targetValue = 1.5f,
                                animationSpec = tween(200)
                            )
                            delay(500)
                            heartScale.animateTo(
                                targetValue = 0f,
                                animationSpec = tween(300)
                            )
                            showHeart = false
                        }
                    },
                    onTap = { onTapImagePost() }
                )
            },
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = modifier.fillMaxWidth(),
                contentDescription = "post image",
                model = post.postImage,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.imagem_padrao),
                contentScale = ContentScale.FillWidth
            )

            if (showHeart || heartScale.value > 0f) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "like heart icon",
                    tint = Color.White,
                    modifier = Modifier
                        .scale(heartScale.value)
                        .size(70.dp)
                )
            }
        }

        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                PostInfoItem(
                    totalCountInfo = post.postTotalLikes,
                    imageVector = if (post.postIsLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    onClickIcon = { onLikePost() },
                    tint = if (post.postIsLiked) Color.Red else Color.Black
                )
                PostInfoItem(
                    totalCountInfo = post.comments.size,
                    imageVector = Icons.Outlined.ModeComment,
                    onClickIcon = onOpenComments
                )
            }

            when (post.postDescriptionTranslateState) {
                TranslateState.IsTranslating -> PostDescriptionText(
                    postId = post.postId,
                    postAuthorName = post.authorPostName,
                    postDescription = post.postDescription,
                    isPostDescriptionIsExpanded = post.isPostDescriptionExpanded,
                    onPostDescriptionExpandedChanged = onPostDescriptionExpandedChanged,
                    onTranslatePostDescription = onTranslatePostDescription,
                    translateButtonText = stringResource(R.string.traduzindo)
                )

                TranslateState.NotTranslated -> PostDescriptionText(
                    postId = post.postId,
                    postAuthorName = post.authorPostName,
                    postDescription = post.postDescription,
                    isPostDescriptionIsExpanded = post.isPostDescriptionExpanded,
                    onPostDescriptionExpandedChanged = onPostDescriptionExpandedChanged,
                    onTranslatePostDescription = onTranslatePostDescription,
                    translateButtonText = stringResource(R.string.ver_traducao)
                )

                TranslateState.Translated -> PostDescriptionText(
                    postId = post.postId,
                    postAuthorName = post.authorPostName,
                    postDescription = post.postDescriptionTranslated,
                    isPostDescriptionIsExpanded = post.isPostDescriptionExpanded,
                    onPostDescriptionExpandedChanged = onPostDescriptionExpandedChanged,
                    onTranslatePostDescription = onTranslatePostDescription,
                    translateButtonText = stringResource(R.string.ver_original)
                )
            }
        }
    }
}

@Composable
private fun PostDescriptionText(
    postAuthorName: String,
    postDescription: String,
    isPostDescriptionIsExpanded: Boolean,
    postId: Long,
    onPostDescriptionExpandedChanged: (Long, Boolean) -> Unit,
    onTranslatePostDescription: () -> Unit,
    translateButtonText: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight(500))) {
                    append("$postAuthorName ")
                }
                if (postAuthorName.length + postDescription.length > 110 && !isPostDescriptionIsExpanded) {
                    append(postDescription.take(110) + "...")
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 12.sp)) {
                        append("mais")
                    }
                } else if (postAuthorName.length + postDescription.length > 110 && isPostDescriptionIsExpanded) {
                    append(postDescription)
                    withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 12.sp)) {
                        append(" menos")
                    }
                } else {
                    append(postDescription)
                }
            },
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onPostDescriptionExpandedChanged(postId, isPostDescriptionIsExpanded)
            },
            style = TextStyle(lineHeight = 18.sp),
            fontSize = 16.sp
        )
        Text(
            text = translateButtonText,
            fontWeight = FontWeight(500),
            fontSize = 14.sp,
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                onTranslatePostDescription()
            }
        )
    }
}

@Composable
fun PostInfoItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    totalCountInfo: Int,
    onClickIcon: () -> Unit = {},
    tint: Color = Color.Black
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "post info icon",
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onClickIcon() },
            tint = tint
        )

        if (totalCountInfo != 0) {
            Text(text = totalCountInfo.toString(), fontWeight = FontWeight(500))
        }
    }
}

@Composable
private fun PostHeader(
    modifier: Modifier,
    userImage: Int,
    userName: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .size(30.dp)
                .clip(CircleShape),
            contentDescription = "user image",
            model = userImage,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.default_profile_picture),
            contentScale = ContentScale.Crop
        )
        Text(text = userName, fontWeight = FontWeight(500))
    }
}

@Preview(showBackground = true)
@Composable
private fun PostItemPreview() {
    PostItem(
        post = Post(
            postId = 1,
            authorPostId = "usuario@email.com",
            authorPostImage = 1,
            authorPostName = "teste_usuaiooooo",
            postImage = R.drawable.asia,
            postDescription = "Que foto deslumbrante! O tucano está simplesmente fantástico.",
            isPostDescriptionExpanded = false,
            postTotalLikes = 140,
            comments = emptyList(),
            postIsLiked = false,
        ),
//        isShowLikeHeart = false
    )
}