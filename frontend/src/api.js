const API = 'http://127.0.0.1:8080/api';

export function apiFetch(url, options = {}) {
  const token = localStorage.getItem('token');

  return fetch(API + url, {
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...(token && { Authorization: `Bearer ${token}` }),
      ...options.headers,
    },
  }).then(res => {
    if (res.status === 401) {
      localStorage.removeItem('token');
      window.location.href = '/login';
      throw new Error('Unauthorized');
    }
    return res;
     });
}
