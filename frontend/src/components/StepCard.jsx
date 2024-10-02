import React from 'react';
import '../styles/StepCard.css';

const StepCard = ({ stepNumber, description }) => {
    return (
        <div className="step-card">
            <div className="step-number">
                {stepNumber}
            </div>
            <div className="step-description">
                {description}
            </div>
        </div>
    );
};

export default StepCard;
