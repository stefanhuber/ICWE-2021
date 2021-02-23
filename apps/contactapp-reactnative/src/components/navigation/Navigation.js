import React from 'react';
import {createDrawerNavigator} from '@react-navigation/drawer';
import {NavigationContainer, DefaultTheme} from '@react-navigation/native';
import StackNavigation from './StackNavigation';
import DrawerContent from './DrawerContent';
import {
  COLOR_PRIMARY,
  COLOR_FONT_LIGHT,
  DISTANCE_SM,
} from '../../utils/Principles';

const Drawer = createDrawerNavigator();

export default function Navigation(param) {
  return (
    <NavigationContainer theme={DefaultTheme}>
      <Drawer.Navigator
        screenOptions={styles}
        drawerContent={props => <DrawerContent {...props} />}>
        <Drawer.Screen
          name="ContactApp"
          component={StackNavigation}
          options={{
            title: 'Contact App',
          }}
        />
      </Drawer.Navigator>
    </NavigationContainer>
  );
}

const styles = {
  header: {
    headerStyle: {
      backgroundColor: COLOR_PRIMARY,
    },
    headerTintColor: COLOR_FONT_LIGHT,
    headerLeftContainerStyle: {
      marginLeft: DISTANCE_SM,
    },
    headerRightContainerStyle: {
      marginRight: DISTANCE_SM,
    },
  },
};
