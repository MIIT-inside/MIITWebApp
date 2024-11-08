import React, { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App';
import '/src/index.css';
import {BrowserRouter as Router} from "react-router-dom";


createRoot(document.getElementById('root')).render(
    <Router>
        <App />
    </Router>
);