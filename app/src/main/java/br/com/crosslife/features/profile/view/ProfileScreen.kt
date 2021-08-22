package br.com.crosslife.features.profile.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.Screen
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.navigate
import br.com.crosslife.ui.components.bottombox.BottomBox
import br.com.crosslife.ui.theme.Purple
import br.com.crosslife.ui.theme.Red
import br.com.crosslife.ui.theme.Space
import br.com.crosslife.ui.theme.Yellow

@ExperimentalMaterialApi
@Composable
fun NavController.ProfileScreen() {
    ProfileBackground()
    BottomBox("Lucas Yuji") {
        ProfileItem(
            Purple,
            painterResource(id = R.drawable.ic_strength),
            stringResource(id = R.string.advanced_profile),
        ) {
            navigate(Screen.DetailProfile)
        }
        ProfileItem(
            Yellow,
            painterResource(id = R.drawable.ic_lock),
            stringResource(id = R.string.change_password),
        ) {
            navigate(Screen.ChangePassword)
        }
        ProfileItem(
            Red,
            painterResource(id = R.drawable.ic_exit),
            stringResource(id = R.string.leave_account),
        ) {
            navigate(Screen.Logout)
        }
    }
}

@Composable
fun ProfileBackground() {
    Box(
        Modifier
            .fillMaxSize()
            .alpha(0.5F)
            .background(MaterialTheme.colors.background)
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            modifier = Modifier.fillMaxSize(),
            contentDescription = stringResource(id = R.string.background),
            contentScale = ContentScale.Crop,
        )
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(Space.BOTTOM_NAVIGATION)
                .background(MaterialTheme.colors.background)
        )
    }
}

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
