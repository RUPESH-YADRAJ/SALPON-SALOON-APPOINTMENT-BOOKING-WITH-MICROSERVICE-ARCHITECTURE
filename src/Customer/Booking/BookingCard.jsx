import { ArrowRightAlt } from '@mui/icons-material'
import { Button } from '@mui/material'
import React from 'react'

const BookingCard = () => {
    return (
        <div className='p-5 rounded-md bg-slate-100 flex flex-col md:flex-row items-start md:items-center justify-between gap-4'>

            <div className='space-y-2'>
                <h1 className='text-2xl font-bold'>Monika Saloon</h1>
                <ul className="list-disc pl-4">
                    <li>Hair Cut</li>
                    <li>Massage Therapy</li>
                    <li>Hair colour</li>
                </ul>
                <div>
                    <p className='flex items-center gap-2'>Time & Date <ArrowRightAlt /> 2025-01-16</p>
                    <p>12:00:00 To 12:45:00</p>
                </div>
            </div>

            <div className='space-y-2 flex flex-col items-center md:items-end'>
                <img className='h-28 w-28 object-cover rounded-md' src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQcmyWnHW8--eWnOZvh2ScihAajOqZHGhQrkg&s" alt="Service" />
                <p className='text-center'>Rs. 150</p>
                <Button color='error' variant="outlined">Cancelled</Button>
            </div>
        </div>
    )
}

export default BookingCard
