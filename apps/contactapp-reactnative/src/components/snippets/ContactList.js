import React, {useState} from 'react';
import {StyleSheet, View, FlatList} from 'react-native';
import ContactItem from './ContactItem';
import ServiceLoader from '../../services/ServiceLoader';
import Helpers from '../../utils/Helpers';
import {COLOR_LIGHT_GRAY} from '../../utils/Principles';
import {useFocusEffect} from '@react-navigation/native';
import {useIsDrawerOpen} from '@react-navigation/drawer';

let shouldUpdate = false;
export default function ContactList(props) {
  const [_contactList, setContactList] = useState(null);
  const drawer = useIsDrawerOpen();
  let _isTouched = false;

  const updateContactList = () => {
    ServiceLoader.database()
      .getAllContacts()
      .then(result => {
        const sortedList = result.sort(
          (a, b) => parseInt(a[0]) - parseInt(b[0]),
        );
        setContactList(sortedList);
      });
  };

  useFocusEffect(
    React.useCallback(() => {
      updateContactList();
    }, [null]),
  );

  if (drawer) {
    /**
     * Update the list by toggeling the drawer-menu
     */
    shouldUpdate = true;
  } else {
    if (!drawer && shouldUpdate) {
      shouldUpdate = false;
      updateContactList();
    }
  }

  const removeItem = key => {
    /**
     * Remove Item from the database by the given key.
     * @pram: key -> Database key of the Item in the Contact List
     */

    if (!_isTouched) {
      ServiceLoader.database()
        .removeContact(key)
        .then(result => {
          if (result) {
            Helpers.showToast('Contact item was removed.');
          }
        });
    }
  };

  const updateItem = index => {
    /**
     * Use a temp Array for the visible Contact List
     * @pram: key -> Database key of the Item in the Contact List
     */

    if (!_isTouched) {
      let temp = [..._contactList];
      temp.splice(index, 1);
      setContactList(temp);
    }
  };

  const setIsTouched = param => {
    /**
     * @pram: boolean -> If Item is touched = true else false
     */
    _isTouched = param;
  };

  return (
    <View style={styles.container}>
      <FlatList
        data={_contactList}
        extraData={_contactList}
        keyExtractor={item => item[0]}
        ItemSeparatorComponent={FlatListItemSeparator}
        renderItem={({item, index}) => (
          <ContactItem
            key={item[0]}
            firstname={item[1].firstname}
            lastname={item[1].lastname}
            phoneNumber={item[1].phoneNumber}
            id={item[0]}
            remove={() => removeItem(item[0])}
            update={() => updateItem(index)}
            setTouch={param => setIsTouched(param)}
          />
        )}
      />
    </View>
  );
}

const FlatListItemSeparator = () => {
  return (
    <View
      style={{
        height: 1,
        width: '100%',
        backgroundColor: COLOR_LIGHT_GRAY,
      }}
    />
  );
};

const styles = StyleSheet.create({
  container: {
    width: '100%',
  },
});
