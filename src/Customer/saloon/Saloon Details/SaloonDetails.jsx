import { DriveFileMove } from "@mui/icons-material";
import React, { useState } from "react";
import SaloonDetail from "./SaloonDetail";
import { Button } from "@mui/material";
import SaloonServiceDetails from "./SaloonServiceDetails";

const tabs = [{ name: "All Services" }, { name: "Reviews" }, { name: "Create Review" }]
const SaloonDetails = () => {
    const [activeTab, setActiveTab] = useState(tabs[0])
    const handleActiveTab = (tab) => setActiveTab(tab)
    return (
        <div className='px-5 lg:px-20'>
            <SaloonDetail></SaloonDetail>
            <div className='space-y-4'>
                <div className='flex gap-2'>
                    {tabs.map((tab) => <Button
                        onClick={() => handleActiveTab(tab)}
                        variant={tab.name === activeTab.name ? "contained" : "outlined"}>
                        {tab.name}</Button>)}
                </div>
                <divider> </divider>
            </div>
            <div>
                {
                    activeTab.name == "Create Review" ? <div>Create Review Form</div> : activeTab.name === "Reviews" ?
                        <div>Review List</div> : <div>
                            <SaloonServiceDetails></SaloonServiceDetails>
                        </div>


                }

            </div>

        </div>
    )
}
export default SaloonDetails