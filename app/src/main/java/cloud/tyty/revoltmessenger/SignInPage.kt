package cloud.tyty.revoltmessenger

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import api.session.Response
import kotlinx.coroutines.coroutineScope
import kotlinx.serialization.json.Json
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import kotlin.coroutines.suspendCoroutine
import kotlin.math.log



@Preview
@Composable
fun LoginPage(){

    val session = api.session.API()
    var loginClick by remember {
        mutableStateOf(false)
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var loginSuccessStatus by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = email,
            onValueChange = { email = it.trim() },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.padding(5.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(
            onClick = {
                loginClick = !loginClick
            },
            modifier = Modifier.width(120.dp)
        )
        {
            Text("Login")
        }
        TextButton(onClick = { /*TODO*/ }) {
            Text("Create Account")
        }
        Text(text = loginSuccessStatus)
    }
    LaunchedEffect(loginClick) {
        val response = session.login(email = email, password = password, friendlyName = "Android")

        if (response != null) {
            loginSuccessStatus = response.result
        }
    }
}


