package com.example.thirtydays

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydays.model.CreatureSource
import com.example.thirtydays.model.Creatures
import com.example.thirtydays.ui.theme.ThirtyDaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThirtyDaysTheme {
                ThirtyDaysApp()
            }
        }
    }
}

@Composable
fun ThirtyDaysApp() {
    ThirtyDaysTheme {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Image(
                        painter = painterResource(id = R.drawable.app_top_icon),
                        contentDescription = null,
                        modifier = Modifier.size(48.dp)
                    )
//                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.app_header),
                        style = MaterialTheme.typography.h1
                    )
                }
            }
            InfoCardList(creatures = CreatureSource.creatures)
        }
    }
}


@Composable
private fun InfoCardList(creatures: List<Creatures>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(creatures) {
            InfoCard(creature = it)
        }
    }
}

@Composable
fun InfoCard(creature: Creatures, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = SpringSpec(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { expanded = !expanded },
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(8.dp)),
            ) {

                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Image(
                        painter = painterResource(id = creature.image),
                        contentDescription = " A photo of ${stringResource(id = creature.name)}",
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .border(
                                BorderStroke(8.dp, MaterialTheme.colors.onSurface),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .size(150.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    // Name
                    Text(
                        text = stringResource(id = creature.name),
                        style = MaterialTheme.typography.h1
                    )


                    // Origin Box Row
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Box(
                            modifier = Modifier
                                .background(
                                    brush = Brush.horizontalGradient(
                                        listOf(
                                            Color(0xFFBA5370), Color(0xD0F4E2D8)
                                        )
                                    )
                                )
                                .clip(RoundedCornerShape(8.dp))
                        ) {
                            // Origin
                            Text(
                                text = stringResource(id = creature.origin).uppercase(),
                                style = MaterialTheme.typography.overline,
                                color = MaterialTheme.colors.onSurface,
                                modifier = Modifier.padding(4.dp)
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        //Show More (was a button to flip visibility state)-
                        // We won't need to implement the button to change state,
                        // We can make the parent to be clickable which changes a mutable state
                        // then that mutable state is watched by AnimatedVisibility(Boolean), which
                        // settles the hidden/exposed function
                    }


                }
            }
            //Description Collapsible and Expandable content
            AnimatedVisibility(expanded) { // Description
                Text(
                    text = stringResource(id = creature.description),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                        .fillMaxWidth()
                )
            }
        }

    }
}

/* === Uncomment for Previews ===*/
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InfoCardPreview() {
    val creature = Creatures(
        name = R.string.creature1,
        description = R.string.description1,
        origin = R.string.origin1,
        image = R.drawable.creatureimage1
    )
    ThirtyDaysTheme {
        InfoCard(creature = creature)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ThirtyDaysAppPreview() {
    ThirtyDaysTheme {
        ThirtyDaysApp()
    }
}


