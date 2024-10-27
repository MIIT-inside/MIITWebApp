import React, {StrictMode} from 'react';
import ReactDOM, {createRoot} from 'react-dom/client';
import App from './App';
import '/src/index.css'

createRoot(document.getElementById('root')).render(
    <StrictMode>
        <App/>
    </StrictMode>
)
