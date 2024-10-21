import React from 'react';

const SmallItem = ({ text }) => (
    <div className="small-item">
        <span dangerouslySetInnerHTML={{ __html: text }}></span>
    </div>
);

export default SmallItem;
