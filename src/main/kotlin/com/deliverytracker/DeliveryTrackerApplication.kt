package com.deliverytracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import com.deliverytracker.model.Delivery
import com.deliverytracker.model.DeliveryStatus
import com.deliverytracker.service.DeliveryService
import java.time.LocalDateTime
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info

@Bean
fun apiInfo(): OpenAPI {
    return OpenAPI()
        .info(
            Info()
                .title("Delivery Tracker API")
                .description("API for tracking deliveries and managing delivery status")
                .version("1.0.0")
        )
}

@SpringBootApplication
class DeliveryTrackerApplication {
    @Bean
    fun dataInitializer(deliveryService: DeliveryService): CommandLineRunner {
        return CommandLineRunner {
            println("Inserindo dados mock no banco...")

            val delivery1 = Delivery(
                trackingNumber = "TN123456789",
                recipientName = "João",
                recipientAddress = "Rua das Acácias, 123",
                status = DeliveryStatus.IN_TRANSIT,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )
            
            val delivery2 = Delivery(
                trackingNumber = "TN987654321",
                recipientName = "Maria",
                recipientAddress = "Av. Brasil, 456",
                status = DeliveryStatus.PENDING,
                createdAt = LocalDateTime.now(),
                updatedAt = LocalDateTime.now()
            )

            deliveryService.createDelivery(delivery1)
            deliveryService.createDelivery(delivery2)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<DeliveryTrackerApplication>(*args)
} 