import React from 'react';
import './App.css';
import MainSection from "./components/MainSection";
import InstructionSteps from "./components/InstructionSteps";
import TitleWithLine from "./components/TitleWithLine";
import {Carousel} from "./carousel/Carousel";
import React, {useEffect, useState} from "react";
import AchievementCard from "./components/AchievementCard";

export default function App() {
    const [achievements, setAchievements] = useState([]);
    useEffect(() => {
        fetch('https://jsonplaceholder.typicode.com/posts')
            .then(response => response.json())
            .then(data => {
                const mappedData = data.slice(0, 5).map(item => ({
                    title: item.title, description: item.body, score: Math.floor(Math.random() * 5) + 1,
                }));
                setAchievements(mappedData);
            })
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    return (<div className="App">
        <MainSection/>
        <TitleWithLine title="Как поступить"/>
        <InstructionSteps/>
        <TitleWithLine title="Баллы за индивидуальные достижения"/>
        <Carousel>
            {achievements.map((achievement, index) => (<div key={index} className="center-achievement-card">
                <AchievementCard
                    title={achievement.title}
                    description={achievement.description}
                    score={achievement.score}
                />
            </div>))}
        </Carousel>
    </div>);
}
