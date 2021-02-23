import React from 'react';
import {StyleSheet, View} from 'react-native';
import InputField from '../snippets/InputField';
import {DISTANCE_SM} from '../../utils/Principles';
import ServiceLoader from '../../services/ServiceLoader';
import SaveButton from '../navigation/SaveButton';
import Person from '../../utils/Person';
import Helpers from '../../utils/Helpers';

export default function AddEntry({navigation, route}) {
  let person = new Person();

  navigation.setOptions({
    headerRight: () => (
      <SaveButton
        navigation={navigation}
        onPress={() => {
          saveContact();
        }}
      />
    ),
  });

  const saveContact = () => {
    ServiceLoader.database()
      .addContact(Helpers.getUniqueKey(), person.getPersonToString())
      .then(success => {
        if (success) {
          ServiceLoader.database()
            .getAllContacts()
            .then(result => {
              navigation.popToTop();
            });
        }
      });
  };

  const handleInput = (text, index) => {
    switch (index) {
      case 0:
        person.setFirstname(text);
        break;
      case 1:
        person.setLastname(text);
        break;
      case 2:
        person.setPhoneNumber(text);
        break;
      case 3:
        person.setEmail(text);
        break;
    }
  };

  return (
    <View style={styles.container}>
      <InputField setItem={text => handleInput(text, 0)} name="Firstname" />
      <InputField setItem={text => handleInput(text, 1)} name="Lastname" />
      <InputField setItem={text => handleInput(text, 2)} name="Phone" />
      <InputField setItem={text => handleInput(text, 3)} name="Email" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'flex-start',
    justifyContent: 'flex-start',
    paddingLeft: DISTANCE_SM,
    paddingRight: DISTANCE_SM,
  },
});
