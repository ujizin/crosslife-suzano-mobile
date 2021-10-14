package br.com.crosslife.features.detailscreen

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalPlay
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.crosslife.components.Button
import br.com.crosslife.domain.models.DetailItem
import br.com.crosslife.ui.components.bottombox.BottomBox
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar
import br.com.crosslife.R
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
            DetailSubTitle(item.subTitle)
            Text(item.description, style = MaterialTheme.typography.body1)
            VideoButton(item.videoUrl)
        }
    }
}

@Composable
private fun DetailSubTitle(subTitle: String?) {
    subTitle?.let {
        Text(
            text = it,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(vertical = Space.XXXS),
        )
    }
}

@Composable
private fun VideoButton(videoUrl: String?) {
    val context = LocalContext.current
    videoUrl?.let {
        Button(
            modifier = Modifier.padding(vertical = Space.XXXS),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                context.startActivity(intent)
            }) {
            Icon(
                imageVector = Icons.Default.PlayArrow,
                modifier = Modifier.size(24.dp, 24.dp),
                contentDescription = stringResource(id = R.string.play)
            )
            Text(
                text = stringResource(R.string.video).uppercase(),
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = Space.S, end = Space.XS)
            )
        }
    }
}
