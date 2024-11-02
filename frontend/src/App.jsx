import './App.css';
import React, {useEffect, useState} from "react";
import {Routes, Route} from 'react-router-dom';
import MainPage from "./pages/MainPage";
import Header from "./components/Header";
import FAQPage from "./pages/FAQPage";
import FooterBlock from "./components/FooterBlock";
import SpecializationPage from "./pages/SpecializationPage";


export default function App() {
    return (
        <div className="flex flex-col min-h-screen">
            <Header/>
            <main className="flex-grow">
                <Routes>
                    <Route path="/main" element={<MainPage/>}/>
                    <Route path="/specialties" element={<SpecializationPage/>}/>
                    <Route path="/faq" element={<FAQPage/>}/>
                </Routes>
            </main>
            <FooterBlock/>
        </div>);
}
