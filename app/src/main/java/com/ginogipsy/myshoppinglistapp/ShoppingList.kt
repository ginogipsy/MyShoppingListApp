package com.ginogipsy.myshoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    var isEditing: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(modifier: Modifier = Modifier) {
    var sItems by remember { mutableStateOf<List<ShoppingItem>>(emptyList()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }



    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center, // Centro verticale
        horizontalAlignment = Alignment.CenterHorizontally // Centro orizzontale
    ) {
        Button(
            onClick = {
                showDialog = true
            }
        ) {
            Text(text = "Add Item")
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            items(sItems) {
                ShoppingListItem(it, {}, {})
            }

        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (itemName.isNotBlank()) {
                            val newItem = ShoppingItem(
                                id = sItems.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toInt()
                            )
                            sItems = sItems.plus(newItem)
                            itemName = ""
                            showDialog = false
                        }

                    }) { Text("Add Item") }
                    TextButton(onClick = { showDialog = false }) { Text("Cancel") }
                }

            },
            title = { Text("Add Shopping Item") },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = { Text("Item name") }
                    )
                    OutlinedTextField(
                        value = itemQuantity,
                        onValueChange = { itemQuantity = it },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        label = { Text("Quantity") }
                    )
                }
            }
        )
    }
}

@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    //we are creating the "onClick" method, it's the same
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row (
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    2.dp,
                    Color(0XFF018786)
                ),
                shape = RoundedCornerShape(20)
            ),
    ) {
        Text(text = item.name, modifier = Modifier.padding(8.dp))
    }
}
