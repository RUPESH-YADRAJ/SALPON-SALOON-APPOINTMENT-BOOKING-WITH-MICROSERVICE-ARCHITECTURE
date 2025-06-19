import { DriveFileMove } from "@mui/icons-material";
import React from "react";
import SaloonDetail from "./SaloonDetail";
import { Button } from "@mui/material";

const tabs = [{ name: "All Services" }, { name: "Reviews" }, { name: "Create Review" }]
const SaloonDetails = () => {
    return (
        <div className='px-5 lg:px-20'>
            <SaloonDetail></SaloonDetail>
            <div className='space-y-4'>
                <div className='flex gap-2'>
                    {tabs.map((tab) => <Button variant='outlined'>{tab.name}</Button>)}
                </div>
            </div>

        </div>
    )
}
export default SaloonDetails