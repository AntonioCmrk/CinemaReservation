import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./MovieList.css";

const CinemaUsers = () => {
	const [cinemas, setCinemas] = useState([]);
	const navigate = useNavigate();

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

	const handleCinemaClick = (cinemaId) => {
		navigate("/Pages/Normal/MovieList", { state: { cinemaId } });
	};

	return (
		<div>
			{cinemas.map((cinema) => (
				<button
					key={cinema.id}
					onClick={() => handleCinemaClick(cinema.id)}
				>
					{cinema.name}
				</button>
			))}
		</div>
	);
};

export default CinemaUsers;
