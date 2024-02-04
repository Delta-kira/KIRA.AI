package com.example.mychatbot

import android.graphics.Bitmap
import com.example.mychatbot.data.Chat

data class ChatState (
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null
)