import React from 'react';

export default function BlueCard({countPoints, description }) {
    return (
        <div className="flex flex-col bg-[#3A5BCC] p-8 space-y-4 border border-black min-h-[270px]">
            <span className="text-white text-2xl">Количество баллов: {countPoints}</span>
            <span className="text-white">{description}</span>
        </div>
    );
}
