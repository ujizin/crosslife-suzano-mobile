package br.com.crosslife.ui.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import br.com.crosslife.R
import br.com.crosslife.components.Button
import br.com.crosslife.ui.theme.Space

@Composable
fun Error(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.default_error_message),
) {
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error),
            contentDescription = "Error"
        )
        Text(
            modifier = Modifier.padding(vertical = Space.XXS),
            text = message,
        )
        Button(
            modifier = Modifier.padding(top = Space.XS),
            textButton = stringResource(id = R.string.try_again),
            onClick = {

            }
        )
    }
}