import React from 'react'
import { Route, Routes } from 'react-router-dom'
import Notifications from '../Customer/Notification/Notifications'
import ServiceTables from '../Saloon/Services/ServiceTable'
import HomePage from '../Saloon/Home/HomePage'
import CreateServiceForm from '../Saloon/Services/CreateServiceForm'
import BookingTables from '../Saloon/Booking/BookingTable'
import TransactionTable from '../Saloon/Transaction/TransactionTable'
import Payment from '../Saloon/Payment/Payment'
import Category from '../Saloon/Category/Category'
import Profile from '../Saloon/Profile/Profile'


const SaloonRoutes = () => {
    return (
        <Routes>
            <Route path='/' element={<HomePage />} />
            <Route path='/services' element={<ServiceTables />} />
            <Route path='/add-services' element={<CreateServiceForm />} />
            <Route path='/bookings' element={<BookingTables />} />
            <Route path='/category' element={<Category />} />
            <Route path='/transaction' element={<TransactionTable />} />
            <Route path='/notifications' element={<Notifications />} />
            <Route path='/payment' element={<Payment />} />
            <Route path='/account' element={<Profile />} />
        </Routes>
    )
}

export default SaloonRoutes
