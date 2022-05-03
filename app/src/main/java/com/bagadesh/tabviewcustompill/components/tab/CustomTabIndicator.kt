package com.bagadesh.tabviewcustompill.components.tab

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LocalContentColor
import androidx.compose.material.TabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object CustomTabIndicator {

    @Composable
    fun Indicator(
        currentTabPosition: TabPosition
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .layout { measurable, constraints ->
                    val placeable = measurable.measure(constraints = constraints)
                    layout(width = constraints.maxWidth, height = constraints.maxHeight) {
                        placeable.place(x = 0, y = 0, -2f)
                    }
                }
                .fillMaxWidth()
                .background(Color.Transparent)
        ) {
            val indicatorOffset by animateDpAsState(
                targetValue = currentTabPosition.left,
                animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
            )
            Box(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxHeight()
                    .offset(x = indicatorOffset)
                    .width(currentTabPosition.width)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(50)
                    )
            )
        }
    }

}