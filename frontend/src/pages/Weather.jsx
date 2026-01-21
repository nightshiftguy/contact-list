// pages/Weather.jsx
import { useEffect, useState } from 'react';
import { apiFetch } from '../api';

export default function Weather() {
  const [city, setCity] = useState('Kraków');
  const [weather, setWeather] = useState(null);

  const load = async () => {
    const res = await apiFetch(`/weather?city=${city}`);
    const json = await res.json();
    setWeather(json.current);
  };

  useEffect(() => {
    load();
  }, []);

  return (
    <>
      <input value={city} onChange={(e) => setCity(e.target.value)} />
      <button onClick={load}>Search</button>

      {weather && (
        <>
          <p>Temperature: {weather.temperature_2m} °C</p>
          <p>Rain: {weather.rain} mm</p>
          <p>Wind: {weather.wind_gusts_10m} km/h</p>
        </>
      )}
    </>
  );
}
