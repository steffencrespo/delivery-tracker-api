package com.deliverytracker.repository

import com.deliverytracker.model.Delivery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DeliveryRepository : JpaRepository<Delivery, Long> {
    fun findByTrackingNumber(trackingNumber: String): Delivery?
} 