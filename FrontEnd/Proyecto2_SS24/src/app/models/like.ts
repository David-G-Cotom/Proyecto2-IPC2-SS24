export class Like {

    idUsuario: number;
    fechaLike: string;
    idRevista: number;

    constructor(idUsuario: number, fechaLike: string, idRevista: number) {
        this.idUsuario = idUsuario;
        this.fechaLike = fechaLike;
        this.idRevista = idRevista;
    }

}