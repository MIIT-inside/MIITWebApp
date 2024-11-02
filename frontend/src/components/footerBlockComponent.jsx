import React from 'react';

export const Item = ({ textItem }) => (
    <div className="item">
        <a className="text-gray-600 hover:text-gray-800">{textItem}</a>
    </div>
);