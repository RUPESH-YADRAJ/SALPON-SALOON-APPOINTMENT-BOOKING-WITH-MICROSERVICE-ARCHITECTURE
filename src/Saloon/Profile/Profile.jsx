import React from 'react'

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
        </div>
    )
}

export default Profile
