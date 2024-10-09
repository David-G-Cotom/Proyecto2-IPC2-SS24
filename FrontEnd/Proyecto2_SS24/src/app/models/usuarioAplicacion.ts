import { Perfil } from "./perfil";
import { User } from "./user";

export class UsuarioAplicacion {

    user: User;
    perfil: Perfil;

    constructor(user:User, perfil:Perfil) {
        this.user = user;
        this.perfil = perfil;
    }

}