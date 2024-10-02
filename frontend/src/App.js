import './App.css';
import MainSection from "./components/MainSection";
import InstructionSteps from "./components/InstructionSteps";
import TitleWithLine from "./components/TitleWithLine";
import {Carousel} from "./carousel/Carousel";
import React from "react";

function App() {
  return (
      <div className="App">
        <MainSection />
          <TitleWithLine title="Как поступить" />
          <InstructionSteps/>
          <TitleWithLine title="Баллы за индивидуальные достижения" />
          <Carousel>
              <div className="item item-1">Item 1</div>
              <div className="item item-2">Item 2</div>
              <div className="item item-3">Item 3</div>
          </Carousel>
      </div>
  );
}

export default App;
