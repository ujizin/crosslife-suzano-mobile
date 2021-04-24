package br.com.crosslife.ui.theme

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
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
    ),
    h2 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
    ),
    h3 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    subtitle1 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    ),
    body1 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    body2 = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    caption = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
    ),
    button = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
    ),
    overline = TextStyle(
        fontFamily = NunitoFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
    ),
)