// src/components/ContactForm.jsx
import { useState } from 'react';

export default function ContactForm({ initial, onSubmit }) {
  const [form, setForm] = useState(
    initial ?? { firstName: '', lastName: '', email: '', phoneNumber: '' }
  );

  const change = e =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const submit = e => {
    e.preventDefault();
    onSubmit(form);
    setForm({ firstName: '', lastName: '', email: '', phoneNumber: '' });
  };

  return (
    <form onSubmit={submit}>
      <input name="firstName" placeholder="First name" value={form.firstName} onChange={change} />
      <input name="lastName" placeholder="Last name" value={form.lastName} onChange={change} />
      <input name="email" placeholder="Email" value={form.email} onChange={change} />
      <input name="phoneNumber" placeholder="Phone" value={form.phoneNumber} onChange={change} />
      <button>Save</button>
    </form>
  );
}
