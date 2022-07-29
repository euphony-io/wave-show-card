package com.euphony.waveshowcardreceiver

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import com.euphony.waveshowcardreceiver.ui.theme.WaveShowCardReceiverTheme
import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager

class MainActivity : ComponentActivity() {

    companion object { const val PERMISSION_REQUEST_CODE = 17389 }
    private var isListening = false
    private fun requestPermissions(): Boolean {
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)
            == PackageManager.PERMISSION_GRANTED
            && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        val permissions: Array<String> = arrayOf(
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        ActivityCompat.requestPermissions(this, permissions, 0)
        return false

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissions()


        setContent {
            WaveShowCardReceiverTheme {

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.primary) {

                    Box1()

                }
            }
        }


    }

}
@Composable
fun Box1(){
    var text by remember { mutableStateOf("화면을 터치하세요")  }
    var text2 by remember { mutableStateOf("hello,euphony!") }

    Box{
        Modifier.pointerInput(Unit) {
            detectTapGestures(
                onTap = {
                    //Todo
                    receiver()
                }
            )
        }
        Text(
            text, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 350.dp), textAlign = TextAlign.Center)
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    text = "음파신호를 수신 중 입니다... "

                }
                .padding(all = 300.dp),
            ){

        }

        Text(text2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 400.dp),
            textAlign = TextAlign.Center)


    }
}

@Composable
fun notify(letters: String){
    Text(letters)
}

fun receiver(){
    val mRxManager = EuRxManager()
    mRxManager.acousticSensor = AcousticSensor {
        //when data is received
    }
    mRxManager.listen() //Listening Start
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WaveShowCardReceiverTheme {
        Box1()
    }
}

