// pages/Register.jsx
import { apiFetch } from '../api';

export default function Register() {
  const submit = async (e) => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.target));

    const res = await apiFetch('/auth/register', {
      method: 'POST',
      body: JSON.stringify(data),
    });

    const json = await res.json();
    localStorage.setItem('token', json.token);
    window.location.href = '/';
  };

  return (
    <form onSubmit={submit}>
      <input name="email" />
      <input name="password" type="password" />
      <button>Register</button>
    </form>
  );
}
