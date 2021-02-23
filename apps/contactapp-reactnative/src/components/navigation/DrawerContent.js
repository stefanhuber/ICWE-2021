import React from 'react';
import {View, Text, StyleSheet} from 'react-native';
import LinearGradient from 'react-native-linear-gradient';
import {
  COLOR_CENTER,
  COLOR_END,
  COLOR_START,
  COLOR_FONT_LIGHT,
  DISTANCE_SM,
  TEXT_APP_NAME,
  COLOR_FONT_TRANS,
  ICON_RADIUS,
  ICON_SIZE,
  COLOR_PRIMARY,
} from '../../utils/Principles';
import Icon from 'react-native-vector-icons/MaterialIcons';
import {DrawerItem} from '@react-navigation/drawer';
import Helpers from '../../utils/Helpers';
import ServiceLoader from '../../services/ServiceLoader';

export default function DrawerContent(props) {
  return (
    <View style={styles.containerNav}>
      <LinearGradient
        start={{x: 0, y: 0}}
        end={{x: 1, y: 0}}
        colors={[COLOR_START, COLOR_CENTER, COLOR_END]}
        style={styles.containerHeader}>
        <View style={styles.containerHeader}>
          <Icon.Button
            name="local-phone"
            type="MaterialIcons"
            size={ICON_SIZE}
            borderRadius={ICON_RADIUS}
            backgroundColor="transparent"
            iconStyle={{marginRight: 0}}
          />
          <Text style={{color: COLOR_FONT_LIGHT}}>{TEXT_APP_NAME}</Text>
          <Text style={{color: COLOR_FONT_TRANS}}>Performance Demo</Text>
        </View>
      </LinearGradient>

      <View style={styles.menuBtns}>
        <DrawerItem
          style={styles.btn}
          icon={({color, size}) => (
            <Icon name="local-phone" color={COLOR_PRIMARY} size={ICON_SIZE} />
          )}
          label="Home"
          onPress={() =>
            props.navigation.navigate('ContactApp', {
              screen: 'Home',
            })
          }
        />

        <DrawerItem
          style={styles.btn}
          icon={({color, size}) => (
            <Icon
              name="add-circle-outline"
              color={COLOR_PRIMARY}
              size={ICON_SIZE}
            />
          )}
          label="Add Entry"
          onPress={() =>
            props.navigation.navigate('ContactApp', {
              screen: 'AddEntry',
            })
          }
        />

        <DrawerItem
          style={styles.btn}
          icon={({color, size}) => (
            <Icon
              name="add-circle-outline"
              color={COLOR_PRIMARY}
              size={ICON_SIZE}
            />
          )}
          label="Create 200 Contacts"
          onPress={() => {
            Helpers.addDummys();
            Helpers.showToast('Succeessfully add 200 Contacts!');
            props.navigation.navigate('ContactApp', {
              screen: 'Home',
            });
          }}
        />
        <DrawerItem
          style={styles.btn}
          icon={({color, size}) => (
            <Icon
              name="remove-circle-outline"
              color={COLOR_PRIMARY}
              size={ICON_SIZE}
            />
          )}
          label="Delete All Contacts"
          onPress={() => {
            ServiceLoader.database()
              .removeAll()
              .then(result => {
                if (result) {
                  Helpers.showToast('Succeessfully deleted all Contacts!');
                  props.navigation.navigate('ContactApp', {
                    screen: 'Home',
                  });
                }
              });
          }}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  containerNav: {flex: 1, alignItems: 'center', justifyContent: 'flex-start'},

  containerHeader: {
    alignItems: 'flex-start',
    justifyContent: 'flex-end',
    width: '100%',
    height: 176,
    padding: DISTANCE_SM / 2,
  },

  menuBtns: {
    width: '100%',
    margin: 0,
    padding: 0,
  },

  btn: {
    borderRadius: 0,
    marginLeft: 0,
    marginRight: 0,
  },
});
