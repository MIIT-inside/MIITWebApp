import React, {useEffect, useState} from 'react';
import {useParams} from 'react-router-dom';
import axios from 'axios';
import testImage from "../assets/test.jpg";
import {FadeInSection} from "../components/FadeInSection"

export default function ProfilePage() {
    const {profileName} = useParams();
    const [profile, setProfile] = useState(null);
    const [educationPlan, setEducationPlan] = useState([]);
    const [selectedSemester, setSelectedSemester] = useState('');
    const [selectedAttestation, setSelectedAttestation] = useState('');

    const uniqueSemesters = educationPlan.map(plan => plan.name);
    const uniqueAttestations = Array.from(new Set(educationPlan.flatMap(plan =>
        plan.disciplines.map(discipline => discipline.attestation)
    )));

    useEffect(() => {
        axios.get(`http://localhost:8080/api/miit/profiles/profile?name=${encodeURIComponent(profileName)}`)
            .then(response => setProfile(response.data))
            .catch(error => console.error('Error fetching profile data:', error));

        axios.get(`http://localhost:8080/api/miit/plans/?name=${encodeURIComponent(profileName)}`)
            .then(response => setEducationPlan(response.data.education_plan))
            .catch(error => console.error('Error fetching education plan:', error));
    }, [profileName]);

    const filteredDisciplines = educationPlan.flatMap(plan =>
        plan.disciplines.filter(discipline =>
            (selectedSemester ? plan.name === selectedSemester : true) &&
            (selectedAttestation ? discipline.attestation === selectedAttestation : true)
        )
    );

    return (
        <div className="mt-10">
            {profile ? (
                <>
                    <div className="py-16 bg-fixed bg-cover relative"
                         style={{backgroundImage: `url(${testImage})`}}>
                        <div className="absolute inset-0 bg-black opacity-60"></div>

                        <div className="relative max-w-[1280px] mx-auto mt-32">
                            <FadeInSection>
                                <h1 className="text-4xl text-center font-bold mb-6 text-white">{profile.name}</h1>
                            </FadeInSection>
                            <FadeInSection>
                                <div className="flex justify-between mt-40">
                                    <div
                                        className="flex flex-col text-white text-center text-xl items-center space-y-4 border border-white py-4 px-8">
                                        <span className="text-2xl">Форма обучения</span>
                                        <span>{profile.form}</span>
                                    </div>
                                    <div
                                        className="flex flex-col text-white text-center text-xl items-center space-y-4 border border-white py-4 px-8">
                                        <span className="text-2xl">Уровень образования</span>
                                        <span>{profile.level}</span>
                                    </div>
                                    <div
                                        className="flex flex-col text-white text-center text-xl items-center space-y-4 border border-white py-4 px-8">
                                        <span className="text-2xl">Институт</span>
                                        <span>{profile.institute}</span>
                                    </div>
                                </div>
                            </FadeInSection>
                        </div>
                    </div>
                    <div className="max-w-[1280px] mx-auto mt-20">
                        <FadeInSection>
                            <span className="text-2xl">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet aperiam aspernatur dicta distinctio, doloribus et eum ipsa iste iusto magni praesentium provident quod sint tenetur voluptas! Magnam magni reprehenderit temporibus!</span>
                        </FadeInSection>
                        <FadeInSection>
                            <h2 className="text-3xl font-bold mt-8 mb-4">Учебный план</h2>
                            <div className="flex space-x-16 mt-8">
                                <div className="mx-4">
                                    <label htmlFor="semester-select" className="block text-xl mb-6">Выберите
                                        семестр:</label>
                                    <select id="semester-select" value={selectedSemester}
                                            onChange={(e) => setSelectedSemester(e.target.value)}
                                            className="border border-black py-4 px-8">
                                        <option value="">Все семестры</option>
                                        {uniqueSemesters.map((semester, index) => (
                                            <option key={index} value={semester}>{semester}</option>
                                        ))}
                                    </select>
                                </div>
                                <div className="mb-4">
                                    <label htmlFor="attestation-select" className="block text-xl mb-6">Выберите
                                        аттестацию:</label>
                                    <select id="attestation-select" value={selectedAttestation}
                                            onChange={(e) => setSelectedAttestation(e.target.value)}
                                            className="border border-black py-4 px-8">
                                        <option value="">Все аттестации</option>
                                        {uniqueAttestations.map((attestation, index) => (
                                            <option key={index} value={attestation}>{attestation}</option>
                                        ))}
                                    </select>
                                </div>
                            </div>
                        </FadeInSection>
                    </div>
                    <div className="grid grid-cols-3 max-w-[1280px] mx-auto gap-6 mt-20">
                        {filteredDisciplines.length > 0 ? (
                            filteredDisciplines.map((discipline, idx) => (
                                <FadeInSection threshold={0.3} key={idx}>
                                    <div
                                        className="mb-6 border border-black py-4 px-8 space-y-4 h-64 flex flex-col justify-center">
                                        <h3 className="text-2xl font-semibold">{discipline.name}</h3>
                                        <p className="text-xl text-gray-700">Аттестация: {discipline.attestation}</p>
                                        <p className="text-xl text-gray-700">Семестр: {selectedSemester}</p>
                                    </div>
                                </FadeInSection>
                            ))
                        ) : (
                            <p>Нет доступных дисциплин для выбранных параметров.</p>
                        )}
                    </div>

                </>
            ) : (
                <p>Загрузка данных...</p>
            )}
        </div>
    );
}
