package com.nuvio.tv.ui.theme

import androidx.compose.ui.graphics.Color
import com.nuvio.tv.domain.model.AppTheme
import com.nuvio.tv.domain.model.CustomThemeColors

data class ThemeColorPalette(
    val secondary: Color,
    val secondaryVariant: Color,
    val onSecondary: Color = NuvioPrimitives.white,
    val onSecondaryVariant: Color = NuvioPrimitives.white,
    val accentGradient: List<Color> = listOf(secondary),
    val focusRing: Color,
    val focusRingGradient: List<Color> = listOf(focusRing),
    val focusBackground: Color,
    val background: Color = NuvioPrimitives.neutral950,
    val backgroundElevated: Color = NuvioPrimitives.neutral900,
    val backgroundCard: Color = NuvioPrimitives.neutral825,
    val surface: Color = NuvioPrimitives.neutral875,
    val surfaceVariant: Color = NuvioPrimitives.neutral800,
    val panel: Color = NuvioPrimitives.neutral900,
    val overlay: Color = Color(0xD9000000),
    val field: Color = NuvioPrimitives.neutral850,
    val menu: Color = NuvioPrimitives.neutral875,
    val modal: Color = NuvioPrimitives.neutral900,
    val playerOverlay: Color = Color(0xCC000000)
)

object ThemeColors {
    val Crimson = ThemeColorPalette(
        secondary = NuvioPrimitives.red500,
        secondaryVariant = NuvioPrimitives.red600,
        focusRing = NuvioPrimitives.red300,
        focusBackground = Color(0xFF3D1A1A),
        backgroundCard = Color(0xFF241A1A)
    )

    val Ocean = ThemeColorPalette(
        secondary = NuvioPrimitives.blue500,
        secondaryVariant = NuvioPrimitives.blue700,
        focusRing = NuvioPrimitives.blue300,
        focusBackground = Color(0xFF1A2D3D),
        background = Color(0xFF0D0D0F),
        backgroundElevated = Color(0xFF1A1A1E),
        backgroundCard = Color(0xFF1A1F24)
    )

    // Reel Room: the Violet slot is restyled as the Ethereal theme (twilight violet with opal highlights).
    val Violet = ThemeColorPalette(
        secondary = Color(0xFFB9A6FF),
        secondaryVariant = Color(0xFF7D63D9),
        onSecondary = Color(0xFF1B1530),
        onSecondaryVariant = NuvioPrimitives.white,
        accentGradient = listOf(Color(0xFFCFE9FF), Color(0xFFE7DCFF), Color(0xFFFFD9F0)),
        focusRing = Color(0xFFE7DCFF),
        focusRingGradient = listOf(Color(0xFFCFE9FF), Color(0xFFE7DCFF), Color(0xFFFFD9F0)),
        focusBackground = Color(0xFF2A2150),
        background = Color(0xFF0E0B1A),
        backgroundElevated = Color(0xFF18132B),
        backgroundCard = Color(0xFF1F1938),
        surface = Color(0xFF1A1530),
        surfaceVariant = Color(0xFF241D40),
        panel = Color(0xFF15112A),
        field = Color(0xFF211B3A),
        menu = Color(0xFF1A1530),
        modal = Color(0xFF15112A)
    )

    val Emerald = ThemeColorPalette(
        secondary = NuvioPrimitives.green500,
        secondaryVariant = NuvioPrimitives.green700,
        focusRing = NuvioPrimitives.green300,
        focusBackground = Color(0xFF1A3D1E),
        backgroundCard = Color(0xFF1A241A)
    )

    val Amber = ThemeColorPalette(
        secondary = NuvioPrimitives.amber500,
        secondaryVariant = NuvioPrimitives.amber700,
        focusRing = NuvioPrimitives.amber300,
        focusBackground = Color(0xFF3D2D1A),
        background = Color(0xFF0F0D0D),
        backgroundElevated = Color(0xFF1E1A1A),
        backgroundCard = Color(0xFF24201A)
    )

    val Rose = ThemeColorPalette(
        secondary = NuvioPrimitives.rose500,
        secondaryVariant = NuvioPrimitives.rose700,
        focusRing = NuvioPrimitives.rose300,
        focusBackground = Color(0xFF3D1A2D),
        backgroundCard = Color(0xFF241A1F)
    )

    val White = ThemeColorPalette(
        secondary = NuvioPrimitives.neutral100,
        secondaryVariant = NuvioPrimitives.neutral200,
        onSecondary = NuvioPrimitives.neutral925,
        onSecondaryVariant = NuvioPrimitives.neutral925,
        focusRing = NuvioPrimitives.white,
        focusBackground = Color(0xFF303030),
        backgroundCard = NuvioPrimitives.neutral850
    )

    fun getColorPalette(
        theme: AppTheme,
        customColors: CustomThemeColors = CustomThemeColors.Default
    ): ThemeColorPalette {
        return when (theme) {
            AppTheme.CUSTOM -> customColors.toColorPalette()
            AppTheme.GOLD -> SupporterThemeColors.Gold
            AppTheme.JADE -> SupporterThemeColors.Jade
            AppTheme.ROSE_GOLD -> SupporterThemeColors.RoseGold
            AppTheme.ARCTIC_BLUE -> SupporterThemeColors.ArcticBlue
            AppTheme.GRAPHITE -> SupporterThemeColors.Graphite
            AppTheme.CRIMSON -> Crimson
            AppTheme.OCEAN -> Ocean
            AppTheme.VIOLET -> Violet
            AppTheme.EMERALD -> Emerald
            AppTheme.AMBER -> Amber
            AppTheme.ROSE -> Rose
            AppTheme.WHITE -> White
        }
    }
}
