import React from 'react';
import {View, TextInput, StyleSheet, Text} from 'react-native';
import {
  DISTANCE_SM,
  COLOR_FONT_MEDIUM,
  COLOR_ACCENT,
} from '../../utils/Principles';

const Input = props => {
  return (
    <TextInput
      {...props}
      editable
      onFocus={() => props.setFocus(true)}
      onBlur={() => props.setFocus(false)}
      style={props.style}
    />
  );
};

export default function InputField(props) {
  const [value, onChangeText] = React.useState('');
  const [focus, onChangeFocus] = React.useState(false);

  return (
    <View style={focus ? styles.containerFocused : styles.container}>
      <Text style={styles.text}>{props.name}</Text>
      <View style={styles.inputContainer}>
        <Input
          multiline
          numberOfLines={1}
          onChangeText={text => {
            onChangeText(text);
            props.setItem(text);
          }}
          value={value}
          style={focus ? styles.textInputFocused : styles.textInput}
          setFocus={param => onChangeFocus(param)}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  containerFocused: {
    width: '100%',
    alignSelf: 'baseline',
    padding: 0,
    marginBottom: DISTANCE_SM / 5,
  },

  container: {
    width: '100%',
    padding: 0,
    marginBottom: DISTANCE_SM / 5,
  },

  inputContainer: {
    padding: 5,
  },

  text: {
    color: COLOR_FONT_MEDIUM,
    fontSize: 14,
  },

  textInput: {
    padding: 0,
    alignSelf: 'baseline',
    width: '100%',
    borderBottomColor: 'gray',
    borderBottomWidth: 1,
    marginBottom: 0.5,
    marginTop: 0.5,
    fontSize: 20,
  },

  textInputFocused: {
    padding: 0,
    width: '100%',
    alignSelf: 'baseline',
    borderBottomColor: COLOR_ACCENT,
    borderBottomWidth: 2,
    fontSize: 20,
  },
});
