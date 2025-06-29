import React from 'react'
import SaloonDrawerList from './components/SaloonDrawerList'
import Navbar from '../Admin Saloon/Navbar'

const SaloonDashboard = () => {
    return (
        <div className='min-h-screen'>
            <Navbar DrawerList={SaloonDrawerList} />
            <section className='lg:flex lg:h-[90vh]'>
                <div className='hidden lg:block h-full'>
                    <SaloonDrawerList />
                </div>


            </section>

        </div>
    )
}

export default SaloonDashboard
