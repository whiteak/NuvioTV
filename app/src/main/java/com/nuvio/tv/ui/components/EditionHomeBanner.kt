package com.nuvio.tv.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.nuvio.tv.R

/** Ethereal: the edition's artwork banner across the top of Home (each edition ships its own image). */
@Composable
fun EditionHomeBanner(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.edition_home_banner),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Fit
    )
}
