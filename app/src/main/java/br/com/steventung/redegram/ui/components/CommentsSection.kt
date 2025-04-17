package br.com.steventung.redegram.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.steventung.redegram.R
import br.com.steventung.redegram.domain.model.Comment
import br.com.steventung.redegram.domain.model.TranslateState
import coil3.compose.AsyncImage

@Composable
fun CommentsSection(
    userCommentText: String,
    modifier: Modifier = Modifier,
    commentsLists: List<Comment>,
    onCommentLiked: (Long) -> Unit = {},
    userImage: Int,
    onCommentTextChanged: (String) -> Unit = {},
    onTranslateComment: (Comment) -> Unit = {}
) {
    Column(
        modifier = modifier
            .imePadding()
            .fillMaxHeight(),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = stringResource(R.string.comment),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.5.dp,
            color = Color.LightGray
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(commentsLists) { comment ->
                CommentItem(
                    comment = comment,
                    onCommentLiked = onCommentLiked,
                    onTranslateComment = { onTranslateComment(comment) }
                )
            }
        }
        ReplyCommentItem(
            modifier = Modifier
                .padding(8.dp),
            userImage = userImage,
            commentText = userCommentText,
            onCommentTextChanged = onCommentTextChanged
        )
    }
}

@Composable
fun CommentItem(
    modifier: Modifier = Modifier,
    comment: Comment,
    onCommentLiked: (Long) -> Unit = {},
    onTranslateComment: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            AsyncImage(
                model = comment.authorImage,
                contentDescription = "authors comment image",
                modifier = Modifier
                    .size(30.dp)
                    .offset(y = 6.dp)
                    .clip(shape = CircleShape),
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.default_profile_picture),
                contentScale = ContentScale.Crop
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        ) {
            Text(text = comment.authorName, fontWeight = FontWeight(500), fontSize = 14.sp)
            when (comment.translateState) {
                TranslateState.IsTranslating -> Text(
                    text = comment.content,
                    fontSize = 16.sp,
                    style = TextStyle(lineHeight = 18.sp)
                )

                TranslateState.NotTranslated -> Text(
                    text = comment.content,
                    fontSize = 16.sp,
                    style = TextStyle(lineHeight = 18.sp)
                )

                TranslateState.Translated -> Text(
                    text = comment.translatedContent,
                    fontSize = 16.sp,
                    style = TextStyle(lineHeight = 18.sp)
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.responder),
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    fontWeight = FontWeight(500)
                )
                when (comment.translateState) {
                    TranslateState.IsTranslating -> Text(
                        text = stringResource(R.string.traduzindo),
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.clickable {
                            onTranslateComment()
                        }
                    )

                    TranslateState.NotTranslated -> Text(
                        text = stringResource(R.string.ver_traducao),
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.clickable {
                            onTranslateComment()
                        }
                    )

                    TranslateState.Translated -> Text(
                        text = stringResource(R.string.ver_original),
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        fontWeight = FontWeight(500),
                        modifier = Modifier.clickable {
                            onTranslateComment()
                        }
                    )
                }

            }
        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .offset(y = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = if (comment.isCommentLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "like comment icon",
                tint = if (comment.isCommentLiked) Color.Red else Color.DarkGray,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onCommentLiked(comment.commentId)
                    }
            )
            if (comment.commentTotalLikes > 0) {
                Text(
                    text = comment.commentTotalLikes.toString(),
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CommentItemPreview() {
    CommentItem(
        comment = Comment(
            commentId = 21,
            authorId = "mikoto@email.com",
            authorImage = R.drawable.perfil_japonesa,
            authorName = "mikoto_k",
            content = "なんて素敵な写真！ここは日本で一番好きな場所です。",
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CommentsSectionPreview() {
    CommentsSection(
        userCommentText = "Teste de comentário",
        userImage = 1,
        commentsLists = listOf(
            Comment(
                commentId = 21,
                authorId = "mikoto@email.com",
                authorImage = 1,
                authorName = "mikoto_k",
                content = "なんて素敵な写真！ここは日本で一番好きな場所です。",
                commentTotalLikes = 15,
                isCommentLiked = true
            ),
            Comment(
                commentId = 22,
                authorId = "amelie@email.com",
                authorImage = 1,
                authorName = "amelie-bernard",
                content = "Mon rêve, c'est de découvrir le Japon ! Je vais ajouter cet endroit à mon itinéraire de voyage.",
            )
        )
    )
}
