package br.com.crosslife.chat.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.Button
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.extensions.navigate
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.domain.model.Instructor
import br.com.crosslife.domain.model.InstructorOne
import br.com.crosslife.domain.model.InstructorTwo
import br.com.crosslife.navigation.Screen
import coil.compose.rememberImagePainter

@Composable
fun NavController.ChatScreen() {
    Column(
        Modifier
            .fillMaxHeight()
            .padding(horizontal = Space.BORDER)
            .verticalScroll(rememberScrollState())
    ) {
        ChatHeader()
        ChatBody()
    }
}

@Composable
private fun ChatHeader() {
    Text(
        text = stringResource(id = R.string.menu_item_chat).capitalize(),
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(top = Space.XXS)
    )
    Text(
        text = stringResource(id = R.string.chat_description).capitalize(),
        style = MaterialTheme.typography.body1,
        modifier = Modifier.padding(top = Space.XXS)
    )
}

@Composable
private fun NavController.ChatBody() {
    InstructorCard(Modifier.padding(top = Space.XM, bottom = Space.XXXS), InstructorOne)
    ChatDivider()
    InstructorCard(Modifier.padding(top = Space.XXXS, bottom = Space.XXXS), InstructorTwo)
}

@Composable
fun NavController.InstructorCard(modifier: Modifier, instructor: Instructor) {
    Card(
        modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
    ) {
        Row(
            Modifier
                .padding(Space.XXS)
                .height(120.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberImagePainter(instructor.imageUrl),
                contentDescription = instructor.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1F)
                    .background(Color.Black, MaterialTheme.shapes.medium)
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = Space.XXS)
            ) {
                Text(instructor.name, style = MaterialTheme.typography.h3)
                Text(
                    stringResource(id = R.string.instructor, instructor.number),
                    style = MaterialTheme.typography.body2
                )
                Spacer(Modifier.weight(1F))
                Button(
                    Modifier
                        .align(Alignment.End)
                        .defaultMinSize(minWidth = 80.dp),
                    textButton = stringResource(id = R.string.menu_item_chat).capitalize(),
                    onClick = {
                        currentBackStackEntry?.arguments?.putParcelable(
                            Instructor.INSTRUCTOR_ARG_KEY,
                            instructor
                        )
                        navigate(Screen.Conversation)
                    }
                )
            }
        }
    }
}

@Composable
fun ChatDivider() {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .background(MaterialTheme.colors.surface)
                .weight(1F)
                .height(1.dp)
        )
        Text(
            text = stringResource(id = R.string.or).uppercase(),
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(horizontal = Space.XS)
                .background(MaterialTheme.colors.background)
        )
        Box(
            Modifier
                .background(MaterialTheme.colors.surface)
                .weight(1F)
                .height(1.dp)
        )
    }
}
