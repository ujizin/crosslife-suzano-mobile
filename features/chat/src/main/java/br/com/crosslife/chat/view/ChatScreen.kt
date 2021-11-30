package br.com.crosslife.chat.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.chat.viewmodel.ChatViewModel
import br.com.crosslife.chat.viewmodel.ChatViewState
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.Button
import br.com.crosslife.commons.components.Loading
import br.com.crosslife.commons.components.error.Error
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.commons.theme.Gray
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.domain.model.Conversation
import br.com.crosslife.domain.model.Instructor
import br.com.crosslife.domain.model.InstructorOne
import br.com.crosslife.domain.model.InstructorTwo
import br.com.crosslife.navigation.Screen
import coil.compose.rememberImagePainter

@Composable
fun NavController.ChatScreen(viewModel: ChatViewModel = hiltViewModel()) {
    val chatState by rememberFlowWithLifecycle(viewModel.chatViewState)
        .collectAsState(initial = ChatViewState.Initial)

    when (val state: ChatViewState = chatState) {
        ChatViewState.Initial -> Loading()
        ChatViewState.Error -> Error {
            viewModel.syncChat()
        }
        is ChatViewState.Instructor -> ChatInstructorScreen(state.conversation)
        ChatViewState.User -> ChatUserScreen()
    }
}

@Composable
fun NavController.ChatInstructorScreen(conversations: List<Conversation>) {
    LazyColumn(Modifier.padding(horizontal = Space.BORDER)) {
        item { ChatHeader(stringResource(id = R.string.chat_instructor_description)) }
        items(conversations) { conversation ->
            ChatUserItem(conversation) {
                navigate(Screen.Conversation.getRoute(it.user))
            }
        }
    }
}

@Composable
private fun ChatUserItem(
    conversation: Conversation,
    onUserClick: (Conversation) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = Space.XXS)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.surface)
            .clickable { onUserClick(conversation) }
            .padding(Space.XXS),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1F)) {
            Text(
                text = conversation.user,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "- ${conversation.message}",
                style = MaterialTheme.typography.caption,
                color = Gray,
                modifier = Modifier.padding(top = Space.S)
            )
        }
        Icon(
            imageVector = Icons.Filled.ArrowRight,
            tint = MaterialTheme.colors.primary,
            contentDescription = "arrow",
            modifier = Modifier.scale(1.25F)
        )
    }
}

@Composable
private fun NavController.ChatUserScreen() {
    Column(
        Modifier
            .fillMaxHeight()
            .padding(horizontal = Space.BORDER)
            .verticalScroll(rememberScrollState())
    ) {
        ChatHeader(stringResource(id = R.string.chat_description))
        ChatBody()
    }
}

@Composable
private fun ChatHeader(description: String) {
    Text(
        text = stringResource(id = R.string.menu_item_chat).capitalize(),
        style = MaterialTheme.typography.h1,
        modifier = Modifier.padding(top = Space.XXS)
    )
    Text(
        text = description.capitalize(),
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
                        navigate(Screen.Conversation.getRoute(instructor.id))
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
