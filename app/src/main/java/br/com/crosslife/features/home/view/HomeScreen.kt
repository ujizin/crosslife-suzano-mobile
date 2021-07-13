package br.com.crosslife.features.home.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.ui.theme.DarkGray
import br.com.crosslife.ui.theme.Gray
import br.com.crosslife.ui.theme.Space
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun NavController.HomeScreen() {
    val searchState = remember { mutableStateOf("") }
    val pagerState = rememberPagerState(
        pageCount = 7,
        infiniteLoop = true,
        initialOffscreenLimit = 2,
    )
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(bottom = Space.BOTTOM_NAVIGATION),
    ) {
        item { Logo() }
        item { TextFieldSearch(searchState) }
        item { WeeklyTrain(pagerState) }
        Notices()
        item { Spacer(Modifier.height(Space.BORDER)) }
    }
}

@Composable
fun Logo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Space.BORDER),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            modifier = Modifier
                .padding(top = Space.XXS)
                .size(56.dp, 52.dp),
            painter = painterResource(
                id = R.drawable.crosslife_logo),
            contentDescription = stringResource(id = R.string.app_logo),
        )
    }
}

@Composable // TODO Change type from TextField
fun TextFieldSearch(searchState: MutableState<String>) {
    TextField(
        value = searchState.value,
        onValueChange = {
            searchState.value = it
        },
        singleLine = true,
        modifier = Modifier
            .padding(top = Space.XXXS)
            .padding(horizontal = Space.BORDER)
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search).capitalize(),
                color = Gray,
                modifier = Modifier.alpha(0.4F),
                style = MaterialTheme.typography.body1
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            placeholderColor = Gray,
        ),
        shape = MaterialTheme.shapes.medium,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = stringResource(id = R.string.search),
                modifier = Modifier.size(32.dp)
            )
        }
    )
}

@Composable
@ExperimentalPagerApi
fun WeeklyTrain(pagerState: PagerState) {
    Text(
        modifier = Modifier
            .padding(top = Space.XS)
            .padding(horizontal = Space.BORDER),
        text = stringResource(id = R.string.weekly_train).capitalize(),
        style = MaterialTheme.typography.h3,
    )
    HorizontalPager(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Space.XXS),
        horizontalAlignment = Alignment.Start,
        state = pagerState,
    ) {
        Card(
            Modifier
                .align(Alignment.CenterStart)
                .padding(start = Space.BORDER)
                .fillMaxWidth(0.8F)
                .height(200.dp),
            backgroundColor = MaterialTheme.colors.surface,
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.padding(start = Space.XXS, bottom = Space.XXS)
            ) {
                Text("Segunda-Feira", style = MaterialTheme.typography.h3)
                Text(
                    "Flexão de braço",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}

fun LazyListScope.Notices() {
    item {
        Text(
            modifier = Modifier
                .padding(top = Space.XXXS)
                .padding(horizontal = Space.BORDER),
            text = stringResource(id = R.string.notices).capitalize(),
            style = MaterialTheme.typography.h3,
        )
    }
    items(10) {
        Notice()
    }
}

@Composable
fun Notice() {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = Space.BORDER)
            .padding(top = Space.XXS)
            .wrapContentHeight(),
    ) {
        Box(
            Modifier
                .size(120.dp, 80.dp)
                .background(MaterialTheme.colors.surface, MaterialTheme.shapes.medium)
        )
        Column(
            Modifier
                .padding(start = Space.XXS)
                .height(80.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Título",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body1,
            )
            Text(
                text = "de: Lucas Yuji Yoshimine",
                style = MaterialTheme.typography.body2,
                color = DarkGray,
            )
            Text(
                text = "16/04/2021 - hoje",
                style = MaterialTheme.typography.body2,
                color = DarkGray,
            )
        }
    }
}