package com.deliverytracker.service

import com.deliverytracker.model.Delivery
import com.deliverytracker.model.DeliveryStatus
import com.deliverytracker.repository.DeliveryRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class DeliveryService(private val deliveryRepository: DeliveryRepository) {

    fun createDelivery(delivery: Delivery): Delivery {
        return deliveryRepository.save(delivery)
    }

    fun getDelivery(id: Long): Delivery? {
        return deliveryRepository.findById(id).orElse(null)
    }

    fun getDeliveryByTrackingNumber(trackingNumber: String): Delivery? {
        return deliveryRepository.findByTrackingNumber(trackingNumber)
    }

    fun updateDeliveryStatus(id: Long, status: DeliveryStatus): Delivery? {
        return deliveryRepository.findById(id).map { delivery ->
            val updatedDelivery = delivery.copy(
                status = status,
                updatedAt = LocalDateTime.now()
            )
            deliveryRepository.save(updatedDelivery)
        }.orElse(null)
    }

    fun getAllDeliveries(): List<Delivery> {
        return deliveryRepository.findAll()
    }

    fun deleteDelivery(id: Long) {
        deliveryRepository.deleteById(id)
    }
} 