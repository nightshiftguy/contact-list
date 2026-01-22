import { useEffect } from 'react';
import { Navigate } from 'react-router-dom';

export default function Logout() {
  useEffect(() => {
    localStorage.removeItem('token');
    window.location.href = '/login';
  }, []);

  return <Navigate to="/login" replace />;
}
