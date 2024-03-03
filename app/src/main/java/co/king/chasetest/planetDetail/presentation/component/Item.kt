package co.king.chasetest.planetDetail.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.king.chasetest.R
import co.king.chasetest.planetDetail.domain.model.Film
import co.king.chasetest.planetDetail.domain.model.Resident
import co.king.chasetest.util.Screen

@Composable
fun Item(item: Any) {
    val cardShape = RoundedCornerShape(size = 10.dp)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = cardShape
            )
            .padding(8.dp)

    ) {
        if(item is Film){
            Column {
                Text(text = item.title, fontWeight = FontWeight.Bold, fontSize = 32.sp)

                Row {
                    Text(text = stringResource(id = R.string.director), fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = item.director, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Row {
                    Text(text = stringResource(id = R.string.open), fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = item.openingCrawl, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }else if( item is Resident){
            Column {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 32.sp)

                Row {
                    Text(text = stringResource(id = R.string.gender), fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = item.gender, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
                Row {
                    Text(text = stringResource(id = R.string.height), fontSize = 16.sp)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = item.height, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                }
            }
        }
    }
}