import ServiceLoader from '../services/ServiceLoader';
import Snackbar from 'react-native-snackbar';
import Person from './Person';

export default class Helpers {
  static addDummys = () => {
    /**
     * Add Dummys to the Database
     */
    let person = new Person();

    for (let i = 1; i < 201; i++) {
      person.setFirstname('Firstname' + i);
      person.setLastname('Lastname ');
      person.setPhoneNumber('0043 1234567' + ' ' + i);
      person.setEmail('mail@' + i + '.com');
      ServiceLoader.database()
        .addContact(i.toString(), person.getPersonToString())
        .then(result => {});
    }
  };

  static getUniqueKey() {
    return Date.now().toString();
  }

  static showToast = text => {
    Snackbar.show({
      text: text,
      duration: Snackbar.LENGTH_SHORT,
    });
  };
}
