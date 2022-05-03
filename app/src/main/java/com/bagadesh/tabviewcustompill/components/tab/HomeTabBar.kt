package com.bagadesh.tabviewcustompill.components.tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bagadesh.tabviewcustompill.components.NoRippleTheme
import com.bagadesh.tabviewcustompill.components.Tab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeTabBar(
    pagerState: PagerState,
    listOfTabs: List<Tab>
) {
    val rememberScope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.Transparent,
        modifier = Modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp)
            .height(50.dp),
        divider = {},
        indicator = @Composable { tabPositions ->
            val currentTabPosition = tabPositions[pagerState.currentPage]
            CustomTabIndicator.Indicator(currentTabPosition)
        }
    ) {
        listOfTabs.forEachIndexed { index, tab ->
            CustomTab(
                isSelected = pagerState.currentPage == index,
                title = tab.value,
                onClick = {
                    rememberScope.launch {
                        pagerState.scrollToPage(page = index)
                    }
                }
            )
        }
    }
}


@Composable
fun CustomTab(
    isSelected: Boolean,
    title: String,
    onClick: () -> Unit
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        Tab(
            selected = isSelected,
            onClick = onClick,
            modifier = Modifier
                .background(Color.Transparent),
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                val secondCanvasColor by animateColorAsState(
                    targetValue = if (isSelected) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        Color.Transparent
                    },
                    animationSpec = tween(
                        durationMillis = 250,
                        delayMillis = 0,
                        easing = FastOutSlowInEasing
                    )
                )
                Text(
                    text = title,
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = title,
                    modifier = Modifier,
                    color = secondCanvasColor
                )
            }

        }
    }

}

