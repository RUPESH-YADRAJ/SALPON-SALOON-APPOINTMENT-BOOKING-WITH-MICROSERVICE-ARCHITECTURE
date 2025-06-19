import logo from './logo.svg';
import './App.css';
import { Button, ThemeProvider } from '@mui/material';
import greenTheme from './theme/greenTheme';
import Home from './Customer/Home/Home';
import SaloonDetails from './Customer/saloon/Saloon Details/SaloonDetails';
function App() {
  return (
    <ThemeProvider theme={greenTheme}>
      <SaloonDetails>

      </SaloonDetails>
    </ThemeProvider>

  );
}

export default App;
