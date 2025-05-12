package com.brighton.carivana.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.brighton.carivana.data.AppDatabase
import com.brighton.carivana.model.Booking
import com.brighton.carivana.repository.BookingRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BookingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BookingRepository
    private val _bookings = mutableStateOf<List<Booking>>(emptyList())
    val bookings: State<List<Booking>> get() = _bookings

    init {
        val db = AppDatabase.getDatabase(application)
        repository = BookingRepository(db.bookingDao())
    }

    fun fetchBookings(userId: Int) = viewModelScope.launch {
        _bookings.value = repository.getBookingsByUser(userId)
    }

    fun fetchAllBookings() = viewModelScope.launch {
        _bookings.value = repository.getAllBookings()
    }

    open fun bookCar(booking: Booking): Job {
        return viewModelScope.launch {
            repository.insertBooking(booking)
            fetchBookings(booking.userId)
        }
    }
}
