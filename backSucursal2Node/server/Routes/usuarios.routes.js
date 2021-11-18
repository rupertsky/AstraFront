const express = require('express');
const router = express.Router();
const usuarioctrl = require('../Controllers/usuarios.controllers');

router.get('/listar', usuarioctrl.listar);
router.post('/guardar', usuarioctrl.guardar);
router.get('/listar/:id', usuarioctrl.listarId);
router.put('/actualizar/:id', usuarioctrl.actualizar);
router.delete('/eliminar/:id', usuarioctrl.eliminar);

module.exports = router;

