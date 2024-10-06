import React from 'react';
import '../styles/AchievementCard.css';

const AchievementCard = ({ title, description, score }) => {
    return (
        <div className="achievement-card">
            <div className="achievement-info">
                <div className="achievement-title">{title}</div>
                <div className="achievement-description">{description}</div>
            </div>
            <div className="achievement-score">
                <div className="score-number">{score}</div>
                <div className="score-label">Балла</div>
            </div>
        </div>
    );
};

export default AchievementCard;
