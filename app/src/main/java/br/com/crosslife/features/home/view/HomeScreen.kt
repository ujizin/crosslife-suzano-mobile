package br.com.crosslife.features.home.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.crosslife.R
import br.com.crosslife.components.input.TextField
import br.com.crosslife.extensions.capitalize
import br.com.crosslife.ui.theme.Space
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun NavController.HomeScreen() {
    val searchState = remember { mutableStateOf("") }
    Column(Modifier.fillMaxSize()) {
        Logo()
        TextFieldSearch(searchState)
        WeeklyTrain()
        Notices()
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
                .size(48.dp),
            painter = painterResource(
                id = R.drawable.crosslife_logo),
            contentDescription = stringResource(id = R.string.app_logo),
        )
    }
}

@Composable // TODO Change type from TextField
fun TextFieldSearch(searchState: MutableState<String>) {
    TextField(
        modifier = Modifier
            .padding(top = Space.M)
            .padding(horizontal = Space.BORDER),
        label = stringResource(id = R.string.search),
        state = searchState,
    )
}

@Composable
@ExperimentalPagerApi
fun WeeklyTrain() {
    val pagerState = rememberPagerState(
        pageCount = 7,
        infiniteLoop = true,
    )
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
        Box(
            Modifier
                .align(Alignment.CenterStart)
                .padding(start = Space.BORDER)
                .fillMaxWidth(0.8F)
                .height(200.dp)
                .background(Color.Red),
        ) {
            Text("${pagerState.currentPage}")
        }
    }
}


@Composable
fun Notices() {
    Text(
        modifier = Modifier
            .padding(top = Space.XXXS)
            .padding(horizontal = Space.BORDER),
        text = stringResource(id = R.string.notices).capitalize(),
        style = MaterialTheme.typography.h3,
    )
}
