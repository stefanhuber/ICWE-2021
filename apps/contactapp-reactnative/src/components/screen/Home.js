import React from 'react';
import {StyleSheet, StatusBar, View} from 'react-native';
import ContactList from '../snippets/ContactList';

export default function Home({navigation, route}) {
  return (
    <View style={styles.outerContainer}>
      <StatusBar
        translucent={true}
        backgroundColor="rgba(0, 0, 0, 0.20)"
        barStyle="dark-content"
      />
      <ContactList style={styles.innterContainer} />
    </View>
  );
}

const styles = StyleSheet.create({
  outerContainer: {
    flex: 1,
    alignItems: 'flex-start',
    justifyContent: 'flex-start',
  },
  button: {
    marginBottom: 20,
  },
  innterContainer: {
    display: 'flex',
    flex: 1,
    width: '100%',
  },
});
