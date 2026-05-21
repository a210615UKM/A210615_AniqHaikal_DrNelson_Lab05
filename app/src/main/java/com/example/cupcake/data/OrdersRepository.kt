package com.example.cupcake.data

import kotlinx.coroutines.flow.Flow

interface OrdersRepository {

    fun getAllOrdersStream(): Flow<List<OrderEntity>>

    fun getOrderStream(id: Int): Flow<OrderEntity?>

    suspend fun insertOrder(order: OrderEntity)

    suspend fun updateOrder(order: OrderEntity)

    suspend fun deleteOrder(order: OrderEntity)
}