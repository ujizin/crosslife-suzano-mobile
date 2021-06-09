package br.com.crosslife.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.crosslife.R

val NunitoFamily = FontFamily(
    Font(R.font.nunitobold, weight = FontWeight.Bold),
    Font(R.font.nunitoregular, weight = FontWeight.Normal),
)

fun getTypography(colors: Colors) = Typography(
    h1 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = colors.onPrimary
    ),
    h2 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        color = colors.onPrimary
    ),
    h3 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = colors.onPrimary
    ),
    subtitle1 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = colors.onPrimary
    ),
    body1 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary
    ),
    body2 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary
    ),
    caption = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary,
    ),
    button = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary,
    ),
    overline = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary,
    ),
)