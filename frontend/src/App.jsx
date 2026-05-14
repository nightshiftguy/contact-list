import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useState } from 'react';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import Weather from './pages/Weather';
import Navbar from './components/Navbar';
import Contacts from './pages/Contacts';
import ProtectedRoute from './ProtectedRoute';
import Logout from './pages/Logout';
import Verify from './pages/Verify';

export default function App() {
  const [logged, setLogged] = useState(!!localStorage.getItem('token'));
  return (
    <BrowserRouter>
      <Navbar logged={logged} setLogged={setLogged} />
        <main className='container'>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/login" element={<Login setLogged={setLogged}/>} />
            <Route path="/register" element={<Register />} />
            <Route path="/weather" element={<Weather />} />
            <Route path="/verify" element={<Verify setLogged={setLogged}/>} />
            <Route path="/contacts" element={
              <ProtectedRoute>
                <Contacts />
              </ProtectedRoute>
            } />
              <Route path="/logout" element={
                <ProtectedRoute>
                  <Logout setLogged={setLogged}/>
                </ProtectedRoute>
            } />
          </Routes>
        </main>
        
    </BrowserRouter>
  );
}
