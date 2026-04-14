import { Routes, Route, useNavigate } from "react-router-dom";
import "./App.css";
import SystemView from "./SystemView";
import HomeButton from "./HomeButton";
import Footer from "./Footer";

function LoggedIn() {
  return <><SystemView /><Footer /></>;
}

function App() {
  const navigate = useNavigate();

  return (
    <Routes>
      <Route
        path="/"
        element={
          <>
            <header>
              <HomeButton />
              <h1 id="headtitle">AquaLifeline</h1>
              <input placeholder="E-MAIL" />
              <input placeholder="PASSWORD" />
              <button className="submit" onClick={() => navigate("/loggedIn")}>
                LOGIN
              </button>
            </header>
            <Footer />
          </>
        }
      />
      <Route
        path="/loggedIn"
        element={
          <>
            <HomeButton />
            <LoggedIn />
          </>
        }
      />
    </Routes>
  );
}

export default App;
