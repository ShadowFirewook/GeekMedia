package com.example.geekmedia.core

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

val REGISTERED_USER = booleanPreferencesKey("registered user")
val USER_AVATAR = stringPreferencesKey("user avatar")
val USER_BIO = stringPreferencesKey("user bio")
val LIKED_NEWS =  booleanPreferencesKey("liked news")