package com.deliverytracker.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class Delivery(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val trackingNumber: String,
    val recipientName: String,
    val recipientAddress: String,
    val status: DeliveryStatus,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)

enum class DeliveryStatus {
    PENDING,
    IN_TRANSIT,
    DELIVERED,
    FAILED
} 