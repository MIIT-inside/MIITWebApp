import React from 'react';
import ProfileComponent from "../components/ProfileComponent";

export default function SpecializationPage() {
    return (
        <div className="mt-40 max-w-[1280px] mx-auto">
            <div className="flex items-center justify-between">
                <span className="text-3xl font-bold">Профили подготовки</span>
                <span className="w-1/2">На этой странице представлены образовательные программы университета, каждая из которых открывает возможности для профессионального роста. Изучите доступные направления, чтобы понять, какие знания и навыки можно получить, и сделать уверенный шаг к успешной карьере.</span>
            </div>
            <div className="flex mt-20 grid-cols-3 space-x-5">
                <ProfileComponent name="Технологии разработки программного обеспечения" description="empty"/>
                <ProfileComponent name="Технологии разработки программного обеспечения" description="empty"/>
                <ProfileComponent name="Технологии разработки программного обеспечения" description="empty"/>
                <ProfileComponent name="Технологии разработки программного обеспечения" description="empty"/>
            </div>
        </div>
    )
}