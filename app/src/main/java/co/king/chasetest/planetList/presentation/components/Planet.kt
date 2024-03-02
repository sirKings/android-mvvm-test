package co.king.chasetest.planetList.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.king.chasetest.planetList.domain.model.Planet


@Composable
fun PlanetView(modifier: Modifier, item: Planet) {
    val cardShape = RoundedCornerShape(size = 10.dp)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = cardShape
            )
            .padding(8.dp)
    ) {

        Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
            Row {
                Text(text = "Climate:", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item.climate, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Row {
                Text(text = "Diameter:", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item.diameter, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = modifier.fillMaxWidth()) {
            Row {
                Text(text = "Population:", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item.population, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PlanetViewPreview() {
    val planet = Planet(
        "Earth",
        "365",
        "1",
        "100",
        "Winter",
        "10",
        "Plain",
        "Lot",
        "1 billion",
        10,
        20,
        "2014-12-09T13:50:49.641000Z",
        "https://swapi.dev/api/planets/1/"
    )

    PlanetView(modifier = Modifier, item = planet)
}