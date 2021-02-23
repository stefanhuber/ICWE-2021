import AsyncStorage from '@react-native-community/async-storage';

export default class Database {
  /**
   * SQLite Database to store Contacts. All Contacts are storey by key (string) : value (string)
   */
  addContact = async (key, value) => {
    /**
     * Add a Contact to the SQLite database
     * Value: key: String, value: string
     * @Return: boolean -> success = true else false
     */

    try {
      await AsyncStorage.setItem(key.toString(), value);
      return true;
    } catch (error) {
      console.log(error);
      return false;
    }
  };

  removeContact = async key => {
    /**
     * Remove a Contact to the SQLite database
     * @param: String -> key = The unique key from the entry
     * @Return: boolean -> success = true else false
     */

    try {
      await AsyncStorage.removeItem(key.toString());
      return true;
    } catch (e) {
      console.log(error);
      return false;
    }
  };

  getContactById = async key => {
    /**
     * Get a Contact with the given key from the SQLite database
     * @Return: JSON Contact Object
     */

    try {
      const item = await AsyncStorage.getItem(key.toString());
      if (item !== null) {
        let contact = [];
        item.map(item => {
          contact.push([item[0], JSON.parse(item[1])]);
        });
        return contact;
      } else {
        return false;
      }
    } catch (error) {
      console.log(error);
      return false;
    }
  };

  removeAll = async () => {
    /**
     * Remove all Contacts from the SQLite database
     * @Return: boolean -> success = true else false
     */
    let keys = [];

    try {
      keys = await AsyncStorage.getAllKeys();
      await AsyncStorage.multiRemove(keys);
      return true;
    } catch (error) {
      console.log(error);
      return false;
    }
  };

  getAllKeys = async () => {
    /**
     * Get all Contact keys from the SQLite database
     * @Return Array<String> An Array which contains all Keys
     */
    let keys = [];

    try {
      keys = await AsyncStorage.getAllKeys();
      return keys;
    } catch (error) {
      console.log(error);
      return false;
    }
  };

  getAllContacts = async () => {
    /**
     * Get all Contacts from the SQLite database
     * @Return: Array<Object> An Array wich contains all Contact Objects
     */
    try {
      const keys = await AsyncStorage.getAllKeys();
      const stringArray = await AsyncStorage.multiGet(keys);
      if (stringArray !== null) {
        let array = [];
        stringArray.map(item => {
          array.push([item[0], JSON.parse(item[1])]);
        });
        return array;
      } else {
        return false;
      }
    } catch (error) {
      console.error(error);
      return false;
    }
  };
}
