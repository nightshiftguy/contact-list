import { apiFetch } from '../api';

export const getContacts = () =>
  apiFetch('/contacts').then(r => r.data);

export const sendEmailWithContacts = () =>
  apiFetch('/contacts/send-email');

export const deleteContact = (id) =>
  apiFetch(`/contacts/${id}`, {
    method: 'DELETE',
  });
