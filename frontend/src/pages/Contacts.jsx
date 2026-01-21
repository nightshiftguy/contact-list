// src/pages/Contacts.jsx
import { useEffect, useState } from 'react';
import {
  getContacts,
  createContact,
  updateContact,
  deleteContact,
  sendEmailWithContacts
} from '../api/contacts';
import ContactForm from '../components/ContactForm';

export default function Contacts() {
  const [contacts, setContacts] = useState([]);
  const [editing, setEditing] = useState(null);

  const load = () => getContacts().then(setContacts);

  useEffect(() => {
    load();
  }, []);

  const create = async (contact) => {
    await createContact(contact);
    load();
  };

  const update = async (contact) => {
    await updateContact(editing.id, contact);
    setEditing(null);
    load();
  };

  const remove = async (id) => {
    await deleteContact(id);
    load();
  };

  return (
    <>
      <h2>Contacts</h2>
      <button onClick={() => sendEmailWithContacts()}>Send email with contacts</button>

      <ContactForm
        initial={editing}
        onSubmit={editing ? update : create}
      />

      <ul>
        {contacts.map(c => (
          <li key={c.id}>
            {c.firstName} {c.lastName} – {c.email}
            <button onClick={() => setEditing(c)}>Edit</button>
            <button onClick={() => remove(c.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </>
  );
}
