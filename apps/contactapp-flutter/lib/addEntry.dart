import 'package:flutter/material.dart';
import 'package:contactappflutter/variables.dart';
import 'package:flutter/rendering.dart';

class AddEntry extends StatefulWidget {
  @override
  _MyCustomFormState createState() => _MyCustomFormState();
}

class _MyCustomFormState extends State<AddEntry> {
  final _controllerFirstName = TextEditingController();
  final _controllerLastName = TextEditingController();
  final _controllerNumber = TextEditingController();
  final _controllerEmail = TextEditingController();

  @override
  void initState() {
    super.initState();
    //_controllerFirstName.addListener(() => print(_controllerFirstName.text));
    //_controllerLastName.addListener(() => print(_controllerLastName.text));
    //_controllerNumber.addListener(() => print(_controllerNumber.text));
    //_controllerEmail.addListener(() => print(_controllerEmail.text));
  }

  @override
  void dispose() {
    _controllerFirstName.dispose();
    _controllerLastName.dispose();
    _controllerNumber.dispose();
    _controllerEmail.dispose();
    _controllerFirstName.text = '';
    _controllerLastName.text = '';
    _controllerNumber.text = '';
    _controllerEmail.text = '';
    super.dispose();
  }

  _printLatestValues() {
     print("firstname: ${_controllerFirstName.text}\n lastname: ${_controllerLastName.text}\n number: ${_controllerNumber.text}\n email: ${_controllerEmail.text}");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(MyScreens.ADD_ENTRY_TITLE),
        backgroundColor: MyColors.PRIMARY,
        actions: <Widget>[
          // action button
          IconButton(
            icon: Icon(
              Icons.save, color: MyColors.FONT_LIGHT,
            ),
            onPressed: () {
              _printLatestValues();
              Navigator.pushNamed(context, MyScreens.HOME_ROUTE);
            },
          ),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.only(left: 16.0, right: 16.0, bottom: 0,top: 0),
        child: Column(
          children: <Widget>[
            TextField(
              style: TextStyle(
                fontSize: 18
              ),
              cursorColor: MyColors.FONT_DARK,
              decoration: InputDecoration(
                  labelText: 'Firstname',
                  labelStyle: TextStyle(
                      height: -1,
                      color: MyColors.FONT_MEDIUM
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: MyColors.ACCENT,width: 2),
                  )
              ),
              controller: _controllerFirstName,
            ),
            TextField(
              style: TextStyle(
                  fontSize: 18
              ),
              cursorColor: MyColors.FONT_DARK,
              decoration: InputDecoration(
                  labelText: 'Lastname',
                  labelStyle: TextStyle(
                      height: -1,
                      color: MyColors.FONT_MEDIUM
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: MyColors.ACCENT,width: 2),
                  )
              ),
              controller: _controllerLastName,
            ),
            TextField(
              style: TextStyle(
                  fontSize: 18
              ),
              cursorColor: MyColors.FONT_DARK,
              decoration: InputDecoration(
                  labelText: 'Phone',
                  labelStyle: TextStyle(
                      height: -1,
                      color: MyColors.FONT_MEDIUM
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: MyColors.ACCENT,width: 2),
                  )
              ),
              controller: _controllerNumber,
            ),
            TextField(
              style: TextStyle(
                  fontSize: 18,
              ),
              cursorColor: MyColors.FONT_DARK,
              decoration: InputDecoration(
                  labelText: 'Email',
                  labelStyle: TextStyle(
                      height: -1,
                    color: MyColors.FONT_MEDIUM
                  ),
                  focusedBorder: UnderlineInputBorder(
                    borderSide: BorderSide(color: MyColors.ACCENT,width: 2),
                  )
              ),
              controller: _controllerEmail,
            ),
          ],
        ),
      ),
    );
  }
}