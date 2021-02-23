import 'package:flutter/material.dart';
import 'package:contactappflutter/myDrawer.dart';
import 'package:contactappflutter/variables.dart';


class Home extends StatefulWidget {
  Home({Key key}) : super(key: key);

  @override
  HomeState createState() {
    return HomeState();
  }
}

class HomeState extends State<Home> {
  // Generate 200 Contact Items
  final items = List<String>.generate(350, (i) => "Firstname ${i + 1} Lastname \n0043 1234567 ${i +  1}");

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(MyScreens.HOME_TITLE),
          backgroundColor: MyColors.PRIMARY,
          actions: <Widget>[
            // action button
            IconButton(
              icon: Icon(
                Icons.add_circle_outline, color: MyColors.FONT_LIGHT,
              ),
              onPressed: () {
                Navigator.pushNamed(context, MyScreens.ADD_ENTRY_ROUTE);
              },
            ),
          ],
        ),
        drawer: MyDrawer(),
        body: ListView.separated(
          itemBuilder: (context, index) {
            final item = items[index];

            return Dismissible(
              key: Key(item),
              // Delete Item by slide
              direction: DismissDirection.endToStart,
              onDismissed: (direction) {
                setState(() {
                  items.removeAt(index);
                });
                // Show Snackbar
                Scaffold.of(context)
                    .showSnackBar(SnackBar(content: Text("Contact item was removed.")));
              },
              background: Container(color: Colors.transparent,),
              child: ListTile(
                contentPadding: EdgeInsets.symmetric(
                    horizontal: 12.00, vertical: 0),
                title: Text('$item',style: TextStyle(color: MyColors.FONT_MEDIUM),),
                trailing: Icon(
                  Icons.phone, color: MyColors.FONT_MEDIUM,
                ),
              ),
            );
          },
          separatorBuilder: (context, index) {
            return Divider(
              height: 0,
            );
          },
          itemCount: items.length,
        ),
      );
  }
}
