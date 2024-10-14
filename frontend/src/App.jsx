import React from 'react';
import './App.css';
import MainSection from "./components/MainSection";
import InstructionSteps from "./components/InstructionSteps";
import InformationBlock from "./components/InformationBlock";
import TrainingPrograms from "./components/TrainingPrograms";

function App() {
  return (
      <div className="App">
        <MainSection />
          <InformationBlock/>
          <TrainingPrograms/>
          <InstructionSteps/>
      </div>
  );
}

export default App;
