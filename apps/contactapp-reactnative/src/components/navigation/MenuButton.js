import React from 'react';
import Icon from 'react-native-vector-icons/MaterialIcons';
import {COLOR_PRIMARY, ICON_SIZE, ICON_RADIUS} from '../../utils/Principles';

export default class SaveButton extends React.Component {
  onPress = () => {
    if (this.props.onPress) this.props.onPress();
  };

  render() {
    return (
      <Icon.Button
        name="menu"
        type="MaterialIcons"
        size={ICON_SIZE}
        borderRadius={ICON_RADIUS}
        backgroundColor={COLOR_PRIMARY}
        iconStyle={{marginRight: 0}}
        onPress={() => this.props.navigation.openDrawer()}
      />
    );
  }
}
