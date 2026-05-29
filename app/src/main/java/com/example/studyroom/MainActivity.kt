package com.example.studyroom // Make sure this matches YOUR package name

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.studyroom.chat.ChatRoom
import com.example.studyroom.ui.theme.StudyRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            StudyRoomTheme {
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                ChatRoom()

            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    StudyRoomTheme {
//        Greeting("Android")
//    }
//}