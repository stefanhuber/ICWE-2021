export default class Person {
  _firstname = null;
  _lastname = null;
  _phoneNumber = null;
  _email = null;

  setFirstname(firstname) {
    this._firstname = firstname.toString();
  }
  getFirstname() {
    return this._firstname;
  }
  setLastname(lastname) {
    this._lastname = lastname.toString();
  }
  getLastname() {
    return this._lastname;
  }
  setPhoneNumber(phoneNumber) {
    this._phoneNumber = phoneNumber.toString();
  }
  getPhoneNumber() {
    return this._phoneNumber;
  }
  setEmail(email) {
    this._email = email.toString();
  }
  getEmail() {
    return this._email;
  }

  getPersonToString() {
    return JSON.stringify({
      firstname: this._firstname,
      lastname: this._lastname,
      phoneNumber: this._phoneNumber,
      email: this._email,
    });
  }

  getPerson() {
    return {
      firstname: this._firstname,
      lastname: this._lastname,
      phoneNumber: this._phoneNumber,
      email: this._email,
    };
  }
}
