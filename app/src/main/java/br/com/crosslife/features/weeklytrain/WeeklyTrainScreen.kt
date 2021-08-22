package br.com.crosslife.features.weeklytrain

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.com.crosslife.domain.models.DetailItem
import br.com.crosslife.ui.components.bottombox.BottomBox
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar
import br.com.crosslife.ui.theme.Space

@ExperimentalAnimationApi
@Composable
fun NavController.DetailScreen(detail: DetailItem?) {
    val item = detail ?: return run { navigateUp() }
    ScaffoldTopbar {
        BottomBox(title = item.title, modifier = Modifier.padding(horizontal = Space.BORDER)) {
            Text(
                text = item.subTitle,
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(vertical = Space.XXXS),
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.body2,
            )
        }
    }
}
