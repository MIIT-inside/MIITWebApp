import React from 'react';

export default function BlueCard({ title, score, description }) {
    return (
        <div className="flex flex-col bg-[#3A5BCC] p-8 space-y-4 border border-black">
            <span className="text-white text-2xl">{score} балла</span>
            <span className="text-white font-semibold">{title}</span>
            <span className="text-white">{description}</span>
        </div>
    );
}
