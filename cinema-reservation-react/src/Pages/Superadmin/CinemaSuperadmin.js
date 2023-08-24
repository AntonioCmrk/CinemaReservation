import React, { useState, useEffect } from "react";
import axios from "axios";

const CinemaUsers = () => {
	const [cinemas, setCinemas] = useState([]);

	useEffect(() => {
		const fetchCinemas = async () => {
			try {
				const response = await axios.get("https://localhost:7097/api/Cinema");
				setCinemas(response.data);
			} catch (error) {
				console.error("Error fetching data:", error);
			}
		};
		fetchCinemas();
	}, []);

	return (
		<div>
			{cinemas.map((cinema) => (
				<button key={cinema.id}>{cinema.name}</button>
			))}
		</div>
	);
};

export default CinemaUsers;
