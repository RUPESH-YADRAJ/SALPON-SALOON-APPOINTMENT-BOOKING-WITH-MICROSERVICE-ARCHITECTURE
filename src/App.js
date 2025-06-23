import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import greenTheme from './theme/greenTheme';
import Home from './Customer/Home/Home';
import SaloonDetails from './Customer/saloon/Saloon Details/SaloonDetails';
import Bookings from './Customer/Booking/Bookings';
import Notifications from './Customer/Notification/Notifications';
import Navbar from './Customer/Navbar/Navbar';
import { Route, Routes } from 'react-router-dom';
import SaloonDashboard from './Saloon/SaloonDashboard';
function App() {
  return (
    <ThemeProvider theme={greenTheme}>
      <Navbar />
      {/* <Home /> */}
      {/* <SaloonDetails/> */}
      {/* <Bookings /> */}
      {/* <Notifications /> */}
      <Routes>
        <Route path='/Saloon-dashboard/*' element={<SaloonDashboard />} />
        <Route path='/' element={<Home />} />
        <Route path='/notifications' element={<Notifications />} />
        <Route path='/bookings' element={<Bookings />} />
        <Route path='/saloon/2' element={<SaloonDetails />} />
      </Routes>
    </ThemeProvider>

  );
}

export default App;
