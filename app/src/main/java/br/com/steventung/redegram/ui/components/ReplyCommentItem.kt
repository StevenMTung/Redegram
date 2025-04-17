package br.com.steventung.redegram.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.steventung.redegram.R
import coil3.compose.AsyncImage

@Composable
fun ReplyCommentItem(
    modifier: Modifier = Modifier,
    userImage: Int,
    commentText: String,
    onCommentTextChanged: (String) -> Unit = {},
    onSendComment: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            model = userImage,
            contentDescription = "user image",
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.default_profile_picture),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
        )
        BasicTextField(
            value = commentText,
            onValueChange = onCommentTextChanged,
            modifier = Modifier
                .weight(1f)
                .border(
                    width = 0.2.dp,
                    shape = RoundedCornerShape(100),
                    color = Color.Gray
                )
                .padding(12.dp),
            decorationBox = { innerTextField ->
                Box {
                    if (commentText.isEmpty()) {
                        Text(
                            text = stringResource(R.string.adicione_um_comentario),
                            color = Color.Gray
                        )
                    }
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Done
            ),
            maxLines = Int.MAX_VALUE,
        )
        AnimatedVisibility(visible = commentText.isNotEmpty()) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                contentDescription = "send icon",
                tint = Color.Blue,
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { onSendComment() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReplyCommentItemPreview() {
    ReplyCommentItem(userImage = 1, commentText = "Teste comentário")
}

@Preview(showBackground = true)
@Composable
private fun ReplyCommentItemEmptyTextPreview() {
    ReplyCommentItem(userImage = 1, commentText = "")
}