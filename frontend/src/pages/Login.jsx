import { useState } from 'react';
import { useApiFetch } from '../api';
import { useNavigate } from 'react-router-dom';

export default function Login({ setLogged }) {
  const apiFetch = useApiFetch();
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

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
      setLogged(true);
      navigate("/");
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
