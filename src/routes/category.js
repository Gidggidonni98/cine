const express = require('express');
const router = express.Router();
const pool = require('../database.js');

// OBTENER TODOS LOS REGISTROS  //

router.get('/', async (req,res) => {
    let listCategory = await pool.query('SELECT * FROM category');
    res.json({
        status: 200,
        message: "Se ha listado correctamente",
        listCategory :listCategory
    });
});

// OBTENER REGISTRO POR ID  //

router.get('/:id' , async(req, res) => {
    const { id } = req.params;
    let category = await pool.query('SELECT * FROM category WHERE id = ?' , [id]);
     res.json({
         status: 200,
         message: "Se ha encontrado el registro",
         category : category
     });
});

// CREACION DE UN REGISTRO  //

router.post('/create', async (req, res) => {
    const { name } = req.body;

    const category = {
        name
    };
    await pool.query('INSERT INTO category SET ?' , [category]);
    res.json({
        status: 200,
        message: "Se ha registrado exitosamente",
        category : category
    });
});

// ACTUALIZACION DE UN REGISTRO  //

router.post('/update/:id', async(req, res) => {
    const { id } = req.params;
    const { name } = req.body;

    const category = {
        name
    };

    await pool.query('UPDATE category SET ? WHERE id = ?', [category,id]);
    res.json({
        status: 200,
        message: "Se ha actualizado correctamente",
        category : category
    });
});

//  ELIMINAR UN REGISTRO  //

router.post ('/delete/:id', async (req, res) =>{
    const { id } = req.params;

    await pool.query('DELETE FROM category WHERE id = ?', [id]);
    res.json({
        status: 200,
        message: "Se ha eliminado correctamente"
    });
});

module.exports = router;