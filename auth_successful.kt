package com.example.authentication_page

import android.R.attr.fontWeight
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.authentication_page.R


@Composable
fun auth_successful(navController: NavController) {

    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(
                "android.resource://${context.packageName}/${R.raw.celebration}"
            )
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 60.dp,top=150.dp,bottom=10.dp),
            text = "Authentication Successful",
            color = Color.Black,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(16.dp))
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    useController = false
                    player = exoPlayer
                }
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        Button(
            onClick = {
                exoPlayer.stop()
                navController.navigate(Routes.home_page)
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
            modifier = Modifier
                .align(Alignment.CenterHorizontally).padding(bottom = 40.dp)
        ) {
            Text("Continue", color = Color.White)
        }
    }
}
