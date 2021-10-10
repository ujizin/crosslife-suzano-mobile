package br.com.crosslife.features.detailscreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import br.com.crosslife.domain.models.DetailItem
import br.com.crosslife.ui.components.bottombox.BottomBox
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar
import br.com.crosslife.ui.theme.Space
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@ExperimentalAnimationApi
@Composable
fun NavController.DetailScreen(detail: DetailItem?) {
    val item = detail ?: return run { navigateUp() }
    ScaffoldTopbar(overlapTopBar = true) {
        Image(
            painter = rememberImagePainter(detail.imageUrl),
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7F),
        )
        Box(
            modifier = Modifier
                .background(brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.5F),
                        Color.Black.copy(alpha = 0.25F),
                    ),
                    startY = 0.4f,
                ))
                .fillMaxWidth()
                .fillMaxHeight(0.7F)
        )
        BottomBox(title = item.title, modifier = Modifier.fillMaxHeight(0.45F)) {
            item.subTitle?.let { subTitle ->
                Text(
                    text = subTitle,
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(vertical = Space.XXXS),
                )
            }
            Text(
                text = item.description,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}
