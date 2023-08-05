import React from "react";
import { Link } from "react-router-dom";
import "../Pages/Welcome.css";

export default function Welcome() {
	return (
		<div className="welcome-container">
			<h1 className="title">Cinema Reservation</h1>
			<p className="message">Please log in or register to continue.</p>
			<div className="button-container">
				<Link
					to="/Users/Login"
					className="btn btn-login"
				>
					Log in
				</Link>
				<Link
					to="/Pages/CinemaUsers"
					className="btn btn-login"
				>
					CinemaUsers
				</Link>
				<Link
					to="/Users/Register"
					className="btn btn-secondary"
				>
					Register
				</Link>
			</div>
		</div>
	);
}
