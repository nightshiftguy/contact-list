import { useEffect, useState } from "react";
import { useNavigate } from "react-router";

const API_URL = import.meta.env.VITE_API_URL;

export function useApiFetch(route, options={}) {
  const navigate = useNavigate();
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [loading , setLoading] = useState(true);
  const [status, setStatus] = useState(null);

  function handleTokenExpiration() {
    localStorage.removeItem('token');
    navigate("/login");
  }

  useEffect(() => {
    async function fetchData() {
    if(route===null) {
      setLoading(false);
      return;
    }
    const token = localStorage.getItem('token');
    try {
      const res = await fetch(API_URL + route, {
        ...options,
        headers: {
          'Content-Type': 'application/json',
          ...(token && { Authorization: `Bearer ${token}` }),
          ...(options.headers || {}),
        },
      });
      let json;
      try{
       json = await res.json();
      } catch{
        json = undefined;
      }

      // Handle token expiration and unauthorized access
      if(res.status === 401 && json.message === "JWT token has expired") {
        handleTokenExpiration();
      }
      if(res.status === 403) {
        navigate("/");
      }

      if(res.status >= 400) {
        let error = new Error(json || 'Error fetching data');
        error = {...error, ...json}
        throw error;
      }

      setData(json);
      setError(null);
      setLoading(false);
      setStatus(res.status);
    } catch (e) {
      setError(e);
      setData(null);
      setLoading(false);
      setStatus(e.status || 500);
    }
  }
  fetchData();
  }, [route, options]);

  return { data, error, loading, status };
}
