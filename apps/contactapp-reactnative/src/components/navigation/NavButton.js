import React from 'react';
import Icon from 'react-native-vector-icons/MaterialIcons';
import {COLOR_PRIMARY, ICON_SIZE, ICON_RADIUS} from '../../utils/Principles';

export default class NavButton extends React.Component {
  onPress = () => {
    if (this.props.navigation && this.props.route) {
      this.props.navigation.navigate('ContactApp', {
        screen: this.props.route,
      });
    }
  };

  render() {
    return (
      <Icon.Button
        name={this.props.route === 'Home' ? 'arrow-back' : 'add-circle-outline'}
        type="MaterialIcons"
        size={ICON_SIZE}
        borderRadius={ICON_RADIUS}
        backgroundColor={COLOR_PRIMARY}
        iconStyle={{marginRight: 0}}
        onPress={() => this.onPress()}
      />
    );
  }
}
