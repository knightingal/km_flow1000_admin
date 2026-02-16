package org.nanking.km_flow1000_admin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class Greeting {
    private val platform = getPlatform()

//    fun greet(): List<String> = buildList {
//        add(if (Random.nextBoolean()) "Hi!" else "Hello!")
//        add("Guess what this is! > ${platform.name.reversed()}!")
//    }
    private val rocketComponent = RocketComponent()
    fun greet(): Flow<String> = flow {
        emit(if (Random.nextBoolean()) "Hi!" else "Hello")
        delay(1.seconds)
        emit("Guess what this is! > ${platform.name.reversed()}")
        delay(1.seconds)
        emit(rocketComponent.launchPhrase())
    }
}

@Composable
@Preview
fun GreetingApp() {
    var text by remember { mutableStateOf("Loading") }
    var picIndexList by remember { mutableStateOf(listOf<PicIndexItem>()) }
    val rocketComponent = RocketComponent()
    LaunchedEffect(true) {
        text = rocketComponent.launchPhrase()
        picIndexList = rocketComponent.fetchPicIndex()
    }
    Column(
        modifier = Modifier
            .padding(all = 10.dp)
            .safeContentPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = text)
        HorizontalDivider()
        for (picIndexItem in picIndexList) {
//            Text(picIndexItem.name, Modifier.padding(bottom = 5.dp))
            AsyncImage(model = picIndexItem.coverUrl(), contentDescription = null,
                modifier = Modifier
                    .height((picIndexItem.coverHeight/4).dp)
                    .width((picIndexItem.coverWidth/4).dp))
        }
    }
}