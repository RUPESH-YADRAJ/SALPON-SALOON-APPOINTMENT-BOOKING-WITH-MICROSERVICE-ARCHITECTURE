import { Button } from '@mui/material'
import React from 'react'
import { useState } from 'react';
import CategoryTables from './CategoryTable';
import CategoryForm from './CategoryForm';

const Category = () => {
    const [activeTab, setActiveTab] = useState(1);
    const handleTabClick = (tab) => () => setActiveTab(tab)

    return (
        <div>
            <div className='flex items-center gap-5'>
                <Button
                    onClick={handleTabClick(1)}
                    variant={activeTab === 1 ? "contained" : "outlined"}>
                    All Categories
                </Button>
                <Button onClick={handleTabClick(2)}
                    variant={activeTab === 2 ? "contained" : "outlined"}>
                    Categories
                </Button>

            </div>
            <div className='mt-10'>
                {activeTab === 1 ? <CategoryTables /> : <CategoryForm />}
            </div>
        </div >
    )
}

export default Category
