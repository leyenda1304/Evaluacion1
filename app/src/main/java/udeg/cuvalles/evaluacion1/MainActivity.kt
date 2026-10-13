package udeg.cuvalles.evaluacion1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 1. Tarjeta superior con foto de perfil
        item {
            HeaderCard()
        }

        // 2. Componentes horizontales (LazyRow de 8 elementos)
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
            ) {
                items(8) { index ->
                    HorizontalSquareItem(itemIndex = index + 1)
                }
            }
        }

        // 3. Título de la sección
        item {
            Text(
                text = "Title of Card",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // 4. Lista vertical (10 elementos)
        items(10) { index ->
            VerticalListItem(itemIndex = index + 1)
        }
    }
}

@Composable
fun HeaderCard() {
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Foto de perfil redonda
                // Nota: Asegúrate de pegar tu imagen en res/drawable/foto_perfil.png
                Image(
                    painter = painterResource(id = R.drawable.foto_perfil),
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(76.dp)
                        .clip(CircleShape)
                )

                Column {
                    Text(
                        text = "Title of Card",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Text text and\nmore subtex",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        lineHeight = 18.sp
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedButton(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(text = "Button", color = Color.Gray)
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B92F6))
                ) {
                    Text(text = "Button", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun HorizontalSquareItem(itemIndex: Int) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.15f else 1.0f,
        animationSpec = tween(durationMillis = 200),
        label = "scaleAnimation"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isHovered) Color(0xFF3B92F6) else Color(0xFFD0D0D0),
        animationSpec = tween(durationMillis = 200),
        label = "colorAnimation"
    )

    val textColor by animateColorAsState(
        targetValue = if (isHovered) Color.White else Color.DarkGray,
        animationSpec = tween(durationMillis = 200),
        label = "textColorAnimation"
    )

    Box(
        modifier = Modifier
            .size(72.dp)
            .hoverable(interactionSource)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$itemIndex",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun VerticalListItem(itemIndex: Int) {
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(
        targetValue = if (isHovered) 1.02f else 1.0f,
        animationSpec = tween(durationMillis = 150),
        label = "listScale"
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isHovered) Color(0xFFE2E8F0) else Color(0xFFD0D0D0),
        animationSpec = tween(durationMillis = 150),
        label = "listColor"
    )

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .hoverable(interactionSource)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Elemento $itemIndex",
                fontSize = 15.sp,
                color = Color.DarkGray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        MainScreen()
    }
}