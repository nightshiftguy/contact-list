import { apiFetch } from '../api';

export const getContacts = () =>
  apiFetch('/contacts').then(r => r.json());

export const sendEmailWithContacts = () =>
  apiFetch('/contacts/send-email').then(r => r.json());

export const createContact = (contact) =>
  apiFetch('/contacts', {
    method: 'POST',
    body: JSON.stringify(contact),
  });

export const updateContact = (id, contact) =>
  apiFetch(`/contacts/${id}`, {
    method: 'PUT',
    body: JSON.stringify(contact),
  });

export const deleteContact = (id) =>
  apiFetch(`/contacts/${id}`, {
    method: 'DELETE',
  });
