# PWA

`npm run build` will build the PWA into the `www` directory. For deployment active the service worker by uncommenting inside `www/index.html`:

```
<script>
    if ('serviceWorker' in navigator) {
      navigator.serviceWorker.register('sw.js')
          .catch(console.error);
    }
</script>
```

# Capacitor

`npx cap add android` to add the android project. `npx cap sync` to sync the `www` directory with the capacitor project. Build the `apk` by opening the project with Android Studio.