package com.krayushkindev.allsounds.ui.widget

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.krayushkindev.allsounds.core.constant.Constants.ZERO_INT
import com.krayushkindev.allsounds.core.extension.empty
import com.krayushkindev.allsounds.ui.theme.Black50
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ALPHA_DEFAULT_VALUE = 1f
private const val ALPHA_DARK_VALUE = 0.5f
private const val PREVIEW_INITIAL_VALUE = 1f
private const val PREVIEW_TARGET_VALUE = 1.1F
private const val PREVIEW_DURATION_MILLIS = 500
private const val PREVIEW_START_DURATION_MILLIS = 300L
private const val CLICK_INITIAL_VALUE = 1f
private const val CLICK_TARGET_VALUE = 0.8F
private const val ANIMATE_DURATION_DEFAULT = 125
private const val SERVER_TIMEOUT = 400L

@Composable
fun ClickAnimateBlock(
    modifier: Modifier = Modifier,
    modifierInner: Modifier = modifier,
    isEnabled: Boolean = true,
    isEnabledState: MutableState<Boolean> = mutableStateOf(true),
    isNotDarkened: Boolean = isEnabled,
    isAlphaActive: Boolean = false,
    isAnimateScalePreviewShow: Boolean = false,
    shape: Shape = RoundedCornerShape(ZERO_INT.dp),
    animateDurationMillis: Int = ANIMATE_DURATION_DEFAULT,
    transformOrigin: TransformOrigin = TransformOrigin.Center,
    onStartClick: (() -> Unit)? = null,
    onFinishClick: () -> Unit,
    content: @Composable () -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }
    var isBoxClicked by remember { mutableStateOf(false) }
    var isEnabledClick by remember { mutableStateOf(true) }
    val scaleAnimation = rememberInfiniteTransition()
    val coroutineScope = rememberCoroutineScope()

    val scalePreviewAnimate by scaleAnimation.animateFloat(
        initialValue = PREVIEW_INITIAL_VALUE,
        targetValue = PREVIEW_TARGET_VALUE,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = PREVIEW_DURATION_MILLIS),
            repeatMode = RepeatMode.Reverse
        ),
        label = String.empty()
    )

    var anim by remember { mutableStateOf(false) }

    if (isAnimateScalePreviewShow) {
        LaunchedEffect(Unit) {
            delay(PREVIEW_START_DURATION_MILLIS)
            anim = true
        }
    }

    val scalePreview = if (anim) scalePreviewAnimate else PREVIEW_INITIAL_VALUE
    val alpha = if (!isEnabled && isAlphaActive) ALPHA_DARK_VALUE else ALPHA_DEFAULT_VALUE

    val scaleClick by animateFloatAsState(
        targetValue = if (isBoxClicked) CLICK_TARGET_VALUE else CLICK_INITIAL_VALUE,
        animationSpec = tween(animateDurationMillis, easing = FastOutSlowInEasing),
        finishedListener = {
            if (isBoxClicked) {
                isBoxClicked = !isBoxClicked
            } else {
                isEnabledState.value = true
                onFinishClick()
                isEnabledClick = true
            }
        },
        label = String.empty()
    )

    Box(
        modifier = modifier
            .alpha(alpha)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !isBoxClicked && isEnabled && isEnabledClick && isEnabledState.value
            ) {
                if (isEnabledClick && isEnabledState.value) {
                    isEnabledState.value = false
                    isBoxClicked = true
                    isEnabledClick = false
                    onStartClick?.invoke()
                } else {
                    coroutineScope.launch {
                        delay(SERVER_TIMEOUT)
                        isEnabledState.value = true
                    }
                }
            }
            .graphicsLayer(
                scaleX = scalePreview,
                scaleY = scalePreview,
                transformOrigin = transformOrigin
            )
            .graphicsLayer(
                scaleX = scaleClick,
                scaleY = scaleClick,
                transformOrigin = transformOrigin
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
        if (!isNotDarkened) {
            Box(
                modifier = modifierInner
                    .matchParentSize()
                    .background(color = Black50, shape = shape)
            )
        }
    }
}

@Preview(name = "ClickAnimateBlock")
@Composable
private fun PreviewClickAnimateBlock() {
    ClickAnimateBlock(onStartClick = {}, onFinishClick = {}) {}
}