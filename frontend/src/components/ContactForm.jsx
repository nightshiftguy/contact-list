export default function ContactForm({ contact, onSave, error }) {
  const submit = e => {
    e.preventDefault();
    const formData = Object.fromEntries(new FormData(e.target));
    onSave(formData);
  };

  return (
    <form onSubmit={submit} className="contact-form">
      <h2>{contact ? 'Edit contact' : 'Add contact'}</h2>

      <input name="firstName" defaultValue={contact?.firstName ?? ''} placeholder='first name'/>
      <p className="error">{error?.firstName}</p>

      <input name="lastName" defaultValue={contact?.lastName ?? ''} placeholder='last name'/>
      <p className="error">{error?.lastName}</p>

      <input name="email" defaultValue={contact?.email ?? ''} placeholder='email'/>
      <p className="error">{error?.email}</p>

      <input name="phoneNumber" defaultValue={contact?.phoneNumber ?? ''} placeholder='phone number'/>
      <p className="error">{error?.phoneNumber}</p>

      <button>{contact ? 'Update' : 'Save'}</button>
    </form>
  );
}
