import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import axios from 'axios';



// Set axios to include cookies with each request
axios.defaults.withCredentials = true;
const root = ReactDOM.createRoot(document.getElementById('root'));
const hasInitialized = localStorage.getItem('hasInitialized');

if (!hasInitialized) {
    // Perform initialization tasks here
    localStorage.setItem('isLoggedIn', 'false');
    localStorage.setItem('hasInitialized', 'true');
    console.log("Initialized isLoggedIn to false");
    console.log("isLoggedIn == false (from index.js)");
}
root.render(
 // <React.StrictMode>
    <App />
 // </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
