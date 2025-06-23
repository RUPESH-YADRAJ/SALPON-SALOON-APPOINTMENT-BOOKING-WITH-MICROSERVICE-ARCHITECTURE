import React from 'react'
import SaloonDashboard from '../Saloon/SaloonDashboard'
import Bookings from '../Customer/Booking/Bookings'
import SaloonDetails from '../Customer/saloon/Saloon Details/SaloonDetails'
import Navbar from '../Customer/Navbar/Navbar'
import { Route, Routes } from 'react-router-dom'
import Home from '../Customer/Home/Home'
import Notifications from '../Customer/Notification/Notifications'
import NotFound from '../NotFound/NotFound'

const CustomerRoutes = () => {
    return (
        <div>
            <Navbar />
            <Routes>
                <Route path='/' element={<Home />} />
                <Route path='/notifications' element={<Notifications />} />
                <Route path='/bookings' element={<Bookings />} />
                <Route path='/saloon/:id' element={<SaloonDetails />} />
                <Route path='*' element={<NotFound />} />
            </Routes>

        </div>
    )
}

export default CustomerRoutes
