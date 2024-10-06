import React from 'react';
import '../styles/AchievementCard.css';

const AchievementCard = ({ title, description, score }) => {
    return (
        <div className="achievement-card">
            <div className="achievement-info">
                <div className="achievement-title">Достижение</div>
                <div className="achievement-description">Наличие полученных в образовательных организациях Российской Федерации документов об образовании или об образовании и о квалификации с отличием:</div>
            </div>
            <div className="achievement-score">
                <div className="score-number">3</div>
                <div className="score-label">Балла</div>
            </div>
        </div>
    );
};

export default AchievementCard;
