const mongoose = require('mongoose');
const {Schema} = mongoose;
const UsuariosSchema = new Schema({
    cedula_usuario : {type: String, required:true},
    nombre_usuario : {type: String, required:true},
    email_usuario : {type: String, required:true},
    usuario : {type: String, required:true},
    password: {type: String, required:true}
});
module.exports = mongoose.model("usuarios", UsuariosSchema);