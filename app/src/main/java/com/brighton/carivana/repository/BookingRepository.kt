package com.brighton.carivana.repository

import com.brighton.carivana.data.BookingDao
import com.brighton.carivana.model.Booking

class BookingRepository(private val bookingDao: BookingDao) {
    suspend fun insertBooking(booking: Booking) = bookingDao.insertBooking(booking)
    suspend fun getBookingsByUser(userId: Int): List<Booking> = bookingDao.getBookingsByUser(userId)
    suspend fun getAllBookings(): List<Booking> = bookingDao.getAllBookings()
}
