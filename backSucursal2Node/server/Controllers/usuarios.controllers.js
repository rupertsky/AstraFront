const Usuarios = require('../Models/usuarios');
const usucontroller = {};

usucontroller.listar = async(req, res) =>{
  const usuario = await Usuarios.find();
  res.json(usuario);
};

usucontroller.listarId = async(req, res) =>{
    const usuario = await Usuarios.findById(req.params.id);
    res.json(usuario);
};

usucontroller.guardar = async(req, res) =>{
    const usuario = new Usuarios(req.body);
    await usuario.save();
    res.json({
        'Status' : '200 Usuario Guardado'
    });
};

usucontroller.actualizar = async(req, res) =>{
    const { id } = req.params;
    const usuario = {
        nombre_usuario: req.body.nombre_usuario,
        email_usuario: req.body.email_usuario,
        usuario: req.body.usuario
    };
    await Usuarios.findByIdAndUpdate(id, {$set:usuario}, {new:true});
    res.json({
        'Status' : '200 Usuario Actualizado'
    });
};

usucontroller.eliminar = async(req, res) =>{
    const usuario = await Usuarios.findByIdAndRemove(req.params.id);
    res.json({
      'Status' : '200 Usuario Eliminado'
   });
  }
module.exports = usucontroller;