import { useState } from 'react';
import { apiFetch } from '../api';
import { useNavigate } from 'react-router-dom';

export default function Register() {
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();
  const submit = async (e) => {
    e.preventDefault();
    setErrors({});
    const formData = Object.fromEntries(new FormData(e.target));
    const {ok, data} = await apiFetch('/auth/register', {
      method: 'POST',
      body: JSON.stringify(formData),
    });

   
    if(!ok && data.message!=="User already exist but needs verification"){
      setErrors(data);
    }
    else{
      navigate('/verify', { state: { email: formData.email } });
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
