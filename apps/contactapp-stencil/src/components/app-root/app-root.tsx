import { Component, State, h } from '@stencil/core';
import { Database } from '../../database';

let nav;

@Component({
  tag: 'app-root'
})
export class AppRoot {

  @State() change = 0;

  componentDidLoad() {
    nav = document.querySelector('ion-nav');
  }

  addDemoEntries() {
    Database.getInstance().then(database => {
      database.addDemoEntries(100).then(() => {        
        nav.insert(0, 'app-home');
        nav.pop();
      });      
    });
  }

  removeDatabaseEntries() {
    Database.getInstance().then(database => {
      database.deleteAll().then(() => {        
        nav.insert(0, 'app-home');
        nav.pop();
      });      
    });
  }

  render() {
    return (
      <ion-app>
        <ion-router useHash={true}>
          <ion-route url="/home" component="app-home" />
          <ion-route url="/form" component="app-form" />
          <ion-route-redirect from="/" to="/home" />
        </ion-router>

        <ion-split-pane content-id="menu-content">

          <ion-menu side="start" content-id="menu-content">
            <ion-header>
              <div class="drawer-gradient">
                <ion-icon name="call"></ion-icon>
                <h3>Contact App</h3>
                <p>Performance Demo</p>
              </div>
            </ion-header>
            <ion-content>
              <ion-list>
                <ion-menu-toggle autoHide={false}>
                  <ion-item href="form">
                    <ion-icon slot="start" name="call"></ion-icon>
                    <ion-label>Home</ion-label>  
                  </ion-item>
                </ion-menu-toggle>
                <ion-menu-toggle autoHide={false}>
                  <ion-item href="form">
                    <ion-icon slot="start" name="add-circle-outline"></ion-icon>
                    <ion-label>Add Entry</ion-label>  
                  </ion-item>
                </ion-menu-toggle>
                <ion-menu-toggle autoHide={false}>
                  <ion-item onClick={() => this.addDemoEntries()}>
                    <ion-icon slot="start" name="add-circle-outline"></ion-icon>
                    <ion-label>Add 100 Entries</ion-label>  
                  </ion-item>
                </ion-menu-toggle>
                <ion-menu-toggle autoHide={false}>
                  <ion-item onClick={() => this.removeDatabaseEntries()}>
                    <ion-icon slot="start" name="remove-circle-outline"></ion-icon>
                    <ion-label>Remove All Entries</ion-label>  
                  </ion-item>
                </ion-menu-toggle>              
              </ion-list>
            </ion-content>
          </ion-menu>
          
          <ion-nav id="menu-content" />

        </ion-split-pane>
      </ion-app>
    );
  }
}
