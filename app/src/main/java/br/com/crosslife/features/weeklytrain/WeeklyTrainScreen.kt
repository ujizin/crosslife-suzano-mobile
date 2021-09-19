package br.com.crosslife.features.weeklytrain

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.com.crosslife.domain.models.DetailItem
import br.com.crosslife.ui.components.bottombox.BottomBox
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar
import br.com.crosslife.ui.theme.Space

@ExperimentalAnimationApi
@Composable
fun NavController.DetailScreen(detail: DetailItem?) {
    val item = detail ?: return run { navigateUp() }
    ScaffoldTopbar(actions = { DetailActions() }) {
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


// TODO should be deleted, only using 'cause of AC02
@Composable
fun DetailActions() {
    var showMenu by remember { mutableStateOf(false) }
    IconButton(onClick = {}) {
        Icon(imageVector = Icons.Filled.Share,
            contentDescription = "compartilhar",
            tint = Color.White)
    }
    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "menu kebab",
            tint = Color.White,
        )
    }
    DropdownMenu(
        expanded = showMenu,
        modifier = Modifier.fillMaxWidth(0.5F),
        onDismissRequest = { showMenu = false }
    ) {
        DropdownMenuItem(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Refresh,
                contentDescription = "atualizar",
                tint = Color.White,
            )
            Text("Atualizar")
        }
        DropdownMenuItem(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = "configuração",
                tint = Color.White,
            )
            Text("Configuração")
        }
    }
}