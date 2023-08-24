import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import jwtDecode from "jwt-decode";

export default function Login({ setIsLoggedIn }) {
	let navigate = useNavigate();

	const [user, setUser] = useState({
		username: "",
		password: "",
	});

	const { username, password } = user;

	const onInputChange = (e) => {
		setUser({ ...user, [e.target.name]: e.target.value });
	};

	const onSubmit = async (e) => {
		e.preventDefault();

		try {
			const response = await axios.post(
				"https://localhost:7097/api/Auth/login",
				user,
			);
			const token = response.data;
			const decodedToken = jwtDecode(token);
			const role =
				decodedToken[
					"http://schemas.microsoft.com/ws/2008/06/identity/claims/role"
				];
			const cinemaId = decodedToken.CinemaId;

			axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
			if (role === "superadmin") {
				navigate("/Pages/Superadmin/CinemaSuperadmin");
			} else if (role === "admin") {
				localStorage.setItem("cinemaId", cinemaId);
				navigate("/Pages/Admin/CinemaDashboard");
			} else {
				navigate("/Pages/Normal/CinemaUsers");
			}
		} catch (error) {
			if (error.response) {
				if (error.response.status === 400) {
					const errorMessage = error.response.data;
					if (errorMessage.includes("Username")) {
						alert("Username not found.");
					} else if (errorMessage.includes("Password")) {
						alert("Incorrect password.");
					} else {
						alert(errorMessage);
					}
				} else {
					alert("Login failed. Please try again.");
				}
			} else if (error.request) {
				alert("No response from the server. Please try again later.");
			} else {
				alert("An error occurred. Please try again later.");
			}
		}
	};

	return (
		<div className="container">
			<div className="row">
				<div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
					<h2 className="text-center m-4">Log in</h2>

					<form onSubmit={(e) => onSubmit(e)}>
						<div className="mb-3">
							<label
								htmlFor="Username"
								className="form-label"
							>
								Username
							</label>
							<input
								type="text"
								className="form-control"
								placeholder="Enter your username"
								name="username"
								value={username}
								onChange={(e) => onInputChange(e)}
							/>
						</div>

						<div className="mb-3">
							<label
								htmlFor="Password"
								className="form-label"
							>
								Password
							</label>
							<input
								type="password"
								className="form-control"
								placeholder="Enter your password"
								name="password"
								value={password}
								onChange={(e) => onInputChange(e)}
							/>
						</div>
						<button
							style={{
								color: "rgb(204, 22, 22)",
								outline: "2px solid rgb(204, 22, 22)",
							}}
							onMouseOver={(e) => {
								e.target.style.backgroundColor = "rgb(204, 22, 22)";
								e.target.style.color = "white";
							}}
							onMouseOut={(e) => {
								e.target.style.backgroundColor = "transparent";
								e.target.style.color = "rgb(204, 22, 22)";
							}}
							type="submit"
							className="btn"
						>
							Submit
						</button>
						<Link
							style={{
								color: "rgb(204, 22, 22)",
								outline: "2px solid rgb(204, 22, 22)",
							}}
							onMouseOver={(e) => {
								e.target.style.backgroundColor = "rgb(204, 22, 22)";
								e.target.style.color = "white";
							}}
							onMouseOut={(e) => {
								e.target.style.backgroundColor = "transparent";
								e.target.style.color = "rgb(204, 22, 22)";
							}}
							className="btn mx-2"
							to="/users/Register"
						>
							Register instead
						</Link>
						<Link
							style={{
								backgroundColor: "rgb(204, 22, 22)",
								outline: "2px solid rgb(204, 22, 22)",
								color: "white",
							}}
							onMouseOver={(e) => {
								e.target.style.backgroundColor = "darkred";
								e.target.style.color = "white";
							}}
							onMouseOut={(e) => {
								e.target.style.backgroundColor = "rgb(204, 22, 22)";
								e.target.style.color = "white";
							}}
							className="btn"
							to="/"
						>
							Cancel
						</Link>
					</form>
				</div>
			</div>
		</div>
	);
}
