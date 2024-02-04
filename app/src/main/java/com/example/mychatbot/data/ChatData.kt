package com.example.mychatbot.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ResponseStoppedException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.lang.Exception

object ChatData {

    private const val api_Key = "AIzaSyBHlvVqm3aqZ52OK9joLz_j7OSpRfn_ujo"

    suspend fun getResponse (prompt: String) : Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro", apiKey = api_Key
        )
        try {
            val response = withContext(Dispatchers.IO){
                generativeModel.generateContent(prompt)
            }
            return Chat(
                prompt = response.text?: "error",
                bitmap = null,
                isFromUser = false
            )

        }catch (e:Exception){
            return Chat(
                prompt = e.message?:"error",
                bitmap = null,
                isFromUser = false
            )
        }
    }
    suspend fun getResponseWithImage(prompt: String,bitmap: Bitmap) : Chat {
        val generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision", apiKey = api_Key
        )
        try {
            val inputContent = content {
                image(bitmap)
                text(prompt)
            }
            val response = withContext(Dispatchers.IO){
                generativeModel.generateContent(inputContent)
            }
            return Chat(
                prompt = response.text?: "error",
                bitmap = null,
                isFromUser = false
            )

        }catch (e:Exception){
            return Chat(
                prompt = e.message?:"error",
                bitmap = null,
                isFromUser = false
            )
        }
    }
}