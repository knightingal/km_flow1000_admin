/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codelab.basiclayouts.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import org.nanking.km_flow1000_admin.ui.theme.GreenColor

val colorSuite = GreenColor

private val lightScheme = lightColorScheme(
    primary = colorSuite.primaryLight,
    onPrimary = colorSuite.onPrimaryLight,
    primaryContainer = colorSuite.primaryContainerLight,
    onPrimaryContainer = colorSuite.onPrimaryContainerLight,
    secondary = colorSuite.secondaryLight,
    onSecondary = colorSuite.onSecondaryLight,
    secondaryContainer = colorSuite.secondaryContainerLight,
    onSecondaryContainer = colorSuite.onSecondaryContainerLight,
    tertiary = colorSuite.tertiaryLight,
    onTertiary = colorSuite.onTertiaryLight,
    tertiaryContainer = colorSuite.tertiaryContainerLight,
    onTertiaryContainer = colorSuite.onTertiaryContainerLight,
    error = colorSuite.errorLight,
    onError = colorSuite.onErrorLight,
    errorContainer = colorSuite.errorContainerLight,
    onErrorContainer = colorSuite.onErrorContainerLight,
    background = colorSuite.backgroundLight,
    onBackground = colorSuite.onBackgroundLight,
    surface = colorSuite.surfaceLight,
    onSurface = colorSuite.onSurfaceLight,
    surfaceVariant = colorSuite.surfaceVariantLight,
    onSurfaceVariant = colorSuite.onSurfaceVariantLight,
    outline = colorSuite.outlineLight,
    outlineVariant = colorSuite.outlineVariantLight,
    scrim = colorSuite.scrimLight,
    inverseSurface = colorSuite.inverseSurfaceLight,
    inverseOnSurface = colorSuite.inverseOnSurfaceLight,
    inversePrimary = colorSuite.inversePrimaryLight,
    surfaceDim = colorSuite.surfaceDimLight,
    surfaceBright = colorSuite.surfaceBrightLight,
    surfaceContainerLowest = colorSuite.surfaceContainerLowestLight,
    surfaceContainerLow = colorSuite.surfaceContainerLowLight,
    surfaceContainer = colorSuite.surfaceContainerLight,
    surfaceContainerHigh = colorSuite.surfaceContainerHighLight,
    surfaceContainerHighest = colorSuite.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = colorSuite.primaryDark,
    onPrimary = colorSuite.onPrimaryDark,
    primaryContainer = colorSuite.primaryContainerDark,
    onPrimaryContainer = colorSuite.onPrimaryContainerDark,
    secondary = colorSuite.secondaryDark,
    onSecondary = colorSuite.onSecondaryDark,
    secondaryContainer = colorSuite.secondaryContainerDark,
    onSecondaryContainer = colorSuite.onSecondaryContainerDark,
    tertiary = colorSuite.tertiaryDark,
    onTertiary = colorSuite.onTertiaryDark,
    tertiaryContainer = colorSuite.tertiaryContainerDark,
    onTertiaryContainer = colorSuite.onTertiaryContainerDark,
    error = colorSuite.errorDark,
    onError = colorSuite.onErrorDark,
    errorContainer = colorSuite.errorContainerDark,
    onErrorContainer = colorSuite.onErrorContainerDark,
    background = colorSuite.backgroundDark,
    onBackground = colorSuite.onBackgroundDark,
    surface = colorSuite.surfaceDark,
    onSurface = colorSuite.onSurfaceDark,
    surfaceVariant = colorSuite.surfaceVariantDark,
    onSurfaceVariant = colorSuite.onSurfaceVariantDark,
    outline = colorSuite.outlineDark,
    outlineVariant = colorSuite.outlineVariantDark,
    scrim = colorSuite.scrimDark,
    inverseSurface = colorSuite.inverseSurfaceDark,
    inverseOnSurface = colorSuite.inverseOnSurfaceDark,
    inversePrimary = colorSuite.inversePrimaryDark,
    surfaceDim = colorSuite.surfaceDimDark,
    surfaceBright = colorSuite.surfaceBrightDark,
    surfaceContainerLowest = colorSuite.surfaceContainerLowestDark,
    surfaceContainerLow = colorSuite.surfaceContainerLowDark,
    surfaceContainer = colorSuite.surfaceContainerDark,
    surfaceContainerHigh = colorSuite.surfaceContainerHighDark,
    surfaceContainerHighest = colorSuite.surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = colorSuite.primaryLightMediumContrast,
    onPrimary = colorSuite.onPrimaryLightMediumContrast,
    primaryContainer = colorSuite.primaryContainerLightMediumContrast,
    onPrimaryContainer = colorSuite.onPrimaryContainerLightMediumContrast,
    secondary = colorSuite.secondaryLightMediumContrast,
    onSecondary = colorSuite.onSecondaryLightMediumContrast,
    secondaryContainer = colorSuite.secondaryContainerLightMediumContrast,
    onSecondaryContainer = colorSuite.onSecondaryContainerLightMediumContrast,
    tertiary = colorSuite.tertiaryLightMediumContrast,
    onTertiary = colorSuite.onTertiaryLightMediumContrast,
    tertiaryContainer = colorSuite.tertiaryContainerLightMediumContrast,
    onTertiaryContainer = colorSuite.onTertiaryContainerLightMediumContrast,
    error = colorSuite.errorLightMediumContrast,
    onError = colorSuite.onErrorLightMediumContrast,
    errorContainer = colorSuite.errorContainerLightMediumContrast,
    onErrorContainer = colorSuite.onErrorContainerLightMediumContrast,
    background = colorSuite.backgroundLightMediumContrast,
    onBackground = colorSuite.onBackgroundLightMediumContrast,
    surface = colorSuite.surfaceLightMediumContrast,
    onSurface = colorSuite.onSurfaceLightMediumContrast,
    surfaceVariant = colorSuite.surfaceVariantLightMediumContrast,
    onSurfaceVariant = colorSuite.onSurfaceVariantLightMediumContrast,
    outline = colorSuite.outlineLightMediumContrast,
    outlineVariant = colorSuite.outlineVariantLightMediumContrast,
    scrim = colorSuite.scrimLightMediumContrast,
    inverseSurface = colorSuite.inverseSurfaceLightMediumContrast,
    inverseOnSurface = colorSuite.inverseOnSurfaceLightMediumContrast,
    inversePrimary = colorSuite.inversePrimaryLightMediumContrast,
    surfaceDim = colorSuite.surfaceDimLightMediumContrast,
    surfaceBright = colorSuite.surfaceBrightLightMediumContrast,
    surfaceContainerLowest = colorSuite.surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = colorSuite.surfaceContainerLowLightMediumContrast,
    surfaceContainer = colorSuite.surfaceContainerLightMediumContrast,
    surfaceContainerHigh = colorSuite.surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = colorSuite.surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = colorSuite.primaryLightHighContrast,
    onPrimary = colorSuite.onPrimaryLightHighContrast,
    primaryContainer = colorSuite.primaryContainerLightHighContrast,
    onPrimaryContainer = colorSuite.onPrimaryContainerLightHighContrast,
    secondary = colorSuite.secondaryLightHighContrast,
    onSecondary = colorSuite.onSecondaryLightHighContrast,
    secondaryContainer = colorSuite.secondaryContainerLightHighContrast,
    onSecondaryContainer = colorSuite.onSecondaryContainerLightHighContrast,
    tertiary = colorSuite.tertiaryLightHighContrast,
    onTertiary = colorSuite.onTertiaryLightHighContrast,
    tertiaryContainer = colorSuite.tertiaryContainerLightHighContrast,
    onTertiaryContainer = colorSuite.onTertiaryContainerLightHighContrast,
    error = colorSuite.errorLightHighContrast,
    onError = colorSuite.onErrorLightHighContrast,
    errorContainer = colorSuite.errorContainerLightHighContrast,
    onErrorContainer = colorSuite.onErrorContainerLightHighContrast,
    background = colorSuite.backgroundLightHighContrast,
    onBackground = colorSuite.onBackgroundLightHighContrast,
    surface = colorSuite.surfaceLightHighContrast,
    onSurface = colorSuite.onSurfaceLightHighContrast,
    surfaceVariant = colorSuite.surfaceVariantLightHighContrast,
    onSurfaceVariant = colorSuite.onSurfaceVariantLightHighContrast,
    outline = colorSuite.outlineLightHighContrast,
    outlineVariant = colorSuite.outlineVariantLightHighContrast,
    scrim = colorSuite.scrimLightHighContrast,
    inverseSurface = colorSuite.inverseSurfaceLightHighContrast,
    inverseOnSurface = colorSuite.inverseOnSurfaceLightHighContrast,
    inversePrimary = colorSuite.inversePrimaryLightHighContrast,
    surfaceDim = colorSuite.surfaceDimLightHighContrast,
    surfaceBright = colorSuite.surfaceBrightLightHighContrast,
    surfaceContainerLowest = colorSuite.surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = colorSuite.surfaceContainerLowLightHighContrast,
    surfaceContainer = colorSuite.surfaceContainerLightHighContrast,
    surfaceContainerHigh = colorSuite.surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = colorSuite.surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = colorSuite.primaryDarkMediumContrast,
    onPrimary = colorSuite.onPrimaryDarkMediumContrast,
    primaryContainer = colorSuite.primaryContainerDarkMediumContrast,
    onPrimaryContainer = colorSuite.onPrimaryContainerDarkMediumContrast,
    secondary = colorSuite.secondaryDarkMediumContrast,
    onSecondary = colorSuite.onSecondaryDarkMediumContrast,
    secondaryContainer = colorSuite.secondaryContainerDarkMediumContrast,
    onSecondaryContainer = colorSuite.onSecondaryContainerDarkMediumContrast,
    tertiary = colorSuite.tertiaryDarkMediumContrast,
    onTertiary = colorSuite.onTertiaryDarkMediumContrast,
    tertiaryContainer = colorSuite.tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = colorSuite.onTertiaryContainerDarkMediumContrast,
    error = colorSuite.errorDarkMediumContrast,
    onError = colorSuite.onErrorDarkMediumContrast,
    errorContainer = colorSuite.errorContainerDarkMediumContrast,
    onErrorContainer = colorSuite.onErrorContainerDarkMediumContrast,
    background = colorSuite.backgroundDarkMediumContrast,
    onBackground = colorSuite.onBackgroundDarkMediumContrast,
    surface = colorSuite.surfaceDarkMediumContrast,
    onSurface = colorSuite.onSurfaceDarkMediumContrast,
    surfaceVariant = colorSuite.surfaceVariantDarkMediumContrast,
    onSurfaceVariant = colorSuite.onSurfaceVariantDarkMediumContrast,
    outline = colorSuite.outlineDarkMediumContrast,
    outlineVariant = colorSuite.outlineVariantDarkMediumContrast,
    scrim = colorSuite.scrimDarkMediumContrast,
    inverseSurface = colorSuite.inverseSurfaceDarkMediumContrast,
    inverseOnSurface = colorSuite.inverseOnSurfaceDarkMediumContrast,
    inversePrimary = colorSuite.inversePrimaryDarkMediumContrast,
    surfaceDim = colorSuite.surfaceDimDarkMediumContrast,
    surfaceBright = colorSuite.surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = colorSuite.surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = colorSuite.surfaceContainerLowDarkMediumContrast,
    surfaceContainer = colorSuite.surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = colorSuite.surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = colorSuite.surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = colorSuite.primaryDarkHighContrast,
    onPrimary = colorSuite.onPrimaryDarkHighContrast,
    primaryContainer = colorSuite.primaryContainerDarkHighContrast,
    onPrimaryContainer = colorSuite.onPrimaryContainerDarkHighContrast,
    secondary = colorSuite.secondaryDarkHighContrast,
    onSecondary = colorSuite.onSecondaryDarkHighContrast,
    secondaryContainer = colorSuite.secondaryContainerDarkHighContrast,
    onSecondaryContainer = colorSuite.onSecondaryContainerDarkHighContrast,
    tertiary = colorSuite.tertiaryDarkHighContrast,
    onTertiary = colorSuite.onTertiaryDarkHighContrast,
    tertiaryContainer = colorSuite.tertiaryContainerDarkHighContrast,
    onTertiaryContainer = colorSuite.onTertiaryContainerDarkHighContrast,
    error = colorSuite.errorDarkHighContrast,
    onError = colorSuite.onErrorDarkHighContrast,
    errorContainer = colorSuite.errorContainerDarkHighContrast,
    onErrorContainer = colorSuite.onErrorContainerDarkHighContrast,
    background = colorSuite.backgroundDarkHighContrast,
    onBackground = colorSuite.onBackgroundDarkHighContrast,
    surface = colorSuite.surfaceDarkHighContrast,
    onSurface = colorSuite.onSurfaceDarkHighContrast,
    surfaceVariant = colorSuite.surfaceVariantDarkHighContrast,
    onSurfaceVariant = colorSuite.onSurfaceVariantDarkHighContrast,
    outline = colorSuite.outlineDarkHighContrast,
    outlineVariant = colorSuite.outlineVariantDarkHighContrast,
    scrim = colorSuite.scrimDarkHighContrast,
    inverseSurface = colorSuite.inverseSurfaceDarkHighContrast,
    inverseOnSurface = colorSuite.inverseOnSurfaceDarkHighContrast,
    inversePrimary = colorSuite.inversePrimaryDarkHighContrast,
    surfaceDim = colorSuite.surfaceDimDarkHighContrast,
    surfaceBright = colorSuite.surfaceBrightDarkHighContrast,
    surfaceContainerLowest = colorSuite.surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = colorSuite.surfaceContainerLowDarkHighContrast,
    surfaceContainer = colorSuite.surfaceContainerDarkHighContrast,
    surfaceContainerHigh = colorSuite.surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = colorSuite.surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun Flow1000Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        mediumContrastDarkColorScheme
    } else {
        mediumContrastLightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        shapes = shapes,
        content = content
    )
}
