import { useEffect, useState } from 'react';
import {
  getContacts,
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

  const remove = async (id) => {
    await deleteContact(id);
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
        initial={editing}
      />

      <button onClick={() => sendEmailWithContacts()}>Send email with contacts</button>
    </>
  );
}
