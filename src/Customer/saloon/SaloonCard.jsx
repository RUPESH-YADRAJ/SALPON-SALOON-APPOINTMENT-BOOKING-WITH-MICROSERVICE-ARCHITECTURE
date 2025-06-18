import React from "react";
import StarIcon from '@mui/icons-material/Star';
const SaloonCard = () => {
    return (
        <div className='w-56 md:w-80 rounded-md bg-slate-100'>
            <img className='w-full h-[15rem] object-cover rounded-t-md' src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTnNPGFH1HxImZ7mOj_nCXej9pUcTyglWQ8Yw&s" alt="" />
            <div className='p-5 space-y-2'>
                <h1>Pablo Saloon</h1>
                <div className='text-white text-sm p-1 bg-green-700 rounded-full w-14 flex items-center justify-center gap-1'>
                    4.5<StarIcon sx={{ fontsize: "16px" }} />
                </div>
                <p>Professional haircut and ....</p>
                <p>Kathmandu</p>

            </div>
        </div>
    )
}
export default SaloonCard