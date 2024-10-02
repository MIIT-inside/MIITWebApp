import React from 'react';
import '../styles/InstructionSteps.css';
import StepCard from './StepCard';
import TitleWithLine from './TitleWithLine';

const InstructionSteps = () => {
    return (
        <div className="instruction-steps">

            <div className="steps-container">
                <StepCard stepNumber="1" description="Выбор программы" />
                <StepCard stepNumber="2" description="Подача документов" />
                <StepCard stepNumber="3" description="Ожидание результатов вступительной кампании" />
                <StepCard stepNumber="4" description="Поздравляем, вы студент!" />
            </div>
        </div>
    );
};

export default InstructionSteps;
