import { useEffect, useState } from 'react';
import { apiFetch } from '../api';

export default function Weather() {
  const [city, setCity] = useState('Kraków');
  const [weather, setWeather] = useState(null);
  const [errors, setErrors] = useState({});

  const load = async () => {
    const {data, ok} = await apiFetch(`/weather?city=${city}`);

    if(!ok){
      setErrors(data);
    }
    else{
      setErrors(null);
      setWeather(data.current);
    }
  };

  useEffect(() => {
    load();
  }, []);

  return (
    <>
      <div>
        <input value={city} onChange={(e) => setCity(e.target.value)} />
        <button onClick={load}>Search</button>
      </div>
      {errors && (<p className="error">{errors.message}</p>)}
      {!errors && weather && (
        <div>
          <p>Temperature: {weather.temperature_2m} °C</p>
          <p>Rain: {weather.rain} mm</p>
          <p>Wind: {weather.wind_gusts_10m} km/h</p>
        </div>
      )}
    </>
  );
}
