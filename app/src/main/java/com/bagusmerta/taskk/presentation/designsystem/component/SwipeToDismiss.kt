package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.utils.extensions.onPositionInParentChanged
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.hypot

private const val dismissFraction = 0.4f
private const val iconShownFraction = 0.07f

@Composable
fun TaskkSwipeToDismiss(
    modifier: Modifier = Modifier,
    backgroundModifier: Modifier = Modifier,
    backgroundSecondaryModifier: Modifier = Modifier,
    content: @Composable (isDismissed: Boolean) -> Unit,
    onDismiss: () -> Unit,
) {
    SwipeDismiss(
        modifier = modifier,
        background = { _, fraction ->
            val wouldCompleteOnRelease = fraction.absoluteValue >= dismissFraction
            val iconVisible = fraction.absoluteValue >= iconShownFraction
            val haptic = LocalHapticFeedback.current

            var shouldTriggerHaptic by remember { mutableStateOf(false) }
            var bounceState by remember { mutableStateOf(false) }
            val lottieIcon by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.delete_anim))
            val lottieAnimatable = rememberLottieAnimatable()
            var iconCenter by remember { mutableStateOf(Offset(0f, 0f)) }

            val bounceInOut by animateFloatAsState(
                targetValue = if (bounceState) 1.2f else 1f
            )

            val maxRadius = hypot(iconCenter.x.toDouble(), iconCenter.y.toDouble())

            LaunchedEffect(wouldCompleteOnRelease) {
                if (wouldCompleteOnRelease) {
                    shouldTriggerHaptic = true

                    launch {
                        bounceState = true
                        delay(100)
                        bounceState = false
                    }
                }

                if (shouldTriggerHaptic) {
                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                }
            }
            LaunchedEffect(iconVisible) {
                if (iconVisible) {
                    launch {
                        delay(50)
                        lottieAnimatable.animate(
                            composition = lottieIcon,
                        )
                    }
                }
            }

            Box(
                modifier = backgroundModifier
                    .fillMaxSize()
            ) {
                // A simple box serve as a background behind the lottie
                Spacer(
                    modifier = backgroundSecondaryModifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.background)
                )

                Box(
                    Modifier
                        .align(Alignment.CenterEnd)
                        .padding(horizontal  = 16.dp)
                        .onPositionInParentChanged { iconCenter = it.boundsInParent().center }
                ) {
                    LottieAnimation(
                        lottieIcon,
                        modifier = Modifier
                            .size(62.dp)
                            .scale(bounceInOut),
                        progress = {
                            lottieAnimatable.progress
                        }
                    )
                }
            }
        },
        content = content,
        onDismiss = onDismiss
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeDismiss(
    modifier: Modifier = Modifier,
    background: @Composable (isDismissed: Boolean, fraction: Float) -> Unit,
    content: @Composable (isDismissed: Boolean) -> Unit,
    directions: Set<DismissDirection> = setOf(DismissDirection.EndToStart),
    enter: EnterTransition = expandVertically(),
    exit: ExitTransition = shrinkVertically(
        animationSpec = tween(
            durationMillis = 200,
        )
    ),
    onDismiss: () -> Unit
) {
    val dismissState = rememberDismissState(
        confirmStateChange = {
            it != DismissValue.DismissedToEnd
        }
    )
    val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == DismissValue.DismissedToStart) {
            delay(400)
            onDismiss()
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = !isDismissed,
        enter = enter,
        exit = exit
    ) {
        SwipeToDismiss(
            modifier = modifier,
            state = dismissState,
            directions = directions,
            background = {
                if (dismissState.dismissDirection != null && dismissState.dismissDirection in directions) {
                    val fraction = dismissState.progress.fraction
                    background(isDismissed, fraction)
                }
            },
            dismissContent = { content(isDismissed) },
            dismissThresholds = { FractionalThreshold(dismissFraction) }
        )
    }
}