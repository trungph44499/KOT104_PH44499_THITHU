package trungmvph44499.fpoly.kot104_trungmvph44499.ui.theme.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import trungmvph44499.fpoly.kot104_trungmvph44499.model.Item
import trungmvph44499.fpoly.kot104_trungmvph44499.viewmodel.ItemViewModel


@Composable
fun MovieGrid(items: List<Item>,
              onEditClick: (id: String) -> Unit,
              onDeleteClick: (id: String) -> Unit) {
    val gridState = rememberLazyStaggeredGridState()

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        state = gridState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp,
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items.size) { index ->
            MovieColumnItem(
                item = items[index],
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick)
        }
    }
}
@Composable
fun MovieScreen(navigationController: NavController, itemViewModel: ItemViewModel) {
    val moviesState = itemViewModel.movies.observeAsState(initial = emptyList())

    Log.d("zzzzzzzzzzz", "MovieScreen: ${moviesState.value}")

    val movies = moviesState.value
    Column {
        // add san pham
        Button(onClick = {
            navigationController.navigate(Screen.ADD.route)
        }) {
            Text("Thêm")
        }
        MovieGrid(movies, onEditClick = {
            navigationController.navigate("${Screen.EDIT.route}/${it}")
        }, onDeleteClick = {itemViewModel.deleteMovieById(it)})
    }
}

@Composable
fun MovieColumn(
    items: List<Item>, onEditClick: (id: String) -> Unit,
    onDeleteClick: (id: String) -> Unit
) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items.size) { index ->
            MovieColumnItem(
                item = items[index],
                onEditClick = onEditClick,
                onDeleteClick = onDeleteClick)
        }
    }
}

@Composable
fun BoldValueText(
    label: String, value: String, style: TextStyle = MaterialTheme.typography.bodySmall
) {
    Text(buildAnnotatedString {
        append(label)
        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
            append(value)
        }
    }, style = style)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieColumnItem(
    item: Item,
    onEditClick: (id: String) -> Unit,
    onDeleteClick: (id: String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    Card(
        onClick = { showDialog = true },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = item.image,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(130.dp)
                    .wrapContentHeight()
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = item.filmName,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                BoldValueText(label = "Thời lượng: ", value = item.duration)
                BoldValueText(label = "Khởi chiếu: ", value = item.releaseDate)
                BoldValueText(label = "Thể loại: ", value = item.genre)
                Text(
                    text = "Tóm tắt nội dung",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(end = 2.dp)
                )
                Row(modifier = Modifier.padding(end = 4.dp))
                {
                    IconButton(
                        onClick = {
                            onEditClick(item.id)
                        },
                        modifier = Modifier.size(32.dp)
                    )
                    {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Edit",
                            tint = MaterialTheme.colorScheme.primary

                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    IconButton(
                        onClick = { onDeleteClick(item.id) }, modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = "Delete",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }) {
                    Text(text = "Cancle")
                }
            },
            title = { Text(text = "Thông tin chi tiết") },
            text = {
                Column {
                    AsyncImage(
                        model = item.image,
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .width(130.dp)
                            .wrapContentHeight()
                    )
                    Text(
                        text = item.filmName,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    BoldValueText(label = "Thời lượng: ", value = item.duration)
                    BoldValueText(label = "Khởi chiếu: ", value = item.releaseDate)
                    BoldValueText(label = "Thể loại: ", value = item.genre)
                    Text(
                        text = "Tóm tắt nội dung",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 5,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(end = 2.dp)
                    )
//                    IconButton(
//                        onClick = {
//                            onEditClick(item.id)
//                        },
//                        modifier = Modifier.size(32.dp)
//                    )
//                    {
//                        Icon(
//                            Icons.Filled.Edit,
//                            contentDescription = "Edit",
//                            tint = MaterialTheme.colorScheme.primary
//
//                        )
//                    }

                }
            },
        )
    }
}