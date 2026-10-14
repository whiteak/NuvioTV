package com.nuvio.tv.domain.model

// Ethereal: the theme an edition opens in until a theme is picked in Settings (Requiem sets WHITE).
val EDITION_DEFAULT_THEME = AppTheme.VIOLET

enum class AppTheme(val displayName: String) {
    CUSTOM("Custom"),
    GOLD("Gold"),
    JADE("Jade"),
    ROSE_GOLD("Rose Gold"),
    ARCTIC_BLUE("Arctic Blue"),
    GRAPHITE("Graphite"),
    CRIMSON("Crimson"),
    OCEAN("Ocean"),
    VIOLET("Violet"),
    EMERALD("Emerald"),
    AMBER("Amber"),
    ROSE("Rose"),
    WHITE("White")
}
