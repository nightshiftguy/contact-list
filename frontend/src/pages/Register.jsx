import { useState, useEffect } from 'react';
import { useApiFetch } from '../api';
import { useNavigate } from 'react-router-dom';

export default function Register() {
  const [routeAndOptions, setRouteAndOptions] = useState({ route: null, options: {} });
  const {error, loading, status} = useApiFetch(routeAndOptions.route, routeAndOptions.options);
  const [formData, setFormData] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    if((!error && status===204) || (error && error.message === "User already exist but needs verification")) {
      navigate('/verify', { state: { email: formData.email } });
    }
  }, [error, status]);

  const submit = async (e) => {
    e.preventDefault();
    let newFormData = Object.fromEntries(new FormData(e.target));
    setFormData(newFormData);
    const data = JSON.stringify(newFormData);
    setRouteAndOptions({
      route: '/auth/register',
      options: {
        method: 'POST',
        body: data,
      }
    });
  };

  return (
    <form onSubmit={submit} className='register-form'>
      <h2>Register</h2>
      <input name="email" placeholder='email'/>
      <p className="error">{error && error.email}</p>
      <input name="password" type="password" placeholder='password'/>
      <p className="error">{error && error.password}</p>

      <p className="error">{error && error.message}</p>
      <button>Register</button>
      <p className='email-info'>IMPORTANT!!! To create account use email from: <a href="https://temp-mail.org/" target='_blank'>temp mail</a> and fake password</p>
      {loading && <p>Loading...</p>}
    </form>
  );
}
