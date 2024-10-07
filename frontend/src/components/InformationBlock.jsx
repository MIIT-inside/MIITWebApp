import React from 'react';
import '../styles/InformationBlock.css';


const InformationBlock = () => {
    return (<div className="container">
            <div className="TopRow">
                <div className="item">
                    <span className="number">60</span>
                    <span>Направлений подготовки</span>
                </div>
                <div className="item">
                    <span className="number">00</span>
                    <span>Бюджетных мест</span>
                </div>
                <div className="item">
                    <span className="number">300</span>
                    <span>Проходной балл в 2023 году</span>
                </div>
            </div>
            <div className="BottomRow">
                <div className="small-item">форма обучения<br/>нестабильная</div>
                <div className="small-item">срок обучения<br/>вся жизнь</div>
                <div className="small-item">стоимость обучения<br/>миллион денег</div>
                <div className="small-item">язык обучения<br/>узбекский</div>
                <div className="small-item">общежития<br/>в подземке живём</div>
                <div className="small-item">скидка<br/>какая?</div>
            </div>
        </div>
    );
};


export default InformationBlock;