const API = 'http://127.0.0.1:8080/api';

export async function apiFetch(url, options = {}) {
  const token = localStorage.getItem('token');

  const res = await fetch(API + url, {
    ...options,
    headers: {
      'Content-Type': 'application/json',
      ...(token && { Authorization: `Bearer ${token}` }),
      ...options.headers,
    },
  })

  let json;
  try {
    json = await res.json();
  } catch {
    json = undefined;
  }

  if(res.status === 401 && json.message === "JWT token has expired") {
    localStorage.removeItem('token');
    window.location.href = '/login';
  }
  if(res.status === 403) {
    window.location.href = '/';
  }
  return { ok: res.ok, status: res.status, data: json };
}
