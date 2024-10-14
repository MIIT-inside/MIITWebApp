import React from 'react';

const Item = ({ number, description }) => (
    <div className="item">
        <span className="number">{number}</span>
        <span>{description}</span>
    </div>
);

export default Item;
