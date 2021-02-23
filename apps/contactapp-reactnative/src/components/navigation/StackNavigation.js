import * as React from 'react';
import Home from '../screen/Home';
import AddEntry from '../screen/AddEntry';
import {createStackNavigator, TransitionPresets} from '@react-navigation/stack';
import {
  COLOR_PRIMARY,
  COLOR_FONT_LIGHT,
  DISTANCE_SM,
} from '../../utils/Principles';
import MenuButton from '../navigation/MenuButton';
import NavButton from '../navigation/NavButton';

const StackApp = createStackNavigator();

export default function StackAppScreen({navigation, route}) {
  return (
    <StackApp.Navigator screenOptions={styles.header}>
      <StackApp.Screen
        name="Home"
        component={Home}
        options={{
          title: 'Contact App',
          headerLeft: () => <MenuButton navigation={navigation} />,
          headerRight: () => (
            <NavButton navigation={navigation} route="AddEntry" />
          ),
        }}
      />

      <StackApp.Screen
        name="AddEntry"
        component={AddEntry}
        options={({navigation, route}) => ({
          title: 'Add Entry',
          headerLeft: () => <NavButton navigation={navigation} route="Home" />,
        })}
      />
    </StackApp.Navigator>
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
    ...TransitionPresets.DefaultTransition,
  },
};
