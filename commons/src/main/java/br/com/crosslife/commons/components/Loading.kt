package br.com.crosslife.commons.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loading() {
    Box(
        Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp)
            .aspectRatio(1.75F),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}
