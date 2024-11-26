import React from 'react';
import { Link } from 'react-router-dom';
import test from '../assets/test.jpg';
import { FadeInSection } from './FadeInSection';

export default function ProfileComponent({ code, name, description }) {
    return (
        <FadeInSection threshold={0.5}>
            <div className="flex flex-col space-y-3">
                <img src={test} alt="изображение профиля" className="w-full h-80 object-cover" />
                <div className="flex flex-col flex-grow px-1 space-y-3">
                    <span className="text-xl font-bold line-clamp-1">{name}</span>
                    <p>{description}</p>
                </div>
                <Link to={`/profile/${code}/${name}`} className="mt-auto">
                    <button className="px-5 py-3 bg-none border border-black">Узнать больше</button>
                </Link>
            </div>
        </FadeInSection>
    );
}
