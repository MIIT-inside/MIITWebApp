import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

export default function ProfileDetailPage() {
    const { name } = useParams();
    const [profile, setProfile] = useState(null);
    const [plans, setPlans] = useState([]);
    const [directions, setDirections] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchProfileData = async () => {
            try {
                const profileResponse = await axios.get(`http://localhost:8080/api/miit/profiles/profile?name=${encodeURIComponent(name)}`);
                const profileData = profileResponse.data;
                setProfile(profileData);
                if (profileData.id) {
                    fetchDirectionsData(profileData.id);
                    fetchPlansData(profileData.id);
                }
            } catch (error) {
                console.error('Ошибка при загрузке данных профиля:', error);
                setError('Ошибка при загрузке данных профиля. Пожалуйста, попробуйте снова.');
            }
        };

        const fetchDirectionsData = async (profileId) => {
            try {
                const directionsResponse = await axios.get(`http://localhost:8080/api/miit/directions/${profileId}/directions`);
                setDirections(directionsResponse.data);
            } catch (error) {
                console.error('Ошибка при загрузке данных направлений:', error);
            }
        };

        const fetchPlansData = async (profileId) => {
            try {
                const plansResponse = await axios.get(`http://localhost:8080/api/miit/profiles/${profileId}/plans`);
                setPlans(plansResponse.data.education_plan);
            } catch (error) {
                console.error('Ошибка при загрузке данных планов обучения:', error);
            }
        };

        fetchProfileData();
    }, [name]);

    if (error) return <div>{error}</div>;
    if (!profile) return <div>Loading...</div>;

    return (
        <div className="bg-[url('./src/assets/image_2024-10-22_12-22-33.png')] h-screen bg-fixed bg-cover relative">
            <div className="absolute inset-0 bg-black opacity-60"></div>
            <div className="relative flex flex-col m-auto ml-44 w-1/2 space-y-6 items-start justify-center size-full border-0 rounded-xl pt-16">
                <span className="text-4xl font-bold text-white">{profile.name}</span>
                <p className="text-white text-xl font-medium">
                    {profile.description || "Описание недоступно."}
                </p>
                <p className="text-white text-lg">Институт: {profile.institute}</p>
                <p className="text-white text-lg">Форма обучения: {profile.form}</p>
                <p className="text-white text-lg">Уровень: {profile.level}</p>
            </div>

            <div className="relative mt-8 p-6 bg-white rounded-lg shadow-lg max-w-4xl mx-auto">
                <h2 className="text-2xl font-bold mb-4">Проходные баллы и направления</h2>
                {directions.length > 0 ? (
                    directions.map((direction, index) => (
                        <div key={index} className="mb-6">
                            <h3 className="text-xl font-semibold">{direction.name} ({direction.code})</h3>
                            <p>Уровень: {direction.level}, Форма: {direction.form}</p>
                            <h4 className="mt-2 font-semibold">Проходные баллы:</h4>
                            <ul className="list-disc ml-6">
                                {direction.pass_points.map((point) => (
                                    <li key={point.id} className="ml-2">
                                        Категория: {point.category}, Мин: {point.min}, Средн.: {point.avg}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ))
                ) : (
                    <p>Информация о направлениях отсутствует.</p>
                )}
            </div>

            <div className="relative mt-8 p-6 bg-white rounded-lg shadow-lg max-w-4xl mx-auto">
                <h2 className="text-2xl font-bold mb-4">Образовательные планы</h2>
                {plans.length > 0 ? (
                    plans.map((plan, index) => (
                        <div key={index} className="mb-6">
                            <h3 className="text-xl font-semibold">{plan.name}</h3>
                            {plan.disciplines.map((discipline, idx) => (
                                <div key={idx} className="ml-4 mt-2">
                                    <h4 className="text-lg font-medium">{discipline.name}</h4>
                                    <p>Аттестация: {discipline.attestation}</p>
                                    <h5 className="mt-1 font-medium">Занятия:</h5>
                                    <ul className="list-disc ml-6">
                                        {discipline.lessons.map((lesson, lessonIdx) => (
                                            <li key={lessonIdx}>
                                                Лекции: {lesson.lecture}, Практика: {lesson.practice}, Лабораторные: {lesson.lab}
                                            </li>
                                        ))}
                                    </ul>
                                </div>
                            ))}
                        </div>
                    ))
                ) : (
                    <p>Информация о планах обучения отсутствует.</p>
                )}
            </div>
        </div>
    );
}
