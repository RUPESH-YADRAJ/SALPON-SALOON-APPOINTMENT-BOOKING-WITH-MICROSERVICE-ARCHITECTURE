
import React from "react";

const SaloonDetail = () => {
    return (
        <div className='space-y-5 mb-20'>
            <section className="grid grid-cols-2 gap-3">
                <div className='col-span-2'>
                    <img className='w-full rounded-md h-[15rem] object-cover' src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTB9aMTuhmD9Q11l2PKy8MyzYyjsfbDgKq_rA&s" alt="" />
                </div>
                <div className='col-span-1'>
                    <img className='w-full rounded-md h-[15rem] object-cover' src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpUrfawq7Z2hOHDGzcpFJSHT1cDQpH__TUOw&s" alt="" />
                </div>
                <div className='col-span-1'>
                    <img className='w-full rounded-md h-[15rem] object-cover' src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKsLHcbrOS59g40FR8HQIBqKLxtkveEVxj9A&s" alt="" />
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