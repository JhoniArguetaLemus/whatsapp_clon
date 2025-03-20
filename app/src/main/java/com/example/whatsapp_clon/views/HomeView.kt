package com.example.whatsapp_clon.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.whatsapp_clon.NavItem
import com.example.whatsapp_clon.R
import kotlinx.coroutines.selects.select


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeView(){



    val items= listOf(
        NavItem("Chats", R.drawable.message_badge_outline),
        NavItem("Status", R.drawable.circle_double),
        NavItem("Calls", R.drawable.phone_outline)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Green
                ),
                title = { Text("WhatsApp")},
                actions = {
                    Icon(Icons.Default.Call, contentDescription = null,
                        modifier=Modifier
                            .size(50.dp)
                            .padding(10.dp)
                        )
                    Icon(Icons.Default.Search, contentDescription = null,
                        modifier=Modifier
                            .size(50.dp)
                            .padding(10.dp)
                        )
                    Icon(Icons.Default.MoreVert, contentDescription = null,
                        modifier=Modifier
                            .size(50.dp)
                            .padding(10.dp)

                        )
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed{ index, item->
                    NavigationBarItem(
                        label = { Text(item.label)},
                        icon = {
                            BadgedBox(
                                badge = {
                                    Badge{
                                        Text("3")
                                    }
                                }
                            ) {
                                Icon(painterResource(item.icon), contentDescription = null)

                            } },
                        onClick = {},
                        selected = false
                    )

                }
            }
        }
    ) {paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            for(i in 1..10){
                Messages()
            }


        }



    }

}


@Composable
fun Messages(){

    var showDialog by remember{ mutableStateOf(false)}
    var imageSelected by remember { mutableStateOf<Int?> (null) }
    if (showDialog){
        AlertDialog(
            onDismissRequest = {},
            text = {
                imageSelected?.let { painterResource(it) }
                    ?.let { Image(painter = it, contentDescription = null, modifier = Modifier.fillMaxWidth()) }
            },
            confirmButton = {
                Button(onClick = {showDialog=false},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    modifier = Modifier.fillMaxWidth(0.60f)
                ) {
                    Text("Ok", color = Color.Black)
                }
            },
            dismissButton = {},
            shape = RectangleShape
        )
    }



    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shadowElevation = 10.dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(painterResource(R.drawable.san_francisco),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .clickable {
                         imageSelected=R.drawable.san_francisco
                        showDialog=true
                    }
                ,
                contentScale = ContentScale.Crop
            )

            Column {

                Text("Pedro Perez",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 20.dp)
                )
                Text("Hi!, How are you?",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp)
                )
            }

            Text("9:06 pm",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )

        }

    }
}