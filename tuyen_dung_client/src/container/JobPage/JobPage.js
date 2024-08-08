import React, { useState, useEffect } from 'react'
const JobPage = () => {
    return (
        <>

            <main>

                {/* <!-- Hero Area Start--> */}
                <div class="slider-area ">
                    <div class="single-slider section-overly slider-height2 d-flex align-items-center" style={{
                        backgroundImage: `url("assets/img/hero/about.jpg")`
                    }}>
                        <div class="container">
                            <div class="row">
                                <div class="col-xl-12">
                                    <div class="hero-cap text-center">
                                        <h2>Tìm việc</h2>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                {/* <!-- Hero Area End -->
        <!-- Job List Area Start --> */}
                <div class="job-listing-area pt-120 pb-120">
                    <div class="container">
                        <div class="row">
                            {/* <!-- Left content --> */}
                            <div class="col-xl-3 col-lg-3 col-md-4">
                                <div class="row">
                                    <div class="col-12">
                                        <div class="small-section-tittle2 mb-45">
                                            <div class="ion"> <svg
                                                xmlns="http://www.w3.org/2000/svg"
                                                xmlnsXlink="http://www.w3.org/1999/xlink"
                                                width="20px" height="12px">
                                                <path fill-rule="evenodd" fill="rgb(27, 207, 107)"
                                                    d="M7.778,12.000 L12.222,12.000 L12.222,10.000 L7.778,10.000 L7.778,12.000 ZM-0.000,-0.000 L-0.000,2.000 L20.000,2.000 L20.000,-0.000 L-0.000,-0.000 ZM3.333,7.000 L16.667,7.000 L16.667,5.000 L3.333,5.000 L3.333,7.000 Z" />
                                            </svg>
                                            </div>
                                            <h4>Lọc công việc</h4>
                                        </div>
                                    </div>
                                </div>
                                {/* <!-- Job Category Listing start --> */}
                                {/* <LeftBar worktype={recieveWorkType} recieveSalary={recieveSalary} recieveExp={recieveExp}
                                    recieveJobType={recieveJobType} recieveJobLevel={recieveJobLevel} recieveLocation={recieveLocation}
                                /> */}
                                {/* <!-- Job Category Listing End --> */}
                            </div>
                            {/* <!-- Right content --> */}
                            <div class="col-xl-9 col-lg-9 col-md-8">
                            
                            
                                
                            </div>
                        </div>
                    </div>
                </div>

                {/* <!--Pagination End  --> */}

            </main>

        </>
    )
}

export default JobPage
