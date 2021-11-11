package br.com.crosslife.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.profile.R

@Composable
fun ProfileBackground() {
    Box(
        Modifier
            .fillMaxSize()
            .alpha(0.5F)
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = stringResource(id = R.string.background),
            contentScale = ContentScale.Crop,
        )
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(Space.BOTTOM_NAVIGATION)
                .background(MaterialTheme.colors.background)
        )
    }
}
