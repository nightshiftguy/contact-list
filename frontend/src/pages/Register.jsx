import { useState } from 'react';
import { apiFetch } from '../api';

export default function Register() {
  const [errors, setErrors] = useState({});

  const submit = async (e) => {
    e.preventDefault();
    const formData = Object.fromEntries(new FormData(e.target));
    const {ok, data} = await apiFetch('/auth/register', {
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
    <form onSubmit={submit} className='register-form'>
      <h2>Register</h2>
      <input name="email" />
      <p className="error">{errors.email}</p>
      <input name="password" type="password" />
      <p className="error">{errors.password}</p>

      <p className="error">{errors.message}</p>
      <button>Register</button>
    </form>
  );
}
