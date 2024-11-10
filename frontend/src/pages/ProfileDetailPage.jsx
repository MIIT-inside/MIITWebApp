import React, {useEffect, useState} from 'react';
import {Link, useParams} from 'react-router-dom';
import axios from 'axios';
import testImage from "../assets/test.jpg";
import {FadeInSection} from "../components/FadeInSection"

const categoryNames = {
    "MAIN": "Основной конкурс",
    "SPECIAL": "Специальная квота",
    "TARGET": "Целевой прием",
    "SEPARATE": "Особое право",
    "CONTRACT": "Договорная основа"
};

export default function ProfileDetailPage() {
    const {code, name} = useParams();
    const [profiles, setProfiles] = useState([]);
    const [error, setError] = useState(null);
    const [directions, setDirections] = useState([]);

    useEffect(() => {
        console.log(`Fetching profile data for code: ${code}`);
        axios.get(`http://localhost:8080/api/miit/profiles/direction?code=${code}`)
            .then(response => {
                setProfiles(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error('Error fetching profile data:', error);
                setError('Ошибка при загрузке данных профиля. Пожалуйста, попробуйте снова.');
            });
    }, [code]);

    useEffect(() => {
        axios.get(`http://localhost:8080/api/miit/directions/direction/${code}`)
            .then(response => {
                setDirections(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error('Error fetching directions data:', error);
                setError('Ошибка при загрузке данных направления. Пожалуйста, попробуйте снова.');
            });
    }, [code]);

    if (error) return <div>{error}</div>;

    return (
        <div>
            <div className="mt-40">
                <FadeInSection>
                    <div className="text-xl space-y-10 max-w-[1280px] mx-auto">
                        <span className="text-4xl font-bold">{decodeURIComponent(name)}</span>
                        <p className="text-2xl text-gray-600">Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                            Blanditiis eaque eligendi eum explicabo
                            id ipsum laboriosam neque omnis quibusdam velit. Aliquid at doloribus libero maiores nisi
                            qui
                            repudiandae unde voluptate.</p>
                    </div>
                    <div className="flex justify-between max-w-[1280px] mx-auto mt-20">
                        <div className="flex flex-col items-center space-y-3 border py-5 px-16 border-black">
                            <span className="text-2xl font-semibold">Код специальности</span>
                            <span className="text-xl text-gray-600">{directions.code}</span>
                        </div>
                        <div className="flex flex-col items-center space-y-3 border py-5 px-16 border-black">
                            <span className="text-2xl font-semibold">Форма обучения</span>
                            <span className="text-xl text-gray-600">{directions.form}</span>
                        </div>
                        <div className="flex flex-col items-center space-y-3 border py-5 px-16 border-black">
                            <span className="text-2xl font-semibold">Уровень образования</span>
                            <span className="text-xl text-gray-600">{directions.level}</span>
                        </div>
                    </div>
                </FadeInSection>

                <div className="py-16 mt-10 bg-fixed bg-cover relative" style={{backgroundImage: `url(${testImage})`}}>
                    <div className="absolute inset-0 bg-black opacity-60"></div>
                    <FadeInSection>
                        <span className="flex text-3xl font-bold max-w-[1280px] mx-auto text-white relative">Профили подготовки</span>
                        <div
                            className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-8 max-w-[1280px] mx-auto relative">
                            {profiles.map((profile, index) => (

                                <div className="p-4 border border-white rounded shadow-sm">
                                    <Link key={index} to={`/profile/${encodeURIComponent(profile.name)}`}>
                                        <h2 className="text-2xl font-semibold line-clamp-1 text-white">{profile.name}</h2>
                                    </Link>
                                    <p className="mt-2 text-white">{profile.form} - {profile.level}</p>
                                    <p className="mt-2 text-white">{profile.institute} ({profile.abbreviation})</p>
                                </div>

                            ))}
                        </div>
                    </FadeInSection>
                </div>
                <div>
                    <div className="flex flex-col mt-10 max-w-[1280px] mx-auto pb-16">
                        <FadeInSection>
                            <span className="text-3xl font-bold">Поступление</span>
                            <div className="text-2xl text-gray-600 my-10">Для успешного поступления в наш вуз важно ориентироваться на проходные и средние баллы прошлых лет, которые помогут вам оценить шансы на зачисление и спланировать подготовку. В этом разделе вы найдете актуальные данные о проходных и средних баллах по каждому направлению. Эти показатели дают общее представление о конкурсной ситуации и помогут вам определиться с выбором направления.</div>
                        </FadeInSection>
                        <FadeInSection>
                            <div>
                                {directions.pass_points && directions.pass_points.map((point, index) => (
                                    <div key={index}
                                         className="flex justify-between items-center border-b border-gray-500 p-3">
                                        <span
                                            className="font-semibold w-1/2 text-xl">{categoryNames[point.category]}</span>
                                        <span className="text-xl">Мин: {point.min}</span>
                                        <span className="text-xl">Сред: {point.avg}</span>
                                    </div>
                                ))}
                            </div>
                        </FadeInSection>
                    </div>
                </div>

            </div>
        </div>
    );
}
