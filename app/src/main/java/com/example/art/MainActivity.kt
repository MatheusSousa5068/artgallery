package com.example.art

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.art.ui.theme.ArtTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtGallery()
                }
            }
        }
    }
}

data class ArtData(val imageRes: Int, val title: String, val description: String)

@Composable
fun ArtGallery() {
    val artList = listOf(
        ArtData(R.drawable.mundial, "Taças dos Mundiais", "Corinthians, 2000 & 2012"),
        ArtData(R.drawable.brasileirao, "Brasileirão", "Corinthians, 2017"),
        ArtData(R.drawable.libertadores, "Libertadores", "Corinthians, 2012")
    )

    var currentIndex by remember { mutableStateOf(0) }

    fun nextArtwork() {
        currentIndex = (currentIndex + 1) % artList.size
    }

    fun previousArtwork() {
        currentIndex = if (currentIndex - 1 < 0) artList.size - 1 else currentIndex - 1
    }

    val currentArt = artList[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = currentArt.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .padding(8.dp)
                .border(BorderStroke(2.dp, Color.Red))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .border(BorderStroke(2.dp, Color.Green))
                .padding(8.dp)
        ) {
            Text(text = currentArt.title)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = currentArt.description)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(2.dp, Color.Blue))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OldStyleButton(onClick = { previousArtwork() }, text = "Anterior")
            OldStyleButton(onClick = { nextArtwork() }, text = "Próximo")
        }
    }
}

@Composable
fun OldStyleButton(onClick: () -> Unit, text: String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
        shape = RoundedCornerShape(0.dp),
        modifier = Modifier.border(BorderStroke(1.dp, Color.Black))
    ) {
        Text(text = text, color = Color.Black, textAlign = TextAlign.Center)
    }
}


@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    ArtTheme {
        ArtGallery()
    }
}
