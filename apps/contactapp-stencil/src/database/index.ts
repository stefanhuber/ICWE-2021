const dbname = "contact_data";
const dbversion = 2;

export class Database {

    protected static instance:Database;
    protected db:IDBDatabase;

    static getInstance(): Promise<Database> {
        return new Promise<Database>((resolve) => {
            if (!Database.instance) {
                const instance = new Database();
                instance.init().then(() => {
                    Database.instance = instance;
                    resolve(Database.instance);
                })
            } else {
                resolve(Database.instance);
            }
        });
    }

    list(): Promise<any> {
        return new Promise<any>((resolve, reject) => {
            const transaction = this.db.transaction(["contacts"], "readonly");
            const request = transaction.objectStore('contacts').openCursor();
            const result = [];
            
            request.onsuccess = () => {
                const cursor = request.result;

                if (cursor) {
                    result.push({
                        key: cursor.primaryKey,
                        firstname: cursor.value.firstname,
                        lastname: cursor.value.lastname,
                        phone: cursor.value.phone,
                    })
                    cursor.continue();
                } else {
                    resolve(result);
                }
            };
            request.onerror = () => {
                reject();
            }
        });
    }

    count(): Promise<number> {
        return new Promise<number>((resolve, reject) => {
            const transaction = this.db.transaction(["contacts"], "readonly");
            const request = transaction.objectStore('contacts').count();
            request.onsuccess = () => {
                resolve(request.result);
            };
            request.onerror = () => {
                reject();
            }
        });
    }

    addDemoEntries(count): Promise<void> {
        return new Promise<void>(async (resolve) => {
            for (let i = 0; i < count; i++) {
                let r = Math.floor(Math.random() * 10000);

                await this.add({
                    firstname: "Firstname",
                    lastname: "Lastname " + r,
                    phone: "+43 2342 23424 " + r
                })
            }
            resolve();
        });
    }

    add(contact): Promise<void> {
        return new Promise<void>((resolve, reject) => {
            const transaction = this.db.transaction(["contacts"], "readwrite");
            const request = transaction.objectStore('contacts').add(contact);
            request.onsuccess = () => {
                resolve();
            };
            request.onerror = () => {
                reject();
            }
        });
    }

    delete(key): Promise<void> {
        return new Promise<void>((resolve, reject) => {
            const transaction = this.db.transaction(["contacts"], "readwrite");
            const request = transaction.objectStore('contacts').delete(key);
            request.onsuccess = () => {
                resolve();
            };
            request.onerror = () => {
                reject();
            }
        });
    }

    deleteAll(): Promise<void> {
        return new Promise<void>((resolve, reject) => {
            const transaction = this.db.transaction(["contacts"], "readwrite");
            const request = transaction.objectStore('contacts').delete(IDBKeyRange.lowerBound(0));
            request.onsuccess = () => {
                resolve();
            };
            request.onerror = () => {
                reject();
            }
        });
    }

    init() {
        return new Promise<IDBDatabase> ((resolve, reject) => {
            const request = indexedDB.open(dbname, dbversion);
                       
            request.onerror = () => {
                reject();
            };

            request.onsuccess = () => {
                if (!this.db) {
                    this.db = request.result;    
                }
                resolve(this.db);
            };

            request.onupgradeneeded = () => {           
                this.db = request.result;       
                const objectStore = this.db.createObjectStore('contacts', {"autoIncrement": true});
                objectStore.createIndex('lastname', 'lastname', { unique: false });
            };
        });        
    }

}