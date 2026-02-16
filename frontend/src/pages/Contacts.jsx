import { useEffect, useState } from 'react';
import ContactForm from '../components/ContactForm';
import { useApiFetch } from '../api';

export default function Contacts() {
  const apiFetch = useApiFetch();
  const [contacts, setContacts] = useState([]);
  const [editing, setEditing] = useState(null);

  const load = () => apiFetch('/contacts').then(r => r.data).then(setContacts);

  const onSave = () => {
  setEditing(null);
  load();
};

  useEffect(() => {
    load();
  }, []);

  const remove = async (id) => {
    await apiFetch(`/contacts/${id}`, {method: 'DELETE',});
    load();
  };

  return (
    <>
      <h2>Contacts</h2>
      <ul>
        {contacts.map(c => (
          <li key={c.id}>
            {c.firstName} {c.lastName} {c.email} {c.phoneNumber}
            <button onClick={() => setEditing(c)}>Edit</button>
            <button onClick={() => remove(c.id)}>Delete</button>
          </li>
        ))}
      </ul>

      <ContactForm
        key={editing ? editing.id : 'new'}
        initial={editing}
        onSave={onSave}
      />

      <button onClick={() => apiFetch('/contacts/send-email')}>Send email with contacts</button>
    </>
  );
}
