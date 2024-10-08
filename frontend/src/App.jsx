import './App.css';
import MainSection from "./components/MainSection";
import InstructionSteps from "./components/InstructionSteps";
import TitleWithLine from "./components/TitleWithLine";
import {Carousel} from "./carousel/Carousel";
import React from "react";
import AchievementCard from "./components/AchievementCard";

export default function App() {
    return (<div className="App">
            <MainSection/>
            <TitleWithLine title="Как поступить"/>
            <InstructionSteps/>
            <TitleWithLine title="Баллы за индивидуальные достижения"/>
            <Carousel>
                <div><AchievementCard
                    title="Достижение"
                    description="Наличие полученных в образовательных организациях Российской Федерации документов об образовании или об образовании и о квалификации с отличием:"
                    score="3"/>
                </div>
                <div><AchievementCard
                    title="Достижение"
                    description="Наличие полученных в образовательных организациях Российской Федерации документов об образовании или об образовании и о квалификации с отличием:"
                    score="3"/>
                </div>
                <div><AchievementCard
                    title="Достижение"
                    description="Наличие полученных в образовательных организациях Российской Федерации документов об образовании или об образовании и о квалификации с отличием:"
                    score="3"/>
                </div>
            </Carousel>
        </div>);
}
