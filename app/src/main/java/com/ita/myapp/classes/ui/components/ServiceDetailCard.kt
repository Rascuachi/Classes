package com.ita.myapp.classes.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ita.myapp.classes.R

@Composable
fun ServiceDetailCard(
    id: Int,
    name: String,
    username: String,
    password: String,
    description: String,
    imageURL: String,
    onEditClick: () -> Unit
) {
    val clipboardManager: ClipboardManager = LocalClipboardManager.current
    Column(modifier = Modifier.padding(10.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                model = imageURL,
                error = painterResource(R.drawable.android_logo),
                contentDescription = "Service logo",
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = name,
                color = colorResource(R.color.primary_button),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Edit service",
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onEditClick() }
            )
        }

        HorizontalDivider()

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Username: ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = username,
                fontSize = 18.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Medium
            )
            IconButton(
                onClick = { clipboardManager.setText(AnnotatedString(username)) }
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Copy username"
                )
            }
        }

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Password: ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "***********",
                fontSize = 18.sp,
                color = Color.LightGray,
                fontWeight = FontWeight.Medium
            )
            IconButton(
                onClick = { clipboardManager.setText(AnnotatedString(password)) }
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Copy password"
                )
            }
        }

        HorizontalDivider()

        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Description: ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = description,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        HorizontalDivider()
    }
}
