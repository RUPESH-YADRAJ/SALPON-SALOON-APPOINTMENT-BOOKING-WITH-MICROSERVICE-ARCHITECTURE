import { AccountBalance, AccountBox, Add, Category, Dashboard, Inventory, Logout, Notifications, NotificationsNone, NotificationsOffOutlined, Receipt, ShoppingBag } from '@mui/icons-material'
import React from 'react'
import CategoryIcon from '@mui/icons-material/Category';
import NotificationsNoneIcon from '@mui/icons-material/NotificationsNone';
import DrawerList from '../../Admin Saloon/DrawerList';
const menu = [
    {
        name: "Dashboard",
        path: "/saloon-dashboard",
        icon: <Dashboard className='text-primary-color' />,
        activeIcon: <Dashboard className='text-secondary-color' />
    },
    {
        name: "Bookings",
        path: "/saloon-dashboard/bookings",
        icon: <ShoppingBag className='text-primary-color' />,
        activeIcon: <ShoppingBag className='text-secondary-color' />
    },
    {
        name: "Services",
        path: "/saloon-dashboard/services",
        icon: <Inventory className='text-primary-color' />,
        activeIcon: <Inventory className='text-secondary-color' />
    },
    {
        name: "Add Services",
        path: "/saloon-dashboard/add-services",
        icon: <Add className='text-primary-color' />,
        activeIcon: <Add className='text-secondary-color' />
    },
    {
        name: "Payment",
        path: "/saloon-dashboard/payment",
        icon: <AccountBalance className="text-primary-color" />,
        activeIcon: <AccountBalance className="text-secondary-color" />
    },
    {
        name: "Transaction",
        path: "/saloon-dashboard/transaction",
        icon: <Receipt className='text-primary-color' />,
        activeIcon: <Receipt className='text-secondary-color' />
    },
    {
        name: "Category",
        path: "/saloon-dashboard/category",
        icon: <CategoryIcon className='text-primary-color' />,
        activeIcon: <CategoryIcon className='text-secondary-color' />
    },
    {
        name: "Notifications",
        path: "/saloon-dashboard/notifications",
        icon: <NotificationsNone className='text-primary-color' />,
        activeIcon: <Notifications className='text-secondary-color' />
    },
]
const menu2 = [
    {
        name: "Account",
        path: "/saloon-dashboard/account",
        icon: <AccountBox className='text-primary-color' />,
        activeIcon: <AccountBox className='text-secondary-color' />
    },
    {
        name: "Logout",
        path: "/",
        icon: <Logout className='text-primary-color' />,
        activeIcon: <Logout className='text-secondary-color' />
    },
]
const SaloonDrawerList = () => {
    return (
        <DrawerList menu={menu} menu2={menu2} />
    )
}

export default SaloonDrawerList

