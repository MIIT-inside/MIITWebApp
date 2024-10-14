import React from 'react';
import Item from "./trainingProgrammsComponents/Item"
import '../styles/TrainingPrograms.css';

const TrainingPrograms = () => {
    return (
        <div className="containerTrainingPrograms">
            <h1>Программы подготовки</h1>
            <hr />
            <div className="item-grid">
                <Item text="очень перспективная программа подготовки" htext="Реально интересное и очень емкое описание программы подготовки" />
                <Item text="очень перспективная программа подготовки" htext="Реально интересное и очень емкое описание программы подготовки" />
                <Item text="очень перспективная программа подготовки" htext="Реально интересное и очень емкое описание программы подготовки" />
                <Item text="очень перспективная программа подготовки" htext="Реально интересное и очень емкое описание программы подготовки" />
            </div>
        </div>
    );
}

export default TrainingPrograms;