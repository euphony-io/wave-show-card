package com.euphony.waveshowcardreceiver

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import kotlin.math.log

class MainActivity : ComponentActivity() {

    companion object { const val PERMISSION_REQUEST_CODE = 17389 }
    private var isListening = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO,
                                                        Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)

        setContent {
            WaveShowCardReceiverTheme {

                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colorScheme.primary) {

                    Box1()

                }
            }
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==PERMISSION_REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){

        }
    }

}
@Composable
fun Box1(){
    var text  = remember { mutableStateOf("화면을 터치하세요")  }
    var text2 = remember { mutableStateOf("hello,euphony!") }

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
            text.value, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 350.dp), textAlign = TextAlign.Center)
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .clickable {
                    text.value = "음파신호를 수신 중 입니다... "

                }
                .padding(all = 300.dp),
            ){

        }

        Text(text2.value,
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

