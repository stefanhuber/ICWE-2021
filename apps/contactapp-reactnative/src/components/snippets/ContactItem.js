import React from 'react';
import {
  StyleSheet,
  View,
  Text,
  ScrollView,
  SafeAreaView,
  useWindowDimensions,
} from 'react-native';
import {
  COLOR_FONT_MEDIUM,
  COLOR_LIGHT_GRAY,
  ICON_SIZE,
  ICON_RADIUS,
  COLOR_FONT_DARK,
} from '../../utils/Principles';
import Icon from 'react-native-vector-icons/MaterialIcons';

export default function ContactItem({
  firstname,
  lastname,
  phoneNumber,
  remove,
  update,
  setTouch,
}) {
  const {width: windowWidth} = useWindowDimensions();

  return (
    <SafeAreaView style={styles.outerContainer}>
      <ScrollView
        horizontal={true}
        pagingEnabled
        showsHorizontalScrollIndicator={false}
        onScroll={event => {
          if (event.nativeEvent.contentOffset.x > windowWidth / 2) {
            remove();
            update();
          }
        }}
        scrollEventThrottle={1}
        onScrollBeginDrag={event => setTouch(true)}
        onScrollEndDrag={event => setTouch(false)}>
        <View style={(styles.container, {width: windowWidth})}>
          <Text style={styles.name}>
            {firstname} {lastname}
          </Text>
          <Text style={styles.phoneNumber}>{phoneNumber}</Text>

          <Icon
            name="local-phone"
            type="MaterialIcons"
            size={ICON_SIZE * 0.75}
            borderRadius={ICON_RADIUS * 0.75}
            backgroundColor="transparent"
            style={styles.icon}
          />
        </View>
        <View style={{width: windowWidth, height: 10}} />
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  outerContainer: {
    width: '100%',
    height: 58,
  },

  container: {
    display: 'flex',
    flex: 1,
  },

  icon: {
    position: 'absolute',
    top: 21,
    right: 12,
    color: COLOR_LIGHT_GRAY,
    height: ICON_SIZE,
    width: ICON_SIZE,
  },

  name: {
    fontSize: 14,
    position: 'absolute',
    top: 12,
    left: 12,
    color: COLOR_FONT_DARK,
  },

  phoneNumber: {
    fontSize: 14,
    position: 'absolute',
    bottom: 12,
    left: 12,
    color: COLOR_FONT_MEDIUM,
  },
});
