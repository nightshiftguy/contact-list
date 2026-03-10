import { useState, useEffect } from 'react';
import { useApiFetch } from '../api';
import { useLocation, useNavigate } from 'react-router-dom';

export default function Verify({ setLogged }) {
  const [routeAndOptions, setRouteAndOptions] = useState({ route: null, options: {} });
  const {data, error, loading, status} = useApiFetch(routeAndOptions.route, routeAndOptions.options);

  const [code, setCode] = useState('');
  const location = useLocation();
  const navigate = useNavigate();
  const email = location.state?.email;

  useEffect(() => {
    if(!email) navigate('/register');
    if(!error && status===200) {
      localStorage.setItem('token', data.token);
      setLogged(true);
      alert('Verification successful');
      navigate("/");
    }
  }, [email, status]);

  const resendCode = async () => {
    setRouteAndOptions({
      route: `/auth/resend?email=${encodeURIComponent(email)}`,
      options: {
        method: 'POST',
      }
    });
  };

  const submit = async (e) => {
    e.preventDefault();

    setRouteAndOptions({
      route: '/auth/verify',
      options: {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          email,
          verificationCode: code,
        }),
      }
    });
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

      <p className="error">{error && error.message}</p>

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

      {loading && <p>Loading...</p>}
    </form>
  );
}
