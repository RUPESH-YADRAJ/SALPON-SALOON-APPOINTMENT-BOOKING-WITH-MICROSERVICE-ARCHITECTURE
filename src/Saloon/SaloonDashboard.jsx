import React from 'react'
import SaloonDrawerList from './components/SaloonDrawerList'
import Navbar from '../Admin Saloon/Navbar'
import BookingTables from './Booking/BookingTable'
import ServiceTables from './Services/ServiceTable'
import TransactionTable from './Transaction/TransactionTable'
import Category from './Category/Category'
import { Route, Routes } from 'react-router-dom'
import { Home } from '@mui/icons-material'
import CreateServiceForm from './Services/CreateServiceForm'
import Notifications from '../Customer/Notification/Notifications'
import HomePage from './Home/HomePage'
import Payment from './Payment/Payment'
import SaloonRoutes from '../Routes/SaloonRoutes'

const SaloonDashboard = () => {
    return (
        <div className='min-h-screen'>
            <Navbar DrawerList={SaloonDrawerList} />
            <section className='lg:flex lg:h-[90vh]'>
                <div className='hidden lg:block h-full'>
                    <SaloonDrawerList />
                </div>
                <div className='p-10 w-full lg:w-[80%] overflow-y-auto'>
                    <SaloonRoutes />
                </div>


            </section>

        </div>
    )
}

export default SaloonDashboard
