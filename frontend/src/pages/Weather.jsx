import { useState } from 'react';
import { useApiFetch } from '../api';

export default function Weather() {
  const [city, setCity] = useState('Kraków');
  const [route , setRoute] = useState(`/weather?city=${city}`);
  const [options, setOptions] = useState({});
  const {data, error, loading} = useApiFetch(route, options);
  let weather = null;
  if(data){
    weather = data.current;
  }
  
  return (
    <>
      <div>
        <label htmlFor="city">City: </label>
        <input id="city" value={city} onChange={(e) => setCity(e.target.value)} />
        <button onClick={() => {setRoute(`/weather?city=${city}`)}}>Search</button>
      </div>
      {loading && !error && <p>Loading...</p>}
      {!loading && error && (<p className="error">{error.message}</p>)}
      {!loading &&!error && weather && (
        <div>
          <p>Temperature: {weather.temperature_2m} °C</p>
          <p>Rain: {weather.rain} mm</p>
          <p>Wind: {weather.wind_gusts_10m} km/h</p>
        </div>
      )}
    </>
  );
}
