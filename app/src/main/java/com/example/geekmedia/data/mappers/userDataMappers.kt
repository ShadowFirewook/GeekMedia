package com.example.geekmedia.data.mappers

import com.example.geekmedia.data.entities.UserDataEntity
import com.example.geekmedia.domain.models.UserData

fun UserDataEntity.toUserData() = UserData(
    first_name, last_name, password, phone
)

fun UserData.toUserDataEntity() = UserDataEntity(
    first_name, last_name, password, phone
)