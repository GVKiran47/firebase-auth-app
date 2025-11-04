package com.example.authentication_page

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.WhitePoint
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.ui.platform.LocalContext

@Composable
fun Sign_Up_Page(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phn by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current
    Column(
        modifier = Modifier.fillMaxWidth().background(color = Color.White)
    ){
        Row(
            modifier = Modifier
                .padding(start=100.dp,end=50.dp,top=70.dp,bottom=20.dp)
        ){
            Button(
                onClick = {
                },
                enabled = false,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                ),
                border = BorderStroke(2.dp, Color.Black),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
            ) {
                Text("Sign Up Page")
            }
        }
        Row(
            modifier = Modifier
                .padding(start=80.dp,end=50.dp,top=30.dp,bottom=20.dp)
        ){
            Text(
                text = "Enter Gmail Below",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.inria_sans_regular)),
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier.padding(top = 12.dp, start = 50.dp, end = 50.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Center

        ){
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email=it
                },
                shape = RoundedCornerShape(10.dp)
            )
        }
        Row(
            modifier = Modifier
                .padding(start=80.dp,end=50.dp,top=30.dp,bottom=20.dp)
        ){
            Text(
                text = "Enter Phone Number Below",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.inria_sans_regular)),
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier.padding(top = 12.dp, start = 50.dp, end = 50.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Center

        ){


            OutlinedTextField(
                value = phn,
                onValueChange = { phn = it },
                shape = RoundedCornerShape(10.dp)
            )
        }
        Row(
            modifier = Modifier
                .padding(start=80.dp,end=50.dp,top=30.dp,bottom=20.dp)
        ){
            Text(
                text = "Enter Password Below",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.inria_sans_regular)),
                fontWeight = FontWeight.Bold,
            )
        }
        Row(
            modifier = Modifier.padding(top = 12.dp, start = 50.dp, end = 50.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Center

        ){
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                shape = RoundedCornerShape(10.dp)
            )
        }
        Spacer(modifier=Modifier.height(10.dp))
        Button(
            onClick = {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->

                        if (task.isSuccessful) {
                            Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()

                            // navigate back to home screen
                            navController.navigate(Routes.home_page)   // <-- change if your route id is different

                        } else {
                            Toast.makeText(context, "Failed: ${task.exception?.localizedMessage}", Toast.LENGTH_LONG).show()
                        }
                    }

            },
            enabled = true,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier.padding(start=90.dp,top=10.dp, bottom = 60.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Text("Create Account")
        }

    }
}
