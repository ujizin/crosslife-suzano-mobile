package br.com.crosslife.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.theme.Space

@Composable
fun ProfileItem(
    color: Color,
    image: Painter,
    description: String,
    onClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = Space.XXS)
            .clip(CircleShape)
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(Space.XS),
    ) {
        Box(
            Modifier.size(48.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = image,
                contentDescription = "Icon - $description",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(color)
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .alpha(0.1F)
                    .background(color, CircleShape),
            )
        }
        Text(
            text = description.capitalize(),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = Space.XXS)
        )
    }
}
