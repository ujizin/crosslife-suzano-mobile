package br.com.crosslife.features.profile.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.ui.components.topbar.ScaffoldTopbar

@ExperimentalAnimationApi
@Composable
fun NavController.DetailProfileScreen() {
    ScaffoldTopbar(titleRes = R.string.advanced_profile, actions = { DetailProfileActions() }) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Em construção", style = MaterialTheme.typography.h3)
        }
    }
}

// TODO should be deleted, only using 'cause of AC02
@Composable
fun DetailProfileActions() {
    var showMenu by remember { mutableStateOf(false) }
    IconButton(onClick = {}) {
        Icon(imageVector = Icons.Filled.Share, contentDescription = "compartilhar")
    }
    IconButton(onClick = { showMenu = !showMenu }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "menu kebab")
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