package com.example.cupcake.data

import kotlinx.coroutines.flow.Flow

class OfflineOrdersRepository(
    private val orderDao: OrderDao
) : OrdersRepository {

    override fun getAllOrdersStream(): Flow<List<OrderEntity>> {
        return orderDao.getAllOrders()
    }

    override fun getOrderStream(id: Int): Flow<OrderEntity?> {
        return orderDao.getOrder(id)
    }

    override suspend fun insertOrder(order: OrderEntity) {
        orderDao.insert(order)
    }

    override suspend fun updateOrder(order: OrderEntity) {
        orderDao.update(order)
    }

    override suspend fun deleteOrder(order: OrderEntity) {
        orderDao.delete(order)
    }
}