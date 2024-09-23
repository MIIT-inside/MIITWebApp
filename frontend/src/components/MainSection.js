import React from 'react';
import '../styles/MainSection.css';
import Header from './Header';

const MainSection = () => {
    return (
        <div className="main-section">
            <Header />
            <div className="main-content">
                <h1>Абитуриенту</h1>
                <div className="search-block">
                    <input type="text" placeholder="Поиск учебной программы" className="search-input"/>
                </div>
                <h2>Результаты ЕГЭ</h2>
                <div className="subject-block">
                    <input type="text" placeholder="Дисциплина" className="subject-input"/>
                </div>
            </div>
        </div>
    );
};

export default MainSection;