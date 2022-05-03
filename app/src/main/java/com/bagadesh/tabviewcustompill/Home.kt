@file:OptIn(ExperimentalPagerApi::class)

package com.bagadesh.tabviewcustompill

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.bagadesh.tabviewcustompill.components.Tab
import com.bagadesh.tabviewcustompill.components.tab.HomeTabBar
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun Home() {
    Column {
        val pagerState = rememberPagerState()
        val tabs = listOf(
            Tab("Home"),
            Tab("Shows"),
            Tab("Listen"),
        )
        HomeTabBar(
            pagerState,
            tabs,
        )
        HorizontalPager(count = tabs.size, state = pagerState) {
            Text(text = "Page, $it")
        }
    }
}