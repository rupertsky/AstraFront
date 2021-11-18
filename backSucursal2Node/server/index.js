const express = require('express');
const morgan = require('morgan');
const app = express(); 
const { mongoose } = require('./conexion');

//Configuración
app.set('port', process.env.PORT || 5000);


//Funciones para procesar los datos
app.use(morgan('dev'));
app.use(express.json());

//Rutas de nuestro servidor
app.use('/api/usuarios', require('./Routes/usuarios.routes'));


//Iniciar el servidor
app.listen(app.get('port'), () => {
    console.log('Servidor por el puerto: ', app.get('port'));
});