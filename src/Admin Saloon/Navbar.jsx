import React, { useState } from 'react'
import { Menu as MenuIcon, NotificationsActive } from '@mui/icons-material'
import { Badge, Drawer, IconButton } from '@mui/material'


const Navbar = ({ DrawerList }) => {
    const [open, setOpen] = useState(false);
    const toggleDrawer = (newOpen) => () => {
        setOpen(newOpen)

    }
    return (
        <div className='h-[10vh] flex items-center justify-between px-5 border-b'>
            <div className='flex items-center gap-3'>
                <IconButton onClick={toggleDrawer(true)}>
                    <MenuIcon color='primary' />
                </IconButton>
                <h1 className='text-xl cursor-pointer font-bold'>Saloon Booking</h1>
            </div>
            <IconButton>
                <Badge color='secondary'>
                    <NotificationsActive color='primary' />
                </Badge>
            </IconButton>
            <Drawer open={open} onClose={toggleDrawer(false)} >
                <DrawerList />
            </Drawer>
        </div>
    )
}

export default Navbar
