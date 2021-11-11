package br.com.crosslife.commons.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.Button
import br.com.crosslife.commons.theme.Space

@Composable
fun Error(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.default_error_message),
    onClick: () -> Unit,
) {
    Column(
        Modifier
            .padding(Space.BORDER)
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = stringResource(id = R.string.error_logo)
        )
        Text(
            modifier = Modifier.padding(vertical = Space.XXS),
            text = message,
            textAlign = TextAlign.Center,
        )
        Button(
            modifier = Modifier.padding(top = Space.XS),
            textButton = stringResource(id = R.string.try_again),
            onClick = onClick
        )
    }
}