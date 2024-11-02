import React from 'react';

export default function ProfileComponent({image, name, description}) {
    return (
        <div className="flex flex-col space-y-3">
            <img src={image} alt="изображение профиля"/>
            <span className="text-xl font-bold">{name}</span>
            <p>{description}</p>
            <button className="px-5 py-3 bg-none border border-black">Узнать больше</button>
        </div>
    )
}