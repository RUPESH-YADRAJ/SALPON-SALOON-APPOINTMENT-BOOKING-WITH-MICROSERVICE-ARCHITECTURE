import React from "react";
import StarIcon from '@mui/icons-material/Star';
import { useNavigate } from "react-router-dom";
const SaloonCard = () => {
    const navigate = useNavigate();
    return (
        <div onClick={() => navigate("/saloon/2")} className='cursor-pointer' >
            <div className='w-56 md:w-80 rounded-md bg-slate-100'>
                <img className='w-full h-[15rem] object-cover rounded-t-md' src="https://cdn.pixabay.com/photo/2022/01/24/20/03/salon-6964546_1280.jpg" alt="" />
                <div className='p-5 space-y-2'>
                    <h1>Pablo Saloon</h1>
                    <div className='text-white text-sm p-1 bg-green-700 rounded-full w-14 flex items-center justify-center gap-1'>
                        4.5<StarIcon sx={{ fontsize: "16px" }} />
                    </div>
                    <p>Professional haircut and ....</p>
                    <p>Kathmandu</p>

                </div>
            </div>
        </div>
    )
}
export default SaloonCard