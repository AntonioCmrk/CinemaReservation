import React from "react";
import { Link } from "react-router-dom";
import "../Layout/Navbar.css";

export default function Navbar({ isLoggedIn, setIsLoggedIn, userEmail }) {
	const handleLogout = () => {
		console.log(userEmail);
		setIsLoggedIn(false);
	};

	return (
		<nav className="navbar navbar-expand-lg navbar-custom">
			<div className="container">
				<Link
					className="navbar-text"
					to="/"
				>
					Cinema Reservation
				</Link>

				<div className="d-flex">
					{isLoggedIn ? (
						<>
							<Link
								className="btn btn-outline-light"
								onClick={handleLogout}
								to="/"
							>
								Log out
							</Link>
						</>
					) : (
						<Link
							className="btn btn-outline-light me-auto"
							to="/Users/Login"
						>
							Log in
						</Link>
					)}
				</div>
			</div>
		</nav>
	);
}
