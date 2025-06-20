import { FiberManualRecord } from '@mui/icons-material'
import React from 'react'
import { Button } from '@mui/material'
const ServiceCard = () => {
    return (
        <div className='w-full'>
            <div className='flex items-center justify-between gap-5'>
                <div className='space-y-1 w-[60%]'>
                    <h1 className='text-2xl font-semibold'>Man Beard</h1>
                    <p className='text-gray-500 text-sm'>Stylish man beard</p>
                    <div className='flex items-center gap-3'>
                        <p>Rs. 150/-</p>
                        <FiberManualRecord sx={{ fontSize: "10px", color: "gray" }} />
                        <p>45 mins</p>
                    </div>
                </div>
                <div className='space-y-3'>
                    <img
                        className='w-32 h-32 object-cover rounded-md' src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTn9CcKhL--VxAKBTLmPEwhtASROctD5btUEg&s" alt="" />
                    <Button fullWidth variant='outlined'>Add</Button>

                </div>
            </div>
        </div>
    )
}

export default ServiceCard
