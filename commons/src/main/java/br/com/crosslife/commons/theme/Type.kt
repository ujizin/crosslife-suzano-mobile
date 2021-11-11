package br.com.crosslife.commons.theme

import androidx.compose.material.Colors
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.crosslife.commons.R

val PoppinsFamily = FontFamily(
    Font(R.font.poppinsbold, weight = FontWeight.Bold),
    Font(R.font.poppinsregular, weight = FontWeight.Normal),
)

fun getTypography(colors: Colors) = Typography(
    h1 = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = colors.onPrimary
    ),
    h2 = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 34.sp,
        fontWeight = FontWeight.Bold,
        color = colors.onPrimary
    ),
    h3 = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = colors.onPrimary
    ),
    subtitle1 = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = colors.onPrimary
    ),
    body1 = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary
    ),
    body2 = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary
    ),
    caption = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary,
    ),
    button = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary,
    ),
    overline = TextStyle(
        fontFamily = PoppinsFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        color = colors.onPrimary,
    ),
)