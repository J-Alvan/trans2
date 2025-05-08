package com.example.swifttrans.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.swifttrans.R
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateToNext: () -> Unit){
    val splashScreenDuration = 3000L
    val alpha = remember { Animatable(0f) }
    var textToShow by remember { mutableStateOf("") }
    val fullyAnimatedText = "Swift Trans"
    val offsetY = remember { Animatable(0f) }
    val pulse by rememberInfiniteTransition().animateFloat(
        initialValue = 20f,
        targetValue = 40f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse)
    )

    LaunchedEffect(Unit) {
        delay(splashScreenDuration)
        onNavigateToNext()
        alpha.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 1000))
        offsetY.animateTo(targetValue = -20f, animationSpec = tween(durationMillis = 1000))
    }
    for (i in 1..fullyAnimatedText.length){
        textToShow = fullyAnimatedText.substring(0, i)

    }
    Box(modifier = Modifier.fillMaxSize().background(Color.Black),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.logo),
                contentDescription = "App logo",
                modifier = Modifier.size(128.dp))
            Text(text = "Swift Trans",
                color = Color.White,
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .alpha(alpha = 0.5f)
                    .offset(y = offsetY.value.dp)
                    .shadow(elevation = pulse.dp, shape = RectangleShape,clip = false),
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Cyan.copy(alpha = 0.8f),
                        offset = Offset(0f, 0f),
                        blurRadius = pulse
                    )
                )
            )
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(onNavigateToNext = {})
}