package com.deliverytracker

import com.deliverytracker.model.Delivery
import com.deliverytracker.model.DeliveryStatus
import com.deliverytracker.service.DeliveryService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var deliveryService: DeliveryService

    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() {
        objectMapper = ObjectMapper()
    }

    @Test
    fun `should get all deliveries`() {
        val delivery1 = Delivery(
            id = 1L,
            trackingNumber = "TN123456789",
            recipientName = "João",
            recipientAddress = "Rua das Acácias, 123",
            status = DeliveryStatus.IN_TRANSIT
        )

        val delivery2 = Delivery(
            id = 2L,
            trackingNumber = "TN987654321",
            recipientName = "Maria",
            recipientAddress = "Av. Brasil, 456",
            status = DeliveryStatus.PENDING
        )

        `when`(deliveryService.getAllDeliveries()).thenReturn(listOf(delivery1, delivery2))

        mockMvc.perform(get("/api/deliveries"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.length()").value(2))
    }
}
