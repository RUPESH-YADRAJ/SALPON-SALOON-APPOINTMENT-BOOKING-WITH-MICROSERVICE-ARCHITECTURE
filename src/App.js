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
import CustomerRoutes from './Routes/CustomerRoutes';
function App() {
  return (
    <ThemeProvider theme={greenTheme}>


      <Routes>
        <Route path='/Saloon-dashboard/*' element={<SaloonDashboard />} />
        <Route path='*' element={<CustomerRoutes />} />

      </Routes>
    </ThemeProvider>

  );
}

export default App;
