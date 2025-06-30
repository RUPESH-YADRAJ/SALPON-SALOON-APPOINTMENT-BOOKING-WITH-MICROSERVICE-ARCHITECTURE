import { AddPhotoAlternate, Close } from '@mui/icons-material';
import {
    Button,
    CircularProgress,
    FormControl,
    Grid,
    IconButton,
    InputLabel,
    MenuItem,
    Select,
    TextField,
} from '@mui/material';
import React from 'react';
import { useFormik } from 'formik';

const CreateServiceForm = () => {
    const formik = useFormik({
        initialValues: {
            name: '',
            image: '',
            description: '',
            price: '',
            duration: '',
            category: '',
        },
        onSubmit: () => {
            console.log('Submitting', formik.values);
        },
    });

    return (
        <div className='flex justify-center items-center'>
            <form
                onSubmit={formik.handleSubmit}
                className='space-y-4 p-4 w-full lg:w-1/2'
            >
                <Grid container direction='column' spacing={2}>
                    {/* Image Upload */}
                    <Grid item xs={12}>
                        <label htmlFor='fileInput' className='relative'>
                            <input
                                type='file'
                                accept='image/*'
                                id='fileInput'
                                style={{ display: 'none' }}
                            />
                            <span className='w-24 h-24 cursor-pointer flex items-center justify-center p-3 border rounded-md border-gray-400'>
                                <AddPhotoAlternate className='text-gray-700' />
                            </span>
                        </label>
                    </Grid>

                    {/* Name */}
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            id='name'
                            name='name'
                            label='name'
                            value={formik.values.name}
                            onChange={formik.handleChange}
                            required
                        />
                    </Grid>

                    {/* Description */}
                    <Grid item xs={12}>
                        <TextField
                            fullWidth
                            multiline
                            rows={4}
                            id='description'
                            name='description'
                            label='description'
                            value={formik.values.description}
                            onChange={formik.handleChange}
                            required
                        />
                    </Grid>

                    {/* Price & Duration in the same row, equal width */}
                    <Grid item xs={12}>
                        <div className='flex gap-4'>
                            <TextField
                                fullWidth
                                id='price'
                                name='price'
                                label='price'
                                value={formik.values.price}
                                onChange={formik.handleChange}
                                required
                            />
                            <TextField
                                fullWidth
                                id='duration'
                                name='duration'
                                label='duration'
                                value={formik.values.duration}
                                onChange={formik.handleChange}
                                required
                            />
                        </div>
                    </Grid>

                    {/* Category */}
                    <Grid item xs={12}>
                        <FormControl fullWidth>
                            <InputLabel id='category-label'>Category</InputLabel>
                            <Select
                                labelId='category-label'
                                id='category'
                                name='category'
                                value={formik.values.category}
                                label='Category'
                                onChange={formik.handleChange}
                            >
                                {[1, 1, 1, 1, 1].map((item, index) => (
                                    <MenuItem value={"haircut" + index}>haircut

                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </Grid>

                    {/* Submit */}
                    <Grid item xs={12}>
                        <Button
                            type='submit'
                            variant='outlined'
                            fullWidth
                            sx={{ py: '.8rem' }}
                        >
                            Create Category
                        </Button>
                    </Grid>
                </Grid>
            </form>
        </div>
    );
};

export default CreateServiceForm;
