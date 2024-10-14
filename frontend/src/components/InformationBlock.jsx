import React from 'react';

import '../styles/InformationBlock.css';
import Item from './informationBlockComponents/Item';
import SmallItem from './informationBlockComponents/SmallItem';

const InformationBlock = () => {
    return (
        <div className="container">
            <div className="TopRow">
                <Item number="60" description="Направлений подготовки" />
                <hr />
                <Item number="00" description="Бюджетных мест" />
                <hr />
                <Item number="300" description="Проходной балл в 2023 году" />
            </div>
            <div className="BottomRow">
                <SmallItem text="форма обучения<br/>нестабильная" />
                <SmallItem text="срок обучения<br/>вся жизнь" />
                <SmallItem text="стоимость обучения<br/>миллион денег" />
                <SmallItem text="язык обучения<br/>узбекский" />
                <SmallItem text="общежития<br/>в подземке живём" />
                <SmallItem text="скидка<br/>какая?" />
            </div>
        </div>
    );
};

export default InformationBlock;
