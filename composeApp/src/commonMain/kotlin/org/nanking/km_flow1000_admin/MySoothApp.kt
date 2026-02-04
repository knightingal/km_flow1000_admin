package org.nanking.km_flow1000_admin

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelab.basiclayouts.ui.theme.MySootheTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.nanking.km_flow1000_admin.resources.Res
import org.nanking.km_flow1000_admin.resources.Res.drawable
import org.nanking.km_flow1000_admin.resources.ab1_inversions
import org.nanking.km_flow1000_admin.resources.ab2_quick_yoga
import org.nanking.km_flow1000_admin.resources.ab3_stretching
import org.nanking.km_flow1000_admin.resources.ab4_tabata
import org.nanking.km_flow1000_admin.resources.ab5_hiit
import org.nanking.km_flow1000_admin.resources.ab6_pre_natal_yoga
import org.nanking.km_flow1000_admin.resources.fc1_short_mantras
import org.nanking.km_flow1000_admin.resources.fc2_nature_meditations
import org.nanking.km_flow1000_admin.resources.fc3_stress_and_anxiety
import org.nanking.km_flow1000_admin.resources.fc4_self_massage
import org.nanking.km_flow1000_admin.resources.fc5_overwhelmed
import org.nanking.km_flow1000_admin.resources.fc6_nightly_wind_down

// Step: Search bar - Modifiers
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    TextField(
        value = "",
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

// Step: Align your body - Alignment
@Composable
fun AlignYourBodyElement(
    modifier: Modifier = Modifier
) {
    // Implement composable here
}

// Step: Favorite collection card - Material Surface
@Composable
fun FavoriteCollectionCard(
    modifier: Modifier = Modifier
) {
    // Implement composable here
}

// Step: Align your body row - Arrangements
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    // Implement composable here
}

// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    // Implement composable here
}

// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    modifier: Modifier = Modifier
) {
    // Implement composable here
}

// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // Implement composable here
}

// Step: Bottom navigation - Material
@Composable
private fun SootheBottomNavigation(modifier: Modifier = Modifier) {
    // Implement composable here
}

// Step: MySoothe App - Scaffold
@Composable
fun MySootheAppPortrait() {
    // Implement composable here
}

// Step: Bottom navigation - Material
@Composable
private fun SootheNavigationRail(modifier: Modifier = Modifier) {
    // Implement composable here
}

// Step: Landscape Mode
@Composable
fun MySootheAppLandscape(){
    // Implement composable here
}

@Composable
fun MySootheApp() {
    // Implement composable here
}

private val alignYourBodyData = listOf(
    drawable.ab1_inversions to Res.string.ab1_inversions,
    drawable.ab2_quick_yoga to Res.string.ab2_quick_yoga,
    drawable.ab3_stretching to Res.string.ab3_stretching,
    drawable.ab4_tabata to Res.string.ab4_tabata,
    drawable.ab5_hiit to Res.string.ab5_hiit,
    drawable.ab6_pre_natal_yoga to Res.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }


private val favoriteCollectionsData = listOf(
    drawable.fc1_short_mantras to Res.string.fc1_short_mantras,
    drawable.fc2_nature_meditations to Res.string.fc2_nature_meditations,
    drawable.fc3_stress_and_anxiety to Res.string.fc3_stress_and_anxiety,
    drawable.fc4_self_massage to Res.string.fc4_self_massage,
    drawable.fc5_overwhelmed to Res.string.fc5_overwhelmed,
    drawable.fc6_nightly_wind_down to Res.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    val drawable: DrawableResource,
    val text: StringResource
)

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun SearchBarPreview() {
    MySootheTheme { SearchBar(Modifier.padding(8.dp)) }
}
@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyElementPreview() {
    MySootheTheme {
        AlignYourBodyElement(
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    MySootheTheme {
        FavoriteCollectionCard(
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionsGridPreview() {
    MySootheTheme { FavoriteCollectionsGrid() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    MySootheTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    MySootheTheme { HomeSection() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ScreenContentPreview() {
    MySootheTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    MySootheTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview() {
    MySootheTheme { SootheNavigationRail() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePortraitPreview() {
    MySootheAppPortrait()
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MySootheLandscapePreview() {
    MySootheAppLandscape()
}
