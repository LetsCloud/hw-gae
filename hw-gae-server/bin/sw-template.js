var cacheName = '{{cacheName}}';
var filesToCache = [  {{#each filesToCache}}
'{{.}}'{{#unless @last}},{{/unless}}
    {{/each}}
    ];

// INSTALL A SERVICE WORKER
//
// Inside of our install callback, we need to take the following steps:
// 1. Open a cache.
// 2. Cache our files.
// 3. Confirm whether all the required assets are cached or not.
//
// Here you can see we call caches.open() with our desired cache name, after
// which we call cache.addAll() and pass in
// our array of files. This is a chain of promises (caches.open() and
// cache.addAll()). The event.waitUntil() method
// takes a promise and uses it to know how long installation takes, and whether
// it succeeded or not.
//
// If all the files are successfully cached, then the service worker will be
// installed. If any of the files fail to
// download, then the install step will fail. This allows you to rely on having
// all the assets that you defined,
// but does mean you need to be careful with the list of files you decide to
// cache in the install step. Defining a
// long list of files will increase the chance that one file may fail to cache,
// leading to your service worker not
// getting installed.
self.addEventListener('install', function (e) {
	console.log('[ServiceWorker] Install');
    e.waitUntil(
        caches.open(cacheName)
        	.then(function (cache) {
        		console.log('[ServiceWorker] Caching app shell');
        		return cache.addAll(filesToCache);
        	})
    );
});

// CACHE AND RETURN REQUESTS
//
// After a service worker is installed and the user navigates to a different
// page or refreshes, the service worker
// will begin to receive fetch events
//

self.addEventListener('fetch', function(event) {
    event.respondWith(
        // Try the cache
        caches.match(event.request).then(function(response) {
            // Fall back to network
            return response || fetch(event.request);
        }).catch(function() {
            // If both fail, show a generic fallback:
            // return caches.match('/offline.html');
            // However, in reality you'd have many different
            // fallbacks, depending on URL & headers.
            // Eg, a fallback silhouette image for avatars.
            sendMessageToAllClients("failingServer");
            return new Response("Can't Connect to server");
        })
    );
});

// UPDATE SERVICE WORKER
//
self.addEventListener('activate', function (e) {
    console.log('[ServiceWorker] Activate');
    e.waitUntil(
        caches.keys().then(function (keyList) {
            return Promise.all(keyList.map(function (key) {
                console.log('[ServiceWorker] Removing old cache', key);
                if (key !== cacheName) {
                    return caches.delete(key);
                }
            }));
        })
    );
});

// Set up a listener for messages posted from the service worker.
// The service worker is set to post a message to all its clients once it's run
// its activation
// handler and taken control of the page, so you should see this message event
// fire once.
// You can force it to fire again by visiting this page in an Incognito window.
self.addEventListener('message', function (e) {
    if (e.data == 'skipWaiting') {
        self.skipWaiting();
    }
});

// The PushEvent interface of the Push API represents a push message that has
// been received.
// This event is sent to the global scope of a ServiceWorker.
// It contains the information sent from an application server to a
// PushSubscription.
// self.addEventListener('push', function(event) {
// console.log('[Service Worker] Push Received.');
// console.log(`[Service Worker] Push had this data: "${event.data.text()}"`);
//    
// var jsonObject = JSON.parse(event.data.text());
//
// const title = jsonObject.title;
// const options = {
// body: jsonObject.description,
// icon: jsonObject.image
// };
//
// event.waitUntil(self.registration.showNotification(title, options));
// });

//
// Notifications API
//

// The notificationclick event is fired to indicate that a system notification
// spawned
// by ServiceWorkerRegistration.showNotification() has been clicked.
self.addEventListener('notificationclick', function(event) {
    console.log('[Service Worker] Notification click Received.');

    event.notification.close();

    event.waitUntil(
        clients.openWindow('https://www.google.com.ph')
    );
});

// *function sendMessageToClient(client, message){
// * return new Promise(function(resolve, reject){
// * var messageChannel = new MessageChannel();

// * messageChannel.port1.onmessage = function(e){
// * if(e.data.error){
// * reject(e.data.error);
// * }else{
// * resolve(e.data);
// * }
// * };

// * client.postMessage(message, [messageChannel.port2]);
// * });
// *}

// *function sendMessageToAllClients(msg){
// * clients.matchAll().then(clients => {
// * clients.forEach(client => {
// * sendMessageToClient(client, msg).then(m => console.log("SW Received
// Message: "+m));
// * })
// * })
// *}

// Import and configure the Firebase SDK
// These scripts are made available when the app is served or deployed on
// Firebase Hosting
// If you do not serve/host your project using Firebase Hosting see
// https://firebase.google.com/docs/web/setup
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase-messaging.js');
importScripts('https://www.gstatic.com/firebasejs/4.8.1/firebase.js');

firebase.initializeApp({messagingSenderId: "489469080035"});
	  
const messaging = firebase.messaging();

// If you would like to customize notifications that are received in the
// background (Web app is closed or not in browser focus) then you should
// implement this optional method.
// [START background_handler]
// messaging.setBackgroundMessageHandler(function(payload) {
// console.log('[firebase-messaging-sw.js] Received background message ',
// payload);
	// Customize notification here
// const notificationTitle = 'Background Message Title';
// const notificationOptions = {
// body: 'Background Message body.',
// icon: '/firebase-logo.png'
// };
//
// return self.registration.showNotification(notificationTitle,
// notificationOptions);
// });
// [END background_handler]
