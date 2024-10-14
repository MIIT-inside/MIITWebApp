import React from 'react';

const Item = ({ text, htext }) => (
    <div className="item">
        <span className="text">{text}</span>
        <br/>
        <span>{htext}</span>
    </div>
);

export default Item;