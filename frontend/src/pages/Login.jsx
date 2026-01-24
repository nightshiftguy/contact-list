import { useState } from 'react';
import { apiFetch } from '../api';

export default function Login() {
  const [errors, setErrors] = useState({});

  const submit = async (e) => {
    setErrors({});
    e.preventDefault();
    const formData = Object.fromEntries(new FormData(e.target));
    const {ok, data} = await apiFetch('/auth/login', {
      method: 'POST',
      body: JSON.stringify(formData),
    });

    if(!ok){
      setErrors(data);
    }
    else{
      localStorage.setItem('token', data.token);
      window.location.href = '/';
    }
  }

  return (
    <form onSubmit={submit} className='login-form'>
      <h2>Log in</h2>
      <input name="email" placeholder="email" />
      <p className="error">{errors.email}</p>
      <input name="password" type="password" placeholder="password" />
      <p className="error">{errors.password}</p>

      <p className="error">{errors.message}</p>
      <button>Login</button>
    </form>
  );
}
