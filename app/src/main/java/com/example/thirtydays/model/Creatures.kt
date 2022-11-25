package com.example.thirtydays.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Creatures(
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val origin: Int,
    @DrawableRes val image: Int,
)
