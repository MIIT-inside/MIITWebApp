import React from 'react';
import './TitleWithLine.css';

const TitleWithLine = ({ title }) => {
    return (
        <div className="title-with-line">
            <h2>{title}</h2>
            <hr className="title-line" />
        </div>
    );
};

export default TitleWithLine;
