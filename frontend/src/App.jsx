import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import Weather from './pages/Weather';
import Navbar from './components/Navbar';
import Contacts from './pages/Contacts';
import ProtectedRoute from './ProtectedRoute';
import Logout from './pages/Logout';

<Routes>
  <Route path="/contacts" element={
    <ProtectedRoute>
      <Contacts />
    </ProtectedRoute>
  } />
</Routes>

export default function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/weather" element={<Weather />} />
        <Route path="/contacts" element={
        <ProtectedRoute>
          <Contacts />
        </ProtectedRoute>
      } />
      <Route path="/logout" element={
        <ProtectedRoute>
          <Logout />
        </ProtectedRoute>
      } />
      </Routes>
    </BrowserRouter>
  );
}
