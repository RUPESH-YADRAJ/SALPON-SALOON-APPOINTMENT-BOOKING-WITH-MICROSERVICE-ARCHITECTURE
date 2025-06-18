import React from "react";
import SaloonCard from "./SaloonCard";
const SaloonList = () => {
    return (
        <div className='flex gap-5 flex-wrap'>
            {[1, 1, 1, 1, 1, 1, 1, 1, 1].map((item) => <SaloonCard />)}
        </div >
    )
}
export default SaloonList