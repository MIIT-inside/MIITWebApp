import './App.css';
import MainSection from "./components/MainSection";
import InstructionSteps from "./components/InstructionSteps";
import TitleWithLine from "./components/TitleWithLine";
import {Carousel} from "./carousel/Carousel";
import React from "react";
import AchievementCard from "./components/AchievementCard";

function App() {
  return (
      <div className="App">
        <MainSection />
          <TitleWithLine title="Как поступить" />
          <InstructionSteps/>
          <TitleWithLine title="Баллы за индивидуальные достижения" />
          <Carousel>
              <div><AchievementCard/></div>

              <div><AchievementCard/></div>
              <div><AchievementCard/></div>
          </Carousel>
      </div>
  );
}

export default App;
