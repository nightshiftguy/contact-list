import { useState, useEffect } from 'react';
import { apiFetch } from '../api';
import { useLocation, useNavigate } from 'react-router-dom';

export default function Verify() {
  const [errors, setErrors] = useState({});
  const [code, setCode] = useState('');
  const location = useLocation();
  const navigate = useNavigate();
  const email = location.state?.email;

  useEffect(() => {
    if (!email) navigate('/register');
  }, [email]);

  const resendCode = async () => {
    await apiFetch(`/auth/resend?email=${encodeURIComponent(email)}`, {
      method: 'POST',
    });
  };

  const submit = async (e) => {
    e.preventDefault();
    setErrors({});

    const { ok, data } = await apiFetch('/auth/verify', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        email,
        verificationCode: code,
      }),
    });

    if (!ok) {
      setErrors(data);
    } else {
      localStorage.setItem('token', data.token);
      alert('Verification succesfull');
      window.location.href = '/';
    }
  };

  return (
    <form onSubmit={submit} className='register-form'>
      <h2>Verify account</h2>
      <p>Enter verification code sent to your email: {email}</p>

      <input
        name="verificationCode"
        type="text"
        placeholder="verification code"
        value={code}
        onChange={(e) => setCode(e.target.value)}
      />

      <p className="error">{errors.message}</p>

      <button type="submit">Verify</button>

      <a
        href="#"
        onClick={async (e) => {
          e.preventDefault();
          await resendCode();
          alert('Verification code resent');
        }}
      >
        Resend code
      </a>
    </form>
  );
}
