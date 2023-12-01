package np.com.santoshpant.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import np.com.santoshpant.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        var isinputtrue = remember { mutableStateOf(false) }
        var isoutputtrue = remember { mutableStateOf(false) }

        var ivalue = remember {
            mutableStateOf("Metres")
        }
        var ovalue = remember {
            mutableStateOf("Metres")
        }
        var inputvalue = remember {
            mutableStateOf("")
        }
        var outputvalue = remember {
            mutableStateOf("")
        }
        var conversionFactor = remember {
            mutableStateOf(1.00)
        }
        var oConvertorFactor = remember {
            mutableStateOf(1.00)
        }

        fun calculation(){
            val ourInput = inputvalue.value.toDoubleOrNull() ?: 0.00
            val result = (ourInput * conversionFactor.value * 100.0 / oConvertorFactor.value).roundToInt() /100.0
            outputvalue.value = result.toString()
        }
        Text(text = "Unit Converter: ")
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(value = inputvalue.value, onValueChange = {
            inputvalue.value = it
            calculation()
        }, label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(15.dp))
        Row {
//            val context = LocalContext.current

//            Button(onClick = { Toast.makeText(context , "I am clicked!!" , Toast.LENGTH_LONG ).show() }) {
//                Text(text = "Click Me!!")
//            }

            Box {
                Button(onClick = {
                    isinputtrue.value = true

                }) {
                    Text(text = ivalue.value)
                    Icon(Icons.Default.ArrowDropDown , contentDescription = "")
                }
                DropdownMenu(expanded = isinputtrue.value, onDismissRequest = { isinputtrue.value = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimetres") }, onClick = {
                        ivalue.value = "Centimetres"
                        isinputtrue.value = false
                        conversionFactor.value = 0.01
                        calculation()
                    })
                    DropdownMenuItem(text = { Text(text = "Metres") }, onClick = {
                        ivalue.value = "Metres"

                        isinputtrue.value = false
                        conversionFactor.value = 1.0
                        calculation()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        ivalue.value = "Feet"

                        isinputtrue.value = false
                        conversionFactor.value = 0.3048
                        calculation()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimetres") }, onClick = {
                        ivalue.value = "Millimetres"

                        isinputtrue.value = false
                        conversionFactor.value = 0.001
                        calculation()
                    })
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            Box {
                Button(onClick = {
                    isoutputtrue.value = true
                    calculation()

                }) {
                    Text(text = ovalue.value)
                    Icon(Icons.Default.ArrowDropDown , contentDescription = "")
                }
                DropdownMenu(expanded = isoutputtrue.value, onDismissRequest = { isoutputtrue.value = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimetres") }, onClick = {
                        ovalue.value = "Centimetres"
                        isoutputtrue.value = false
                        oConvertorFactor.value = 0.01
                        calculation()
                    })
                    DropdownMenuItem(text = { Text(text = "Metres") }, onClick = {
                        ovalue.value = "Metres"
                        isoutputtrue.value = false
                        oConvertorFactor.value = 1.0
                        calculation()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        ovalue.value = "Feet"
                        isoutputtrue.value = false
                        oConvertorFactor.value = 0.3048
                        calculation()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimetres") }, onClick = {
                        ovalue.value = "Millimetres"
                        isoutputtrue.value = false
                        oConvertorFactor.value = 0.001
                        calculation()
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "Results: ${outputvalue.value} ${ovalue.value}")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}