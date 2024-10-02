import React from 'react';
import '../styles/TitleWithLine.css';

const TitleWithLine = ({ title }) => {
    return (
        <div className="title-with-line">
            <h2>{title}</h2>
            <div className="line"></div>
        </div>
    );
};

export default TitleWithLine;
