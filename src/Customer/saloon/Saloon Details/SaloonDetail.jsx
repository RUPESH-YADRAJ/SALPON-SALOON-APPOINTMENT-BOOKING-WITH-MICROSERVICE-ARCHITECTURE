
import React from "react";

const SaloonDetail = () => {
    return (
        <div className='space-y-5 mb-20'>
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
            <section className='space-y-3'>
                <h1 className='font-bold text-3xl'>Monu Saloon</h1>
                <p>Koteswor,Kathmandu</p>
                <p>Timing: 10:00:00 To 18:30:00</p>
            </section>

        </div>
    )
}
export default SaloonDetail