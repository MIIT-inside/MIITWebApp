import React, {useEffect, useState} from 'react';
import test from "../assets/test.jpg"

export default function ProfileComponent({image, name, description}) {

    return (
        <div className="flex flex-col space-y-3">
            <img src={test} alt="изображение профиля" className="w-full h-80 object-cover"/>
            <span className="text-xl font-bold h-16 overflow-hidden text-ellipsis">{name}</span>
            <p>{description}</p>
            <button className="px-5 py-3 bg-none border border-black mt-auto">Узнать больше</button>
        </div>
    )
}