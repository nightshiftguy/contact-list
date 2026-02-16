import { useNavigate } from "react-router";

const API = import.meta.env.VITE_API_URL;

export function useApiFetch() {
  const navigate = useNavigate();
  return async function apiFetch(url, options = {}) {
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
      navigate("/login");
    }
    if(res.status === 403) {
      navigate("/");
    }
    return { ok: res.ok, status: res.status, data: json };
  };
}
