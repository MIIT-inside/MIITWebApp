import React, { useState } from 'react';

export const Accordion = ({ textTheme, description }) => {
    const [isOpen, setIsOpen] = useState(false);

    const handleToggle = () => {
        setIsOpen(!isOpen);
    };

    return (
        <div className="Accordion border-b border-gray-300">
            <button
                onClick={handleToggle}
                className="flex justify-between items-center w-full text-lg font-semibold cursor-pointer py-2">
                <span>{textTheme}?</span>
                <span className={`transform transition-transform duration-200 ${isOpen ? 'rotate-180' : '0'}`}>
                    (◕‿◕)
                </span>
            </button>
            {isOpen && (
                <p className="text-base text-gray-700 py-2">
                    {description}
                </p>
            )}
        </div>
    );
};