// pages/Login.jsx
import { apiFetch } from '../api';

export default function Login() {
  const submit = async (e) => {
    e.preventDefault();
    const data = Object.fromEntries(new FormData(e.target));
    const res = await apiFetch('/auth/login', {
      method: 'POST',
      body: JSON.stringify(data),
    });

    const json = await res.json();
    localStorage.setItem('token', json.token);
    window.location.href = '/';
  };

  return (
    <form onSubmit={submit}>
      <input name="email" placeholder="email" />
      <input name="password" type="password" placeholder="password" />
      <button>Login</button>
    </form>
  );
}
