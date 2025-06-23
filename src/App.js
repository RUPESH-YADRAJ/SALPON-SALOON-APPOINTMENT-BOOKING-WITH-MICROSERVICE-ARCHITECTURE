import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import greenTheme from './theme/greenTheme';
import Home from './Customer/Home/Home';
import SaloonDetails from './Customer/saloon/Saloon Details/SaloonDetails';
import Bookings from './Customer/Booking/Bookings';
import Notifications from './Customer/Notification/Notifications';
function App() {
  return (
    <ThemeProvider theme={greenTheme}>
      {/* <SaloonDetails/> */}
      {/* <Bookings /> */}
      <Notifications />
    </ThemeProvider>

  );
}

export default App;
