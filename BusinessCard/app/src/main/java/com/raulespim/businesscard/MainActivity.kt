package com.raulespim.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raulespim.businesscard.ui.theme.BgAndroid
import com.raulespim.businesscard.ui.theme.BusinessCardTheme
import com.raulespim.businesscard.ui.theme.GreenAndroid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = GreenAndroid.copy(alpha = 0.3f)
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        TopInfo()
        Spacer(modifier = Modifier.weight(1f))
        ContactInfo()
    }
}

@Composable
fun TopInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            modifier = Modifier.size(100.dp).background(color = BgAndroid),
            contentDescription = null
        )
        Text(
            text = "Raul Espim",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
        )
        Text(
            text = "Android Developer Extraordinaire",
            style = MaterialTheme.typography.bodyLarge,
            color = GreenAndroid,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        RowContactInfo(icon = R.drawable.ic_phone, infoText = "(11) 97777-7777")
        RowContactInfo(
            icon = R.drawable.ic_share,
            infoText = "@raulespim_",
            modifier = Modifier.padding(vertical = 8.dp)
        )
        RowContactInfo(icon = R.drawable.ic_email, infoText = "followme@dev.com")
    }
}

@Composable
fun RowContactInfo(
    icon: Int,
    infoText: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null
) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = contentDescription,
            tint = GreenAndroid
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = infoText)
    }
}


@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = GreenAndroid.copy(alpha = 0.3f)
        ) {
            BusinessCardApp()
        }
    }
}