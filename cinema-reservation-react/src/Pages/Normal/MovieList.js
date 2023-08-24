import React, { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";

const MovieList = () => {
	const [movies, setMovies] = useState([]);
	const navigate = useNavigate();
	const location = useLocation();
	const cinemaId = location.state.cinemaId;

	useEffect(() => {
		fetch(`https://localhost:7097/api/Projection`)
			.then((response) => response.json())
			.then((data) => {
				const filteredMovies = data.filter(
					(movie) => movie.cinemaId === cinemaId,
				);
				setMovies(filteredMovies);
			})
			.catch((error) => console.log(error));
	}, [cinemaId]);

	return (
		<div className="container">
			<h1>Movies</h1>
			<ul>
				{movies.map((movie) => (
					<li key={movie.id}>
						<img
							src={movie.imagePath}
							alt={movie.name}
						/>
						<h3>{movie.name}</h3>
						<p>{movie.day}</p>
						<p>{movie.time}</p>
						<p>Price: €{movie.price}</p>
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
							className="btn mx-2"
							onClick={() => navigate(`/pages/ReserveMovie`)}
						>
							Reserve
						</button>
					</li>
				))}
			</ul>
		</div>
	);
};

export default MovieList;
