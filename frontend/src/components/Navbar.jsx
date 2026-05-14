import { Link } from 'react-router-dom';

export default function Navbar({ logged }) {
  return (
    <nav className='navbar'>
      <Link to="/">Home</Link> |{' '}
      <Link to="/weather">Weather</Link> |{' '}
      {!logged && (
        <>
          <Link to="/login">Login</Link> |{' '}
          <Link to="/register">Register</Link>
        </>
      )}
      {logged && (
        <>
          <Link to="/contacts">Contacts</Link> |{' '}
          <Link to="/logout">Logout</Link>
        </>
      )}
    </nav>
  );
}
