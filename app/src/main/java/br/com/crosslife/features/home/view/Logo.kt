package br.com.crosslife.features.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.crosslife.R
import br.com.crosslife.ui.theme.Space

@Composable
fun HomeLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Space.BORDER),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .padding(top = Space.XXS)
                .size(56.dp, 52.dp),
            painter = painterResource(
                id = R.drawable.crosslife_logo),
            contentDescription = stringResource(id = R.string.app_logo),
        )
    }
}
