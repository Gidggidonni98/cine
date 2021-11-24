const { Router } = require('express');
const express = require('express');
const router = express.Router();

const pool = require('../database.js');
const { route } = require('./category.js');

// OBTENER TODOS LOS REGISTROS  //

router.get('/', async(req, res) =>{
    let listMovies = await pool.query('SELECT * FROM movie');
    res.json({
        status: 200,
        message:"Se ha listado correctamente",
        listMovies : listMovies
    });
});

// OBTENER REGISTRO POR ID  //

router.get('/:id', async (req, res)=> {
    const { id } = req.params;
    let movie = await pool.query('SELECT * FROM movie WHERE id = ?', [id]);
    res.json({
        status: 200,
        message: "Se ha encontrado el registro",
        movie : movie
    });
});

// CREAR UN REGISTRO  //

router.post('/create', async (req,res) => {
    const { title,description, sinopsis, raiting, category } = req.body;
    var date = new Date().toISOString();
    const movie = {
        title,description, sinopsis, raiting, dateCreated: date, status: 1, category
    };
    await pool.query('INSERT INTO movie SET ?', [movie]);
    res.json({
        status: 200,
        message: "Se ha registrado correctamente",
        movie : movie
    });
});

// ACTUALIZAR UN REGISTRO  //

router.post('/update/:id', async(req,res) => {
    const { id } = req.params;
    const { title, description, sinopsis, raiting, category } = req.body;
    var date = new Date().toISOString();
    const movie = {
        title, description, sinopsis, raiting, dateUpdate: date, category 
    };
    await pool.query('UPDATE movie SET ? WHERE id = ?', [movie,id]);
    res.json({
        status: 200,
        message: "Se ha actualizado correctamente",
        movie: movie
    });
});

// DAR DE BAJA UN REGISTRO  //

router.post('/delete/:id', async(req,res) => {
    const { id } = req.params;
    pool.query('UPDATE movie SET status = 0 WHERE id = ?', [id]);
    res.json({
        status: 200,
        message: "Se ha inactivado correctamente"
    });
});

module.exports = router;
