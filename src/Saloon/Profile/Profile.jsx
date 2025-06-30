import React from 'react'
import ProfileFildCard from './ProfileFildCard'
import { Divider } from '@mui/material'

const Profile = () => {
    return (
        <div className='lg:px-20 lg:bottom-20 space-y-20'>
            <div className='w-full lg:w-[70%]'>
                <h1 className='text-5xl font-bold pb-5'>Monu Saloon</h1>
                <section className="grid grid-cols-2 gap-3">
                    <div className='col-span-2'>
                        <img className='w-full rounded-md h-[15rem] object-cover' src="https://cdn.pixabay.com/photo/2020/05/21/11/42/hair-salon-5200392_1280.jpg" alt="" />
                    </div>
                    <div className='col-span-1'>
                        <img className='w-full rounded-md h-[15rem] object-cover' src="https://cdn.pixabay.com/photo/2020/05/24/02/00/barber-shop-5212059_1280.jpg" alt="" />
                    </div>
                    <div className='col-span-1'>
                        <img className='w-full rounded-md h-[15rem] object-cover' src="https://cdn.pixabay.com/photo/2018/02/22/17/08/barber-3173419_1280.jpg" alt="" />
                    </div>
                </section>
            </div>
            <div className='mt-10 lg:w-[70%]'>
                <div className='flex items-center pb-3 justify-between'>
                    <h1 className='text-2xl font-bold text-gray-600 '>Owner Details</h1>
                </div>
                <div>
                    <ProfileFildCard keys={"Owner Name"} value={"Pablo"} />
                    <Divider />
                    <ProfileFildCard keys={"Email"} value={"pablo@gmail.com"} />
                    <Divider />
                    <ProfileFildCard keys={"Role"} value={"Saloon_Owner"} />

                </div>
            </div>

            <div className='mt-10 lg:w-[70%]'>
                <div className='flex items-center pb-3 justify-between'>
                    <h1 className='text-2xl font-bold text-gray-600 '>Saloon Details</h1>
                </div>
                <div>
                    <ProfileFildCard keys={"Saloon Name"} value={"Pablo Saloon"} />
                    <Divider />
                    <ProfileFildCard keys={"Saloon Address"} value={"Koteswor, Kathmandu"} />
                    <Divider />
                    <ProfileFildCard keys={"Open Time"} value={"10:00AM"} />
                    <Divider />
                    <ProfileFildCard keys={"Close Time"} value={"09:00PM"} />
                </div>
            </div>
        </div>
    )
}

export default Profile
