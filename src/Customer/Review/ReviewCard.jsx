import { Delete } from '@mui/icons-material'
import { Avatar, Box, Grid, IconButton, Rating } from '@mui/material'
import { red } from '@mui/material/colors'
import React from 'react'

const ReviewCard = () => {
    return (
        <div className=' flex justify-between'>
            <div className='w-[80%'>
                <Grid container spacing={1}>
                    <Grid item xs={1.5}>
                        <Box>
                            <Avatar className="text-white" sx={{ width: 56, height: 56, bgcolor: "#9155FD" }}>
                                A
                            </Avatar>
                        </Box>

                    </Grid>
                    <Grid item xs={9}>
                        <div className='space-y-2'>
                            <p className="font-semibold text-lg">Manish</p>
                            <p className='opacity-70'>2014-12-01T09:51:18.321553</p>
                        </div>
                        <div>
                            <Rating readOnly value={4.5} name="half-rating" defaultValue={4.5} precision={0.5} />
                        </div>
                        <p>Provides a great service</p>
                    </Grid>
                </Grid>
            </div>

            <IconButton><Delete sx={{ color: red[700] }} /></IconButton>


        </div>
    )
}

export default ReviewCard
