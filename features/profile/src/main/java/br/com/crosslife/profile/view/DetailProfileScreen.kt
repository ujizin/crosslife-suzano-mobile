package br.com.crosslife.profile.view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.crosslife.commons.R
import br.com.crosslife.commons.components.Loading
import br.com.crosslife.commons.components.error.Error
import br.com.crosslife.commons.components.topbar.ScaffoldTopbar
import br.com.crosslife.commons.extensions.capitalize
import br.com.crosslife.commons.extensions.rememberFlowWithLifecycle
import br.com.crosslife.commons.theme.Space
import br.com.crosslife.commons.utils.BioimpedanceUtil.Format.Kilos
import br.com.crosslife.commons.utils.BioimpedanceUtil.Format.Milliliters
import br.com.crosslife.commons.utils.BioimpedanceUtil.format
import br.com.crosslife.domain.model.DetailProfile
import br.com.crosslife.domain.model.Result
import br.com.crosslife.profile.viewmodel.DetailProfileViewModel

@ExperimentalAnimationApi
@Composable
fun NavController.DetailProfileScreen(viewModel: DetailProfileViewModel = hiltViewModel()) {
    val detailProfileState by rememberFlowWithLifecycle(viewModel.profileState)
        .collectAsState(initial = Result.Initial)

    ScaffoldTopbar(title = stringResource(id = R.string.advanced_profile)) {
        when (val state: Result<DetailProfile> = detailProfileState) {
            Result.Initial, Result.Loading -> Loading()
            is Result.Error -> Error {
                viewModel.getDetailProfile()
            }
            is Result.Success -> DetailProfileUI(state.data)
        }
    }
}

@Composable
private fun DetailProfileUI(detailProfile: DetailProfile) {
    with(detailProfile) {
        Column(Modifier.padding(top = Space.XXS)) {
            DetailProfileItem(stringResource(R.string.fat_mass), format(fatMass, Kilos))
            DetailProfileItem(stringResource(R.string.slim_mass), format(slimMass, Kilos))
            DetailProfileItem(stringResource(R.string.muscle_mass), format(muscleMass, Kilos))
            DetailProfileItem(stringResource(R.string.hydration), format(hydration, Milliliters))
            DetailProfileItem(stringResource(R.string.bone_density), format(boneDensity, Kilos))
            DetailProfileItem(stringResource(R.string.visceral_fat), format(visceralFat, Kilos))
            DetailProfileItem(
                stringResource(R.string.basal_metabolism),
                format(basalMetabolism, Kilos)
            )
        }
    }
}

@Composable
private fun DetailProfileItem(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Space.BORDER)
            .padding(bottom = Space.XXS),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            title.capitalize(),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
        Text(
            value.uppercase(),
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold
        )
    }
}