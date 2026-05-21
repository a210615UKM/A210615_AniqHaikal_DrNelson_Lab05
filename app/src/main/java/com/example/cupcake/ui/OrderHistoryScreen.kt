package com.example.cupcake.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cupcake.data.OrderEntity

@Composable
fun OrderHistoryScreen(
    orderList: List<OrderEntity>,
    onDeleteOrder: (OrderEntity) -> Unit,
    onIncreaseQuantity: (OrderEntity) -> Unit,
    onBackToStartClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    var orderToIncrease by remember { mutableStateOf<OrderEntity?>(null) }
    var orderToDelete by remember { mutableStateOf<OrderEntity?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Saved Order History",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (orderList.isEmpty()) {
            Text(
                text = "No saved orders yet.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBackToStartClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Back to Start")
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(orderList) { order ->
                    OrderHistoryCard(
                        order = order,
                        onRequestIncreaseQuantity = { orderToIncrease = it },
                        onRequestDeleteOrder = { orderToDelete = it }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBackToStartClicked,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Back to Start")
            }
        }
    }

    if (orderToIncrease != null) {
        AlertDialog(
            onDismissRequest = { orderToIncrease = null },
            title = {
                Text(text = "Confirm Add Quantity")
            },
            text = {
                Text(text = "Are you sure you want to add 1 quantity?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        orderToIncrease?.let { onIncreaseQuantity(it) }
                        orderToIncrease = null
                    }
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { orderToIncrease = null }
                ) {
                    Text(text = "No")
                }
            }
        )
    }

    if (orderToDelete != null) {
        AlertDialog(
            onDismissRequest = { orderToDelete = null },
            title = {
                Text(text = "Confirm Delete")
            },
            text = {
                Text(text = "Are you sure you want to delete this order?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        orderToDelete?.let { onDeleteOrder(it) }
                        orderToDelete = null
                    }
                ) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { orderToDelete = null }
                ) {
                    Text(text = "No")
                }
            }
        )
    }
}

@Composable
private fun OrderHistoryCard(
    order: OrderEntity,
    onRequestIncreaseQuantity: (OrderEntity) -> Unit,
    onRequestDeleteOrder: (OrderEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Order #${order.id}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(text = "Quantity: ${order.quantity}")
            Text(text = "Flavor: ${order.flavor}")
            Text(text = "Pickup Date: ${order.date}")
            Text(text = "Price: ${order.price}")

            Spacer(modifier = Modifier.height(4.dp))

            Button(
                onClick = { onRequestIncreaseQuantity(order) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add 1 Quantity")
            }

            OutlinedButton(
                onClick = { onRequestDeleteOrder(order) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete")
            }
        }
    }
}