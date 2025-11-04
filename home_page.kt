package com.example.authentication_page

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun Home_page(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val context= LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth().background(color = Color.White)
    )
    {
        Row(
            modifier = Modifier.padding(start = 60.dp,top=70.dp,bottom=30.dp),
            //verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        )
        {
            Text(
                "  Firebase Authentication",
                fontFamily = FontFamily(androidx.compose.ui.text.font.Font(R.font.inria_sans_regular)),
                fontSize = 19.sp,
            )

        }
        Spacer(modifier =Modifier.height(10.dp))
        Row(
            modifier = Modifier.padding(top = 16.dp, start = 50.dp, end = 50.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Center

        ){
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Enter Gmail") },
                shape = RoundedCornerShape(10.dp)
            )
        }
        //Spacer(modifier =Modifier.height(2.dp))
        Row(
            modifier = Modifier.padding(top = 10.dp, start = 50.dp, end = 50.dp, bottom = 20.dp),
            horizontalArrangement = Arrangement.Center

        ){
            OutlinedTextField(
                value = password ,
                onValueChange = { password = it },
                label = { Text("Enter Password") },
                shape = RoundedCornerShape(16.dp),
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Row(
            modifier = Modifier.padding(top = 16.dp, start = 120.dp, end = 85.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Center

        ){
            Button(
                onClick = {
                    if(email.isEmpty()){
                        Toast.makeText(context, "Empty mail not allowed",
                            Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    if(password.isEmpty()){
                        Toast.makeText(context, "Empty password not allowed",
                            Toast.LENGTH_SHORT).show()
                        return@Button
                    }
                    auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener { task ->
                            if(task.isSuccessful){
                                // login success
                                navController.navigate(Routes.auth_successful)
                            }else{
                                // login failed
                                val error = task.exception?.localizedMessage
                                Toast.makeText(context, "Login Failed: $error", Toast.LENGTH_LONG).show()
                            }
                        }

                },
                // Makes the button take full width
                enabled = true,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.Black),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text("Sign In")
            }


        }
        Text(
            text = "Forgot Password?",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.inria_sans_regular)),
                fontWeight = FontWeight(700),
                color = Color(0xFF000000),
            ),
            modifier = Modifier
                .padding(start = 100.dp, top = 6.dp)
                .clickable {
                    navController.navigate(Routes.password_reset_page1)
                }
        )

        Spacer(modifier =Modifier.height(10.dp))
        Divider(
            color = Color.Black,
            thickness = 2.dp,
            modifier = Modifier.width(370.dp)
        )
        Spacer(modifier =Modifier.height(20.dp))
        Text(
            text = "New User Registration",
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.inria_sans_regular)),
                fontWeight = FontWeight(550),
                color = Color(0xFF000000),

            ),
            modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)

        )
        Row(
            modifier = Modifier.padding(top = 16.dp, start = 120.dp, end = 85.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Center

        ){
            Button(
                onClick = {
                    navController.navigate(Routes.sign_up_page)
                },
                enabled = true,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.Black),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text("Sign Up")
            }


        }
    }
}

