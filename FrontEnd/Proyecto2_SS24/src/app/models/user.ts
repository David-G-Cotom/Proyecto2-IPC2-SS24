export class User {

    userName: string = '';
    password: string = '';
    tipoUsuario: string = '';

    constructor(userName:string, password:string, tipoUsuario:string) {
        this.userName = userName;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }
}