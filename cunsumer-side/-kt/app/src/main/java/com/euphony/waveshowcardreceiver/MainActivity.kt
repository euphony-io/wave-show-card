package com.euphony.waveshowcardreceiver

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.euphony.waveshowcardreceiver.ui.theme.WaveShowCardReceiverTheme
import androidx.compose.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission

import euphony.lib.receiver.AcousticSensor
import euphony.lib.receiver.EuRxManager

class MainActivity : ComponentActivity() {
    companion object {
        const val PERMISSION_REQUEST_CODE = 17389
    }
    private var isListening = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WaveShowCardReceiverTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.primary) {
                    ChangeText()
                    receiver()
                }
            }
        }
    }
}




@Composable
fun ChangeText(){
    var text by remember { mutableStateOf("화면을 터치하세요") }

    Text(
        text, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable { text = "음파신호를 수신 중 입니다. " }
            .padding(all = 300.dp),
        color = MaterialTheme.colorScheme.surface) {


    }

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
        ChangeText()
    }
}