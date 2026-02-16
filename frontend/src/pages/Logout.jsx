import { useEffect } from 'react';
import { Navigate } from 'react-router-dom';

export default function Logout({ setLogged }) {
  useEffect(() => {
    setLogged(false);
    localStorage.removeItem('token');
  }, []);

  return <Navigate to="/login" replace />;
}
