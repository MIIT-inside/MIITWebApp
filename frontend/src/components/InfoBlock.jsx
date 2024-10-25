import React from 'react';

export default function InfoBlock({title, description}) {
    return (
        <div className="flex flex-col space-y-5">
            <span className="text-3xl font-bold">{title}</span>
            <span className="text-[#1E1E1E]">{description}</span>
        </div>
    )
}
