import React, { useEffect, useState } from 'react';
import ProfileComponent from '../components/ProfileComponent';
import axios from 'axios';

export default function SpecializationPage() {
    const [profiles, setProfiles] = useState([]);

    useEffect(() => {
        axios.get('http://localhost:8080/api/miit/directions/')
            .then(response => {
                const mappedData = response.data.map(item => ({
                    code: item.code,
                    name: item.name,
                    description: item.description,
                }));
                setProfiles(mappedData);
            })
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    return (
        <div className="mt-40 max-w-[1280px] mx-auto">
            <div className="flex justify-between">
                <span className="text-3xl font-bold">Профили подготовки</span>
                <span className="w-1/2">
                    На этой странице представлены образовательные программы университета, каждая из которых открывает возможности для профессионального роста.
                </span>
            </div>
            <div className="grid my-20 grid-cols-3 gap-6">
                {profiles.map((profile) => (
                    <ProfileComponent
                        key={profile.code}
                        code={profile.code}
                        name={profile.name}
                        description={profile.description}
                    />
                ))}
            </div>
        </div>
    );
}
