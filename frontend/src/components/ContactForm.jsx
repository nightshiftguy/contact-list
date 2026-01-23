import { useState } from 'react';
import { apiFetch } from '../api';

export default function ContactForm({ initial }) {
  const [errors, setErrors] = useState({});
  const isEdit = !!initial;

  const submit = async e => {
    e.preventDefault();
    const formData = Object.fromEntries(new FormData(e.target));
    const { ok, data } = await apiFetch(
      isEdit ? `/contacts/${initial.id}` : '/contacts',
      {
        method: isEdit ? 'PUT' : 'POST',
        body: JSON.stringify(formData),
      }
    );

    if (!ok) setErrors(data);
    else window.location.reload();
  };

  return (
    <form onSubmit={submit} className="contact-form">
      <h2>{isEdit ? 'Edit contact' : 'Add contact'}</h2>

      <input name="firstName" defaultValue={initial?.firstName ?? ''} placeholder='first name'/>
      <p className="error">{errors?.firstName}</p>

      <input name="lastName" defaultValue={initial?.lastName ?? ''} placeholder='last name'/>
      <p className="error">{errors?.lastName}</p>

      <input name="email" defaultValue={initial?.email ?? ''} placeholder='email'/>
      <p className="error">{errors?.email}</p>

      <input name="phoneNumber" defaultValue={initial?.phoneNumber ?? ''} placeholder='phone number'/>
      <p className="error">{errors?.phoneNumber}</p>

      <p className="error">{errors?.message}</p>

      <button>{isEdit ? 'Update' : 'Save'}</button>
    </form>
  );
}
