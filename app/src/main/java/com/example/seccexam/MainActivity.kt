package com.example.seccexam

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.seccexam.ui.theme.SecCExamTheme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecCExamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var context = LocalContext.current
                    val dataStoreManager = DataStoreManager(context)
                    MyApp(context, dataStoreManager)
//                    Greeting("Android")
                }
            }
        }
    }
}
class DataStoreManager(context: Context){
    private val thisContent = context
    private val Context.datastore by preferencesDataStore(name = "user_pref")
    private val titleKey = stringPreferencesKey("title")
    private val incomeKey = intPreferencesKey("income")
    private val expenseKey = intPreferencesKey("expense")
    private val typeKey = stringPreferencesKey("type")

    suspend fun saveTitle(title : String){
        thisContent.datastore.edit {
                myPrefs -> myPrefs[titleKey] = title
        }
    }

    suspend fun saveIncome(income : Int){
        thisContent.datastore.edit {
                myPrefs -> myPrefs[incomeKey] = income
        }
    }
    suspend fun saveExpense(expense : Int){
        thisContent.datastore.edit {
                myPrefs -> myPrefs[expenseKey] = expense
        }
    }
    suspend fun saveType(type : String){
        thisContent.datastore.edit {
                myPrefs -> myPrefs[typeKey] = type
        }
    }
    suspend fun getType():String {
        var typeFlow = thisContent.datastore.data.map {
                myPrefs -> myPrefs[typeKey] ?: ""
        }
        return typeFlow.first()
    }

    suspend fun getIncome():Int {
        var amountFlow = thisContent.datastore.data.map {
                myPrefs -> myPrefs[incomeKey] ?: 0
        }
        return amountFlow.first()
    }

    suspend fun getExpense():Int {
        var amountFlow = thisContent.datastore.data.map {
                myPrefs -> myPrefs[expenseKey] ?: 0
        }
        return amountFlow.first()
    }

    suspend fun getTitle():String {
        var titleFlow = thisContent.datastore.data.map {
                myPrefs -> myPrefs[titleKey] ?: ""
        }
        return titleFlow.first()
    }
}

@Composable
fun MyApp(context: Context, dataStoreManager: DataStoreManager){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Splash"){
        composable("MainScreen"){ MainScreen(navController, dataStoreManager)}
        composable("Splash"){ SplashScreen(navController = navController)}
        composable("AddScreen"){ AddScreen(navController = navController, dataStoreManager = dataStoreManager)}
        composable("OverviewScreen"){ OverviewScreen(navController = navController, dataStoreManager = dataStoreManager)}
    }
}

@Composable
fun MainScreen(navController: NavController, dataStoreManager: DataStoreManager) {
    var totalAmount by remember {
      mutableStateOf(0)
    }
    LaunchedEffect(Unit){
        var income = dataStoreManager.getIncome()
        var expense = dataStoreManager.getIncome()
        totalAmount = income - expense
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = "Current Budget", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "${totalAmount}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate("AddScreen") }) {
            Text(text = "Add Income/Expense")
        }
        Button(onClick = { navController.navigate("OverviewScreen") }) {
            Text(text = "Show Statistics")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SecCExamTheme {
//        Greeting("Android")
    }
}