import React, { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App';
import '/src/index.css';
import FooterBlock from './components/footerBlock';
import HelpBlock from './components/HelpBlock';


createRoot(document.getElementById('root')).render(
    <StrictMode>
        <App />
        <HelpBlock/>
        <FooterBlock />
    </StrictMode>
);