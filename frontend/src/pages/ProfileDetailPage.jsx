import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

export default function ProfileDetailPage() {
    const { code, name } = useParams();
    const [profiles, setProfiles] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        console.log(`Fetching profile data for code: ${code}`);
        axios.get(`http://localhost:8080/api/miit/profiles/direction?code=${code}`)
            .then(response => {
                setProfiles(response.data);
                console.log(response.data);
            })
            .catch(error => {
                console.error('Error fetching profile data:', error);
                if (error.response) {
                    console.error('Response data:', error.response.data);
                    console.error('Response status:', error.response.status);
                    console.error('Response headers:', error.response.headers);
                }
                setError('Ошибка при загрузке данных профиля. Пожалуйста, попробуйте снова.');
            });
    }, [code]);

    if (error) return <div>{error}</div>;

    return (
        <div className="max-w-[1280px] mx-auto mt-40">
            <h1 className="text-3xl font-bold">{decodeURIComponent(name)}</h1>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mt-8">
                {profiles.map((profile, index) => (
                    <div key={index} className="p-4 border rounded shadow-sm">
                        <h2 className="text-2xl font-semibold">{profile.name}</h2>
                        <p className="text-sm mt-2">{profile.form} - {profile.level}</p>
                        <p className="text-sm mt-2">{profile.institute} ({profile.abbreviation})</p>
                        <p className="text-gray-700 mt-2">{profile.description || 'Описание отсутствует'}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}
