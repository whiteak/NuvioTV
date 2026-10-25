package com.nuvio.tv.data.remote.supabase

// Ethereal: avatars that ship with this edition (Requiem's apply.sh turns them off).
// The Nuvio server only knows its own avatar ids, so these are synced as an avatar URL,
// which the phone app and other devices show like any custom avatar.
const val EDITION_AVATARS_ENABLED = true

private const val EditionAvatarBaseUrl =
    "https://raw.githubusercontent.com/whiteak/NuvioMobile/cmp-rewrite/editions/ethereal/avatars"

val EDITION_AVATARS: List<AvatarCatalogItem> = if (EDITION_AVATARS_ENABLED) {
    listOf(
        AvatarCatalogItem(
            id = "edition-mn",
            displayName = "MN",
            imageUrl = "$EditionAvatarBaseUrl/mn.png",
            category = "edition",
            sortOrder = 0,
            bgColor = "#FFFFFF"
        )
    )
} else {
    emptyList()
}

fun editionAvatar(id: String?): AvatarCatalogItem? =
    id?.let { EDITION_AVATARS.firstOrNull { avatar -> avatar.id == it } }

fun editionAvatarForUrl(url: String?): AvatarCatalogItem? =
    url?.trim()?.let { EDITION_AVATARS.firstOrNull { avatar -> avatar.imageUrl == it } }
