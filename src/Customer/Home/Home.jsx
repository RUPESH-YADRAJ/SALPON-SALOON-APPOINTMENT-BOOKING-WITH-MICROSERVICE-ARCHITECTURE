import React from "react";
import Banner from './Banner';
import HomeServiceCard from "./HomeServiceCard";
import { service } from "../../Data/service";
import SaloonList from "../Saloon/SaloonList";
const Home = () => {
    return (
        <div className="space-y-20">
            <section>
                <Banner>
                </Banner>
            </section>
            <section className="space-y-10 lg:space-y-0 lg:flex items-center gap-5 px-20">
                <div className="w-full lg:w-1/2">
                    <h1 className="text-2xl font-semibold pb-9">What are you looking for?</h1>
                    <div className="flex flex-wrap justify-center items-centergap-5">
                        {
                            service.map((item) => <HomeServiceCard key={item.id} item={item} />)
                        }
                    </div>
                </div>
                <div className="w-full lg:w-1/2 border grid gap-3 grid-cols-2 grid-rows-12 h-[45vh] md:h-[90vh]">
                    <div className="row-span-7">
                        <img className="h-full w-full rounded-md:" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStoZL3u-Iphxky_N4wtKzFAANYm1Rrm2Wneg&s" alt='' />
                    </div>

                    <div className="row-span-5">
                        <img className="h-full w-full rounded-md:" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSCM0tvAEV5SIMSHWr_dkjn8XAvCJpMsIx1HQ&s " alt="" />
                    </div>
                    <div className="row-span-7">
                        <img className="h-full w-full rounded-md:" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlO3jvysiC0aIk2A0RxhZytMzgwXKU_bni1g&s" alt="" />
                    </div>
                    <div className="row-span-5">
                        <img className="h-full w-full rounded-md:" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRlIvfzI-wdOmPi-ND_vuVTBbJF0AU2Vr5x_g&s" alt="" />
                    </div>

                </div>
            </section>
            <section className="px-20">
                <h1 className="text-3xl font-bold pb-10">Book your Favourite saloon</h1>
                <SaloonList>

                </SaloonList>
            </section>

        </div>
    )
}
export default Home