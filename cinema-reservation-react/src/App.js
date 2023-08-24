import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import Login from "./Users/Login";
import Register from "./Users/Register";
import Navbar from "./Layout/Navbar";
import Welcome from "./Pages/Welcome";
import CinemaUsers from "./Pages/Normal/CinemaUsers";
import CinemaSuperadmin from "./Pages/Superadmin/CinemaSuperadmin";
import CinemaDashboard from "./Pages/Admin/CinemaDashboard";
import MovieList from "./Pages/Normal/MovieList";

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
						path="/Pages/Normal/CinemaUsers"
						element={<CinemaUsers />}
					/>
					<Route
						path="/Pages/Superadmin/CinemaSuperadmin"
						element={<CinemaSuperadmin />}
					/>
					<Route
						path="/Pages/Admin/CinemaDashboard"
						element={<CinemaDashboard />}
					/>
					<Route
						path="/Pages/Normal/MovieList"
						element={<MovieList />}
					/>
				</Routes>
			</Router>
		</div>
	);
}

export default App;
