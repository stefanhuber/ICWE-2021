import { Component, h } from '@stencil/core';
import { Database } from '../../database';

const nav = document.querySelector('ion-nav');

@Component({
  tag: 'app-form'
})
export class AppForm {

  firstname:string = "";
  lastname:string = "";
  phone:string = "";

  save() {
    Database.getInstance()
      .then(database => database.add({
        firstname: this.firstname,
        lastname: this.lastname,
        phone: this.phone
      }))
      .then(() => {
        if (nav.canGoBack()) {
          nav.removeIndex(0);
        }
        nav.insert(0, 'app-home');
        nav.pop();
      });
  }

  render() {
    return [
      <ion-header>
        <ion-toolbar color="primary">
          <ion-buttons slot="start">
            <ion-back-button></ion-back-button>
          </ion-buttons>
          <ion-title>Contact App</ion-title>
          <ion-buttons slot="end">
              <ion-button onClick={() => this.save()}>
                <ion-icon slot="icon-only" name="paper-plane"></ion-icon>
              </ion-button>
          </ion-buttons>
        </ion-toolbar>
      </ion-header>,

      <ion-content class="ion-padding">
        <ion-list>
            <ion-item>
                <ion-label position="stacked">Firstname</ion-label>
                <ion-input onIonChange={event => this.firstname = event.detail.value}></ion-input>
            </ion-item>
            <ion-item>
                <ion-label position="stacked">Lastname</ion-label>
                <ion-input onIonChange={event => this.lastname = event.detail.value}></ion-input>
            </ion-item>
            <ion-item>
                <ion-label position="stacked">Phone</ion-label>
                <ion-input onIonChange={event => this.phone = event.detail.value}></ion-input>
            </ion-item>
            <ion-item>
                <ion-label position="stacked">Email</ion-label>
                <ion-input></ion-input>
            </ion-item>
        </ion-list>
      </ion-content>
    ];
  }
}
