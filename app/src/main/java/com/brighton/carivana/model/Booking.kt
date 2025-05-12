package com.brighton.carivana.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: Int,
    val carId: Int,
    val startDate: String,
    val endDate: String,
    val totalPrice: Double
)
