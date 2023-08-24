import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function Register({ setIsLoggedIn }) {
	let navigate = useNavigate();

	const [user, setUser] = useState({
		username: "",
		email: "",
		password: "",
	});

	const { username, email, password } = user;

	const onInputChange = (e) => {
		setUser({ ...user, [e.target.name]: e.target.value });
	};

	const loginUser = async (credentials) => {
		try {
			const response = await axios.post(
				"https://localhost:7097/api/Auth/login",
				credentials,
			);
			const loginResponse = response;

			if (loginResponse.status) {
				const token = loginResponse.data;
				localStorage.setItem("userToken", token);

				axios.defaults.headers.common["Authorization"] = `Bearer ${token}`;
				navigate("/Pages/CinemaUsers");
			} else {
				alert("Login failed after registration. Please log in manually.");
			}
		} catch (error) {
			alert("Login failed after registration. Please log in manually.");
		}
	};

	const onSubmit = async (e) => {
		e.preventDefault();
		if (
			username.trim() === "" ||
			email.trim() === "" ||
			password.trim() === ""
		) {
			alert("Please fill in all fields.");
			return;
		}

		if (!validateEmail(email)) {
			alert("Please enter a valid email address.");
			return;
		}

		try {
			const response = await axios.post(
				"https://localhost:7097/api/Auth/register",
				user,
			);
			if (response.status === 200) {
				alert("Registration successful!");
				loginUser({ username, password });
			}
		} catch (error) {
			if (error.response) {
				if (error.response.status === 400) {
					const errorMessage = error.response.data;
					if (errorMessage.includes("Email")) {
						alert("Email already in use.");
					} else {
						alert(errorMessage);
					}
				} else {
					alert("Registration failed. Please try again.");
				}
			} else if (error.request) {
				alert("No response from the server. Please try again later.");
			} else {
				alert("An error occurred. Please try again later.");
			}
		}
	};

	const validateEmail = (email) => {
		const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		return emailPattern.test(email);
	};

	return (
		<div className="container">
			<div className="row">
				<div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
					<h2 className="text-center m-4">Register</h2>

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
								htmlFor="Email"
								className="form-label"
							>
								E-mail
							</label>
							<input
								type="text"
								className="form-control"
								placeholder="Enter your e-mail address"
								name="email"
								value={email}
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
							Register
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
							to="/users/Login"
						>
							Log in instead
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
							className="btn btn-danger"
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
