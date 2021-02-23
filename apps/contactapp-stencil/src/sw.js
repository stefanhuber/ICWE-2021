self.addEventListener('install', (event) => {
    event.waitUntil(
        caches.open('contact-app-v1')
            .then(cache => cache.addAll([                
                'index.html',
                'manifest.json'
            ]))
    );
});

self.addEventListener('fetch', (event) => {
    event.respondWith(
        caches.match(event.request).then((cacheResponse) => {
            if (cacheResponse) {
                return cacheResponse;
            } else {
                return fetch(event.request)
                    .then((response) => {
                        return caches
                            .open('contact-app-v1')
                            .then((cache) => {
                                cache.put(event.request, response.clone());
                                return response;
                            });
                    });
            }
        })
    );
});