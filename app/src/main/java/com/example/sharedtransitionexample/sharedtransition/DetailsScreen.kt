package com.example.sharedtransitionexample.sharedtransition

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedtransitionexample.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DetailsScreen(
    modifier: Modifier = Modifier,
    item : Int,
    name : String,
    animatedVisibilityScope : AnimatedVisibilityScope
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(item),
            contentDescription = "image",
            modifier = Modifier
                .aspectRatio(16 / 9f)
                .sharedElement(
                    state = rememberSharedContentState(key = "image/${item}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _,_ ->
                        tween(durationMillis = 1000)
                    }
                )
        )
        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = name,
            modifier = Modifier
                .sharedElement(
                    state = rememberSharedContentState(key = "text/${name}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                    boundsTransform = { _,_ ->
                        tween(durationMillis = 1000)
                    }
                ),
        )
    }
}