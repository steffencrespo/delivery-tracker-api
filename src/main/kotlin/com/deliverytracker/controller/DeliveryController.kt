package com.deliverytracker.controller

import com.deliverytracker.model.Delivery
import com.deliverytracker.model.DeliveryStatus
import com.deliverytracker.service.DeliveryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import io.swagger.v3.oas.annotations.Operation

@RestController
@RequestMapping("/api/deliveries")
class DeliveryController(private val deliveryService: DeliveryService) {

    @Operation(summary = "Create a new delivery")
    @PostMapping
    fun createDelivery(@RequestBody delivery: Delivery): ResponseEntity<Delivery> {
        return ResponseEntity(deliveryService.createDelivery(delivery), HttpStatus.CREATED)
    }

    @Operation(summary = "Get a delivery by ID")
    @GetMapping("/{id}")
    fun getDelivery(@PathVariable id: Long): ResponseEntity<Delivery> {
        return deliveryService.getDelivery(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @Operation(summary = "Get a delivery by tracking number")
    @GetMapping("/tracking/{trackingNumber}")
    fun getDeliveryByTrackingNumber(@PathVariable trackingNumber: String): ResponseEntity<Delivery> {
        return deliveryService.getDeliveryByTrackingNumber(trackingNumber)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @Operation(summary = "Update the status of a delivery")
    @PutMapping("/{id}/status")
    fun updateDeliveryStatus(
        @PathVariable id: Long,
        @RequestParam status: DeliveryStatus
    ): ResponseEntity<Delivery> {
        return deliveryService.updateDeliveryStatus(id, status)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @Operation(summary = "Get all deliveries")
    @GetMapping
    fun getAllDeliveries(): ResponseEntity<List<Delivery>> {
        return ResponseEntity.ok(deliveryService.getAllDeliveries())
    }

    @Operation(summary = "Delete a delivery by ID")
    @DeleteMapping("/{id}")
    fun deleteDelivery(@PathVariable id: Long): ResponseEntity<Void> {
        deliveryService.deleteDelivery(id)
        return ResponseEntity.noContent().build()
    }
} 