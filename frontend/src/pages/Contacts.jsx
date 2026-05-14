import { useState } from 'react';
import ContactForm from '../components/ContactForm';
import { useApiFetch } from '../api';

export default function Contacts() {
  const [routeAndOptions, setRouteAndOptions] = useState({ route: '/contacts', options: {} });
  const {data, error, loading} = useApiFetch(routeAndOptions.route, routeAndOptions.options);
  const [editingContact, setEditingContact] = useState(null);
  
  const onSave = (formData) => {
    setRouteAndOptions({
      route : editingContact ? `/contacts/${editingContact.id}` : '/contacts',
      options : {
        method: editingContact ? 'PUT' : 'POST',
        body: JSON.stringify(formData),
      }
    });
    setEditingContact(null);
  };

  const remove = (id) => {
    setRouteAndOptions({route: `/contacts/${id}`, options : {method: 'DELETE',}});
  };

  return (
    <>
      <h2>Contacts</h2>
      <ul>
        {loading && <p>Loading...</p>}
        {!loading && data && data.map(c => (
          <li key={c.id}>
            {c.firstName} {c.lastName} {c.email} {c.phoneNumber}
            <button onClick={() => setEditingContact(c)}>Edit</button>
            <button onClick={() => remove(c.id)}>Delete</button>
          </li>
        ))}
      </ul>
      <p className="error">{error && error.message}</p>
      <ContactForm
        error={error}
        key={editingContact ? editingContact.id : 'new'}
        contact={editingContact}
        onSave={onSave}
      />

      <button onClick={() => setRouteAndOptions({ route: '/contacts/send-email', options: {} })}>Send email with contacts</button>
    </>
  );
}
