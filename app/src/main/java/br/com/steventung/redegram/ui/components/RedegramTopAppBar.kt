package br.com.steventung.redegram.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.steventung.redegram.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RedegramTopAppBar(modifier: Modifier = Modifier, scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Color.White),
        modifier = modifier,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight(1000),
                fontSize = 30.sp
            )
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = "favorite icon",
                    tint = Color.Black
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Send,
                    contentDescription = "favorite icon",
                    tint = Color.Black
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun RedegramTopAppBarPreview() {
    RedegramTopAppBar(scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior())
}