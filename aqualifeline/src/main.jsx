import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import SystemView from './SystemView.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
    <SystemView />
  </StrictMode>,
)
