import React from 'react';
import '../styles/MainSection.css';
import Header from './Header';
import LeftImage from '../assets/RUT_logo.svg';
import RightImage from '../assets/RUT_logo.svg';

const MainSection = () => {
    return (
        <div className="main-section">
            <Header />
            <img src={LeftImage} alt="Left Background" className="background-image-left" />
            <img src={RightImage} alt="Right Background" className="background-image-right" />
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
