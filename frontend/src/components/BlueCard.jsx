import React from 'react';

export default function BlueCard({score, description}) {
    return (
        <div className="flex flex-col bg-[#3A5BCC] p-8 space-y-4 border border-black">
            <span className="text-white text-2xl">3 балла</span>
            <span className="text-white">Учеба в РУТ(МИИТ) открыла для меня множество возможностей. Преподаватели всегда готовы помочь, а курсы актуальны и интересны.</span>
        </div>
    )
}