import { Component, State, h } from '@stencil/core';
import { Database } from '../../database';

@Component({
  tag: 'app-home'
})
export class AppHome {

  @State()
  items:any[];

  constructor() {
    this.items = [];    
  }

  componentWillLoad() {
    return Database.getInstance()
      .then(database => database.list())
      .then(contacts => {        
        for (let i = 0; i < contacts.length; i++) {
          this.items.push({
            key: contacts[i].key ,
            name: contacts[i].firstname + " " + contacts[i].lastname ,
            phone: contacts[i].phone
          });
        }
      });
  }

  toast() {
    const toast = document.createElement('ion-toast');
    toast.message = 'Contact item was removed.';
    toast.duration = 30000;
    document.body.appendChild(toast);
    return toast.present();
  }

  delete(item, index) {
    return Database.getInstance()
      .then(database => database.delete(item.key))
      .then(() => {
        this.items.splice(index, 1)
        this.items = [...this.items];
        return this.toast();
      });
  }

  renderItem(item, index) {
    return (
      <ion-item-sliding>
        <ion-item>
          <ion-label>
            <h3>{item.name}</h3>
            <p>{item.phone}</p>
          </ion-label>
          <ion-icon slot="end" name="call"></ion-icon>
        </ion-item>
        <ion-item-options onIonSwipe={() => this.delete(item, index)} side="end">
          <ion-item-option color="danger">
            DELETE <ion-icon slot="icon-only" name="trash"></ion-icon>
          </ion-item-option>
        </ion-item-options>
      </ion-item-sliding>
    );
  }

  render() {
    return [
      <ion-header>
        <ion-toolbar color="primary">
          <ion-buttons slot="start">
            <ion-menu-button>              
            </ion-menu-button>
          </ion-buttons>
          <ion-title>Contact App</ion-title>
          <ion-buttons slot="end">
            <ion-button href="form">
              <ion-icon slot="icon-only" name="add-circle-outline"></ion-icon>
            </ion-button>
          </ion-buttons>
        </ion-toolbar>
      </ion-header>,

      <ion-content>
        <ion-virtual-scroll items={this.items} approxItemHeight={65} renderItem={(item, index) => { return this.renderItem(item, index); }} id="virtual">
      </ion-virtual-scroll>
      </ion-content>
    ];
  }
}
