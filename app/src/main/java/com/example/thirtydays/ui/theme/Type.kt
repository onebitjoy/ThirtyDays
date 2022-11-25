package com.example.thirtydays.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.thirtydays.R


val fontFamilyCarterOne = FontFamily(
    Font(resId = R.font.carterone_regular)
)

val fontFamilyCabin = FontFamily(
    listOf(
        Font(resId = R.font.cabin_bold, FontWeight.Bold),
        Font(resId = R.font.cabin_regular, FontWeight.Normal)
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = fontFamilyCarterOne,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    ),

    h2 = TextStyle(
        fontFamily = fontFamilyCabin,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),

    h3 = TextStyle(
        fontFamily = fontFamilyCabin,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),

    body1 = TextStyle(
        fontFamily = fontFamilyCabin,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    overline = TextStyle(
        fontFamily = fontFamilyCabin,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
    )
)