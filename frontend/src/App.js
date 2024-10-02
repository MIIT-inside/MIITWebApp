import './App.css';
import MainSection from "./components/MainSection";
import InstructionSteps from "./components/InstructionSteps";
import TitleWithLine from "./components/TitleWithLine";
import React from "react";

function App() {
  return (
      <div className="App">
        <MainSection />
          <TitleWithLine title="Как поступить" />
          <InstructionSteps/>
          <TitleWithLine title="Баллы за индивидуальные достижения" />
      </div>
  );
}

export default App;
