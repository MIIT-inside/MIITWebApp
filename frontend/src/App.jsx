import React from 'react';
import './App.css';
import MainSection from "./components/MainSection";
import InstructionSteps from "./components/InstructionSteps";
import InformationBlock from "./components/InformationBlock";

function App() {
  return (
      <div className="App">
        <MainSection />
          <InstructionSteps/>
          <InformationBlock />

      </div>
  );
}

export default App;
