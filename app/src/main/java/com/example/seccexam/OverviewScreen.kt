package com.example.seccexam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.seccexam.ui.theme.SecCExamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(navController: NavController, dataStoreManager: DataStoreManager){
    var income by remember {
        mutableStateOf(0)
    }
    var expense by remember {
        mutableStateOf(0)
    }
    LaunchedEffect(Unit){
        income = dataStoreManager.getIncome()
        expense = dataStoreManager.getExpense()
    }
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Add Transaction") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Menu, contentDescription = null)
                    }
                },)
        },
        floatingActionButton = { FloatingActionButton(onClick = { navController.navigate("AddScreen") }) {
            Icon(Icons.Default.Add, contentDescription = null)
        }}
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ){
            Spacer(modifier = Modifier.height(20.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 40.dp)
                    .background(Color.LightGray),
//                color = Color.LightGray,
//                contentColor = Color.White,
//                shape = RectangleShape
            ){
                Text(text = "Income")
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "R$ ${income}")
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 40.dp)
                    .background(Color.LightGray),
//                color = Color.LightGray,
//                contentColor = Color.White,
//                shape = RectangleShape
            ){
                Text(text = "Expense")
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "R$ ${expense}")
            }

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 40.dp)
                    .background(Color.LightGray),
//                color = Color.Black,
//                contentColor = Color.White,
//                shape = RectangleShape
            ){
                Text(text = "Expense")
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "R$ ${expense}")
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(bottom = 40.dp)
                    .background(Color.Black),
//                color = Color.Black,
//                contentColor = Color.White,
//                shape = RectangleShape
            ){
                Text(text = "Total")
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "R$ ${income - expense}")
            }

            Text(text = "Last Transaction", modifier = Modifier.padding(top = 20.dp, bottom = 20.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Income}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "${income}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(20.dp))
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Expense", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "${expense}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(20.dp))
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}