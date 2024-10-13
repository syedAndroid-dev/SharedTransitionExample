package com.example.sharedtransitionexample.sharedtransition

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharedtransitionexample.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ListScreen(
    modifier: Modifier = Modifier,
    onNavigateDetailsPage : (Int,String) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val images = listOf(R.drawable.ic_android, R.drawable.ic_ios)
    val texts = listOf("Android", "iOS")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        itemsIndexed(images){ index, item ->
            Card(
                onClick = {
                    onNavigateDetailsPage(item,texts[index])
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    Image(
                        painter = painterResource(item),
                        contentDescription = "image",
                        modifier = Modifier
                            .aspectRatio(16/9f)
                            .weight(.5f)
                            .sharedElement(
                                state = rememberSharedContentState(key = "image/$item"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _,_ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                    )
                    Text(
                        text = texts[index],
                        modifier = Modifier
                            .weight(.5f)
                            .sharedElement(
                                state = rememberSharedContentState(key = "text/${texts[index]}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _,_ ->
                                    tween(durationMillis = 1000)
                                }
                            )
                    )
                }
            }
        }
    }
}