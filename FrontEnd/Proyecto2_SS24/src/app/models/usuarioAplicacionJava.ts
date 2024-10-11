export class UsuarioAplicacionJava {
    
    foto: string;
    hobbies: string;
    temasInteres: string;
    descripcion: string;
    gustos: string;
    userName: string;
    password: string;
    idTipoUsuario: number;
    nombre: string;
    idUsuario: number;

    constructor (foto: string, hobbies: string, temasInteres: string,
        descripcion: string, gustos: string, userName: string,
        password: string, idTipoUsuario: number, nombre: string, idUsuario: number) {
            this.foto = foto;
            this.hobbies = hobbies;
            this.temasInteres = temasInteres;
            this.descripcion = descripcion;
            this.gustos = gustos;
            this.userName = userName;
            this.password = password;
            this.idTipoUsuario = idTipoUsuario;
            this.nombre = nombre;
            this.idUsuario = idUsuario;
    }

}