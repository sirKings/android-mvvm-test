package co.king.chasetest.planetList.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import co.king.chasetest.R
import co.king.chasetest.planetList.domain.model.Planet
import co.king.chasetest.util.Screen


@Composable
fun PlanetView(modifier: Modifier, item: Planet, navController: NavController) {
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
            .clickable {
                navController.navigate(Screen.PlanetDetailScreen.route + "/${item.id}")
            }
    ) {

        Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 32.sp)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
            Row {
                Text(text = stringResource(id = R.string.climate), fontSize = 16.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item.climate, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
            Row {
                Text(text = stringResource(id = R.string.diameter), fontSize = 16.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item.diameter, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = modifier.fillMaxWidth()) {
            Row {
                Text(text = stringResource(id = R.string.population), fontSize = 16.sp)
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
        1,
        "Earth",
        "365",
        "1",
        "100",
        "Winter",
        "10",
        "Plain",
        "Lot",
        "1 billion",
        emptyList(),
        emptyList(),
        "2014-12-09T13:50:49.641000Z",
        "https://swapi.dev/api/planets/1/"
    )

    PlanetView(modifier = Modifier, item = planet, rememberNavController())
}