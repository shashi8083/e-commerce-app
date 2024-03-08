
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import LandingPage from './Components/LandingPage';
import UserLogin from './Components/UserLogin';
import MerchantLogin from './Components/MerchantLogin';
import 'bootstrap/dist/css/bootstrap.min.css';
import Merchantsignup from './Components/MerchantSignUp';
import MerchantHomePage from './Components/MerchantHomePage';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LandingPage />}/>
          <Route path='/merchant' element={<MerchantLogin/>}/>
          <Route path='/user' element={<UserLogin/>}/>
          <Route path='/merchantsignup' element={<Merchantsignup/>}/>
          <Route path='/merchanthomepage' element={<MerchantHomePage/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
