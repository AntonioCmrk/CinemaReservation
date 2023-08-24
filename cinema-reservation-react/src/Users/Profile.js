import React, { useEffect, useState } from "react";
import jwtDecode from "jwt-decode";

export default function Profile({ token }) {
	const [user, setUser] = useState(null);

	useEffect(() => {
		if (token) {
			const decodedToken = jwtDecode(token);
			const username = decodedToken.sub;
			const cinemaId = parseInt(
				decodedToken["http://schemas.example.com/identity/claims/cinemaid"],
			);
			setUser({ username, cinemaId });
		}
	}, [token]);

	if (!user) {
		return <div>Loading...</div>;
	}

	return (
		<div>
			<h2>Welcome, {user.username}!</h2>
			<p>Your Cinema ID: {user.cinemaId}</p>
		</div>
	);
}
