import { Box, Grid, LinearProgress, Rating } from '@mui/material'
import React from 'react'

const RatingCard = () => {
    return (
        <div className='border p-5 rounded-md'>
            <div className='flex items-center space-x-3 pb-10'>
                <Rating
                    readOnly
                    value={4.5}
                    name="half-rating"
                    defaultValue={4.5}
                    precision={0.5}
                />
                <p className='opacity-60'>5647855</p>
            </div>
            <Box>
                <Grid container justifyContent={"center"} alignItems={"center"} >
                    <Grid size={2}>
                        <p>Excellent</p>
                    </Grid>
                    <Grid size={2}>
                        <LinearProgress
                            variant="determinate"
                            value={40}
                            color="success"
                            sx={{
                                height: 6,
                                borderRadius: 4,
                                backgroundColor: '#e0e0e0'
                            }} />
                    </Grid>
                    <Grid size={7}>
                        <p className='opacity-50 p-2'>1279</p>
                    </Grid>

                </Grid>
                <Grid container justifyContent={"center"} alignItems={"center"} >
                    <Grid size={2}>
                        <p>Very Good</p>
                    </Grid>
                    <Grid size={2}>
                        <LinearProgress
                            variant="determinate"
                            value={50}
                            color="success"
                            sx={{
                                height: 6,
                                borderRadius: 4,
                                backgroundColor: '#e0e0e0'
                            }} />
                    </Grid>
                    <Grid size={7}>
                        <p className='opacity-50 p-2'>1279</p>
                    </Grid>

                </Grid>
                <Grid container justifyContent={"center"} alignItems={"center"} >
                    <Grid size={2}>
                        <p>Good</p>
                    </Grid>
                    <Grid size={2}>
                        <LinearProgress
                            variant="determinate"
                            value={30}
                            color="warning"
                            sx={{
                                height: 6,
                                borderRadius: 4,
                                backgroundColor: '#e0e0e0'
                            }} />
                    </Grid>
                    <Grid size={7}>
                        <p className='opacity-50 p-2'>1279</p>
                    </Grid>

                </Grid>
                <Grid container justifyContent={"center"} alignItems={"center"} >
                    <Grid size={2}>
                        <p>Average</p>
                    </Grid>
                    <Grid size={2}>
                        <LinearProgress
                            variant="determinate"
                            value={20}
                            color="success"
                            sx={{
                                height: 6,
                                borderRadius: 4,
                                backgroundColor: '#e0e0e0'
                            }} />
                    </Grid>
                    <Grid size={7}>
                        <p className='opacity-50 p-2'>1279</p>
                    </Grid>

                </Grid>
                <Grid container justifyContent={"center"} alignItems={"center"} >
                    <Grid size={2}>
                        <p>Poor</p>
                    </Grid>
                    <Grid size={2}>
                        <LinearProgress
                            variant="determinate"
                            value={10}
                            color="error"
                            sx={{
                                height: 6,
                                borderRadius: 4,
                                backgroundColor: '#e0e0e0'
                            }} />
                    </Grid>
                    <Grid size={7}>
                        <p className='opacity-50 p-2'>1279</p>
                    </Grid>

                </Grid>

            </Box>
        </div>
    )
}

export default RatingCard

