import Database from './Database';

const database = new Database();

export default class ServiceLoader {
  static database() {
    return database;
  }
}
