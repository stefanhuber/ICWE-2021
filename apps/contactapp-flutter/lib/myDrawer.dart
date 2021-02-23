import 'package:flutter/material.dart';
import 'package:contactappflutter/variables.dart';

class MyDrawer extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return  Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            UserAccountsDrawerHeader(
              accountName: Text(MyScreens.HOME_TITLE),
              accountEmail: Text('Performance Demo',style: TextStyle(color: MyColors.FONT_MEDIUM),),
              currentAccountPicture: Icon(Icons.phone,color: MyColors.FONT_LIGHT),
              decoration: BoxDecoration(
                color: MyColors.PRIMARY,
              ),
            ),

            ListTile(
              title: Text('Home'),
              leading: Icon(Icons.home),
              onTap: () {
                // Check route name if it is same like current, pop context.
                if(ModalRoute.of(context).settings.name == MyScreens.HOME_ROUTE || ModalRoute.of(context).settings.name == '/'){
                  Navigator.pop(context);
                } else {
                  Navigator.pushNamed(context, MyScreens.HOME_ROUTE);
                }
              },
            ),
            ListTile(
              title: Text(MyScreens.ADD_ENTRY_TITLE),
              leading: Icon(Icons.add_circle_outline),
              onTap: () {
                // Check route name if it is same like current, pop context.
                if(ModalRoute.of(context).settings.name == MyScreens.ADD_ENTRY_ROUTE){
                  Navigator.pop(context);
                } else {
                  Navigator.pushNamed(context, MyScreens.ADD_ENTRY_ROUTE);
                }
              },
            ),
          ],
        ),
      );
  }
}
