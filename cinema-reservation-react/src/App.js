import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Login from "./Users/Login";
import Register from "./Users/Register";
import Navbar from "./Layout/Navbar";
import Welcome from "./Pages/Welcome";
import CinemaUsers from "./Pages/CinemaUsers";

function App() {
	return (
		<div className="App">
			<Router>
				<Navbar />
				<Routes>
					<Route
						path="/"
						element={<Welcome />}
					/>
					<Route
						path="/Users/Login"
						element={<Login />}
					/>
					<Route
						path="/Users/Register"
						element={<Register />}
					/>
					<Route
						path="/Pages/CinemaUsers"
						element={<CinemaUsers />}
					/>
				</Routes>
			</Router>
		</div>
	);
}

export default App;
